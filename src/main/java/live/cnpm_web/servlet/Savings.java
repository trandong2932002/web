package live.cnpm_web.servlet;

import com.google.gson.JsonObject;
import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.data.verification.VerificationDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
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

            live.cnpm_web.entity.transaction.Savings savings = TransactionDB.selectById(Long.parseLong(id), live.cnpm_web.entity.transaction.Savings.class);

            request.setAttribute("savings", savings);

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

        String sessionId = request.getSession().getId();
        Activity activity = ActivityDB.selectBySessionId(sessionId);
        Customer customer = (Customer) activity.getAccount();
        TransactionAccount src = customer.getTransactionAccount();

        // XHR
        if (action == null) {
            JsonObject json = XHRUtil.parseJSONObject(request);
            action = json.get("action").getAsString();
            if (action.equals("create_savings")) {
                String amount = json.get("amount").getAsString();
                String name = json.get("savings-name").getAsString();
                int term = Integer.parseInt(json.get("term").getAsString());
                int rolled_over = Integer.parseInt(json.get("rolled-over").getAsString());

                message = ValidateTransactionUtil.validateSavings(src, amount);
                boolean validateData = message.equals("");

                if (validateData) {
                    live.cnpm_web.entity.transaction.Savings savings = new live.cnpm_web.entity.transaction.Savings(src, name, Double.parseDouble(amount), term, rolled_over);
                    Verification verification = new Verification();
                    VerificationDB.insert(verification);
                    savings.setVerification(verification);
                    request.getSession().setAttribute("savings", savings);

                    String a = XHRUtil.getJSONString(new JSONMessage(true, ""));
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    out.print(a);
                    out.flush();
                } else {
                    String a = XHRUtil.getJSONString(new JSONMessage(false, message));

                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(a);
                    out.flush();
                }
            } else if (action.equals("create_verification_code")) {
                live.cnpm_web.entity.transaction.Savings savings = (live.cnpm_web.entity.transaction.Savings) request.getSession().getAttribute("savings");
                message = VerificationUtil.createNewCode(savings.getVerification());
                boolean createCode = message.equals("");

                if (!createCode) {
                    String a = XHRUtil.getJSONString(new JSONMessage(false, message));

                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(a);
                    out.flush();
                }
            } else if (action.equals("check_verification_code")) {
                String code = json.get("verification_code").getAsString();
                live.cnpm_web.entity.transaction.Savings savings = (live.cnpm_web.entity.transaction.Savings) request.getSession().getAttribute("savings");
                message = VerificationUtil.verify(savings.getVerification(), code);
                boolean verify = message.equals("");

                if (verify) {
                    // make transfer here
                    TransactionUtil.savings(savings);

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

        // non XHR
        else if (action.equals("break")) {

        }
    }
}
