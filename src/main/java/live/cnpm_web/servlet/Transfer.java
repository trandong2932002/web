package live.cnpm_web.servlet;

import com.google.gson.JsonObject;
import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Transfer", value = "/transfer")
public class Transfer extends HttpServlet {
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
            url = "/WEB-INF/customer/transaction/transfer/transfer-create.jsp";
        }
        // end check
        TransactionAccount src = ((Customer) activity.getAccount()).getTransactionAccount();
        request.setAttribute("src", src);

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
            live.cnpm_web.entity.transaction.Transfer transfer = (live.cnpm_web.entity.transaction.Transfer)
                    request.getSession().getAttribute("temp-transfer");

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
                    TransactionUtil.transfer(transfer);

                    message = "Th??nh c??ng";

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
            String destNumber = request.getParameter("destNumber");
            String amount = request.getParameter("amount");
            String content = request.getParameter("content");

            Activity activity = (Activity) request.getSession().getAttribute("activity");
            TransactionAccount src = ((Customer) activity.getAccount()).getTransactionAccount();
            TransactionAccount dest = AccountDB.selectByAccountNumber(destNumber);

            message = ValidateTransactionUtil.validateTransfer(src, dest, amount);

            if (message.equals("")) {
                url = "/WEB-INF/customer/transaction/transfer/transfer-detail.jsp";

                live.cnpm_web.entity.transaction.Transfer transfer
                        = new live.cnpm_web.entity.transaction.Transfer(
                        src, dest, Double.parseDouble(amount), content);

                request.getSession().setAttribute("temp-transfer", transfer);
            } else {
                url = "/WEB-INF/customer/transaction/transfer/transfer-create.jsp";
            }

            request.setAttribute("src", src);
            request.setAttribute("destNumber", destNumber);
            request.setAttribute("message", message);
            request.setAttribute("amount", amount);
            request.setAttribute("content", content);
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
