package live.cnpm_web.servlet;

import com.google.gson.JsonObject;
import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Savings", value = "/savings")
public class Savings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String action = request.getParameter("action");

        String sessionId = request.getSession().getId();
        Activity activity = ActivityDB.selectBySessionId(sessionId);
        Customer customer = (Customer) activity.getAccount();
        TransactionAccount src = customer.getTransactionAccount();

        if (action == null) {
            url = "/WEB-INF/customer/savings/savings-page.jsp";
            // get savings list by account id
            List<live.cnpm_web.entity.transaction.Savings> savingsList = TransactionDB.selectAllByTransactionAccount(src, live.cnpm_web.entity.transaction.Savings.class);

            // set parameters
            request.setAttribute("savingsList", savingsList);

        } else if (action.equals("create")) {
            url = "/WEB-INF/customer/savings/savings-create.jsp";
        } else if (action.equals("information")) {
            String id = request.getParameter("id");



            url = "/WEB-INF/customer/savings/savings-information.jsp";
        }

        request.setAttribute("src", src);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/savings/savings-create.jsp";
        String message = "";
        String action = request.getParameter("action");

        // XHR
        if (action == null) {
            JsonObject json = XHRUtil.parseJSONObject(request);
            System.out.println(json);
            action = json.get("action").getAsString();
            if (action.equals("create_verification_code")) {
//                if (transfer.getVerification() == null) {
//                    Verification verification = new Verification();
//                    // one way binding, so verification must be inserted first
//                    VerificationDB.insert(verification);
//                    transfer.setVerification(verification);
//                } else {
//                    Verification verification = transfer.getVerification();
//                    message = VerificationUtil.createNewCode(verification);
//                    boolean createCode = message.equals("");
//
//                    if (!createCode) {
//                        String a = XHRUtil.getJSONString(new JSONMessage(false, message));
//
//                        PrintWriter out = response.getWriter();
//                        response.setContentType("application/json");
//                        response.setCharacterEncoding("UTF-8");
//                        out.print(a);
//                        out.flush();
//                    }
//                }
            } else if (action.equals("check_verification_code")) {
//                String code = json.get("verification_code").getAsString();
//                message = VerificationUtil.verify(transfer.getVerification(), code);
//                boolean verify = message.equals("");
//
//                if (verify) {
//                    // make transfer here
//                    TransactionUtil.transfer(transfer);
//
//                    message = "Thành công";
//
//                    String a = XHRUtil.getJSONString(
//                            new JSONMessage(true, message)
//                    );
//
//                    PrintWriter out = response.getWriter();
//                    response.setContentType("application/json");
//                    response.setCharacterEncoding("UTF-8");
//                    out.print(a);
//                    out.flush();
//                    ActivityUtil.clearSession(request.getSession());
//                } else {
//
//                    String a = XHRUtil.getJSONString(
//                            new JSONMessage(false, message)
//                    );
//
//                    PrintWriter out = response.getWriter();
//                    response.setContentType("application/json");
//                    response.setCharacterEncoding("UTF-8");
//                    out.print(a);
//                    out.flush();
//                }
            }
            return;
        }

//        else if (action.equals("create")) {
//            String name = request.getParameter("savings-name");
//            String amount = request.getParameter("amount");
//            String term = request.getParameter("term");
//            String createdDate = request.getParameter("create-date");
//            String rolledOver = request.getParameter("rolled-over");
//        }
    }
}
