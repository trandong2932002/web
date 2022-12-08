package live.cnpm_web.servlet;

import com.google.gson.JsonObject;
import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.data.verification.VerificationDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.entity.verification.VerificationCode;
import live.cnpm_web.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Transfer", value = "/transfer")
public class Transfer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/transaction/transfer/transfer-create.jsp";

        String sessionId = request.getSession().getId();
        Activity activity = ActivityDB.selectBySessionId(sessionId);
        Customer customer = (Customer) activity.getAccount();
        TransactionAccount src = customer.getTransactionAccount();

        request.setAttribute("src", src);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String url = "";
        String message = "";
        String action = request.getParameter("action");

        // XML request
        if (action == null) {
            JsonObject json = XHRUtil.parseJSONObject(request);
            action = json.get("action").getAsString();
            live.cnpm_web.entity.transaction.Transfer transfer = (live.cnpm_web.entity.transaction.Transfer)
                    request.getSession().getAttribute("temp-transfer");

            if (action.equals("create_verification_code")) {
                if (transfer.getVerification() == null) {
                    Verification verification = new Verification();
                    // one way binding, so verification must be inserted first
                    VerificationDB.insert(verification);

                    // send mail
                    String sessionId = request.getSession().getId();
                    Activity activity = ActivityDB.selectBySessionId(sessionId);
                    Customer customer = (Customer) activity.getAccount();
                    List<VerificationCode> verificationCodeList = verification.getVerificationCodeList();
                    VerificationCode code = verificationCodeList.get(verificationCodeList.size() - 1);
                    EmailUtil.sendEmail(customer.getEmail(), code.getCode());
                    //

                    transfer.setVerification(verification);
                } else {
                    Verification verification = transfer.getVerification();
                    message = VerificationUtil.createNewCode(verification);

                    // send mail
                    Customer customer = transfer.getTransactionAccountSource().getAccount();
                    List<VerificationCode> verificationCodeList = verification.getVerificationCodeList();
                    VerificationCode code = verificationCodeList.get(verificationCodeList.size() - 1);
                    EmailUtil.sendEmail(customer.getEmail(), code.getCode());
                    //

                    boolean createCode = message.equals("");

                    if (!createCode) {
                        String a = XHRUtil.getJSONString(new JSONMessage(false, message));

                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(a);
                        out.flush();
                    }
                }
            } else if (action.equals("check_verification_code")) {
                String code = json.get("verification_code").getAsString();
                message = VerificationUtil.verify(transfer.getVerification(), code);
                boolean verify = message.equals("");

                if (verify) {
                    // make transfer here
                    TransactionUtil.transfer(transfer);

                    message = "Thành công";

                    String a = XHRUtil.getJSONString(
                            new JSONMessage(true, message)
                    );

                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(a);
                    out.flush();
                    ActivityUtil.clearSession(request.getSession());
                } else {

                    String a = XHRUtil.getJSONString(
                            new JSONMessage(false, message)
                    );

                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
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

            String sessionId = request.getSession().getId();
            Activity activity = ActivityDB.selectBySessionId(sessionId);
            Customer customer = (Customer) activity.getAccount();
            TransactionAccount src = customer.getTransactionAccount();
            TransactionAccount dest = AccountDB.selectByAccountNumber(destNumber);

            message = ValidateTransactionUtil.validateTransfer(src, dest, amount);
            boolean validateData = message.equals("");

            if (validateData) {
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
