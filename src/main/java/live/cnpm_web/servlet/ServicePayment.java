package live.cnpm_web.servlet;

import com.google.gson.JsonObject;
import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.ServiceAccount;
import live.cnpm_web.entity.account.ServiceProvider;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.transaction.Transfer;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.util.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Payment", value = "/payment")
public class ServicePayment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // check activity session
        Activity activity = (Activity) request.getSession().getAttribute("activity");
        String accountType = (String) request.getSession().getAttribute("accountType");
        if (activity == null || !accountType.equals("Customer")) {
            response.sendRedirect("/");
            return;
        } else {
            url = "/WEB-INF/customer/transaction/payment/payment-create.jsp";
        }
        // end check
        TransactionAccount src = ((Customer) activity.getAccount()).getTransactionAccount();
        List<ServiceProvider> providerList = AccountDB.selectAll(ServiceProvider.class);

        request.setAttribute("src", src);
        request.setAttribute("providerList", providerList);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String url = "";
        String message = "";
        String action = request.getParameter("action");

        // XHR
        if (action == null) {
            JsonObject json = XHRUtil.parseJSONObject(request);
            action = json.get("action").getAsString();
            Transfer transfer = (Transfer) request.getSession().getAttribute("temp-transfer");

            Activity activity = (Activity) request.getSession().getAttribute("activity");
            Customer customer = (Customer) activity.getAccount();


            if (action.equals("create_verification_code")) {
                if (transfer.getVerification() == null) {
                    Verification verification = VerificationUtil.createVerification(customer.getEmail());

                    transfer.setVerification(verification);
                } else {
                    Verification verification = transfer.getVerification();
                    message = VerificationUtil.createNewCode(verification, "");

                    if (!message.equals("")) {
                        String a = XHRUtil.getJSONString(new JSONMessage(false, message));
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter out = response.getWriter();
                        out.print(a);
                        out.flush();
                    }
                }
            } else if (action.equals("check_verification_code")) {
                String code = json.get("verification_code").getAsString();
                message = VerificationUtil.verify(transfer.getVerification(), code);

                if (message.equals("")) {
                    // make transfer here
                    // because service account is management by provider -> calc debt
                    ServiceAccount serviceAccount = (ServiceAccount) request.getSession().getAttribute("temp-service-account");
                    TransactionUtil.payment(transfer, serviceAccount);

                    message = "Thành công";

                    String a = XHRUtil.getJSONString(new JSONMessage(true, message));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(a);
                    out.flush();
                    ActivityUtil.clearTempSession(request.getSession());
                } else {

                    String a = XHRUtil.getJSONString(new JSONMessage(false, message));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(a);
                    out.flush();
                }
            }
            return;
        }


        //
        // non XHR
        //
        else if (action.equals("create")) {
            String provider = request.getParameter("service-provider");
            String customerCode = request.getParameter("customer-code");

            Activity activity = (Activity) request.getSession().getAttribute("activity");
            TransactionAccount src = ((Customer) activity.getAccount()).getTransactionAccount();

            ImmutablePair<ServiceAccount, String> result = ValidateTransactionUtil.validateServicePayment(src, provider, customerCode);
            message = result.getRight();

            if (message.equals("")) {
                url = "/WEB-INF/customer/transaction/payment/payment-detail.jsp";
                ServiceAccount serviceAccount = result.getLeft();
                TransactionAccount dest = serviceAccount.getProvider().getProvider().getTransactionAccount();
                Double amount = serviceAccount.getDebt();
                String providerName = serviceAccount.getProvider().getName();

                Transfer transfer = new Transfer(src, dest, amount, "Thanh toán hóa đơn cho " + providerName);

                request.getSession().setAttribute("temp-transfer", transfer);
                request.getSession().setAttribute("temp-service-account", serviceAccount);
                request.setAttribute("amount", amount);
                request.setAttribute("providerName", providerName);
            } else {
                url = "/WEB-INF/customer/transaction/payment/payment-create.jsp";
                List<ServiceProvider> providerList = AccountDB.selectAll(ServiceProvider.class);

                request.setAttribute("message", message);
                request.setAttribute("providerList", providerList);
            }

            request.setAttribute("src", src);
            request.setAttribute("customerCode", customerCode);
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

    }
}
