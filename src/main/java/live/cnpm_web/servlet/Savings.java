package live.cnpm_web.servlet;

import com.google.gson.JsonObject;
import live.cnpm_web.data.transaction.TransactionDB;
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
import java.util.List;

@WebServlet(name = "Savings", value = "/savings")
public class Savings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String action = request.getParameter("action");

        // check activity session
        Activity activity = (Activity) request.getSession().getAttribute("activity");
        String accountType = (String) request.getSession().getAttribute("accountType");
        if (activity == null || !accountType.equals("Customer")) {
            response.sendRedirect("/");
            return;
        } else {
            url = "/WEB-INF/customer/customer-information/information.jsp";
        }
        TransactionAccount src = ((Customer) activity.getAccount()).getTransactionAccount();
        // end check

        if (action == null) {
            url = "/WEB-INF/customer/savings/savings-page.jsp";
            // get savings list by account id
            List<live.cnpm_web.entity.transaction.savings.Savings> savingsList = TransactionDB.selectAllByTransactionAccount(src, live.cnpm_web.entity.transaction.savings.Savings.class);

            // set parameters
            request.setAttribute("savingsList", savingsList);

        } else if (action.equals("create")) {
            url = "/WEB-INF/customer/savings/savings-create.jsp";
        } else if (action.equals("information")) {
            String id = request.getParameter("id");
            live.cnpm_web.entity.transaction.savings.Savings savings = TransactionDB.selectById(Long.parseLong(id), live.cnpm_web.entity.transaction.savings.Savings.class);
            if (savings == null || !savings.getTransactionAccountSource().getAccountNumber().equals(src.getAccountNumber())) {
                response.sendRedirect("/savings");
                return;
            } else {
                request.setAttribute("savings", savings);
                url = "/WEB-INF/customer/savings/savings-information.jsp";
            }
        }

        request.setAttribute("src", src);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/savings/savings-create.jsp";
        String message = "";
        String action = request.getParameter("action");

        Activity activity = (Activity) request.getSession().getAttribute("activity");
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

                if (message.equals("")) {
                    live.cnpm_web.entity.transaction.savings.Savings savings = new live.cnpm_web.entity.transaction.savings.Savings(src, name, Double.parseDouble(amount), term, rolled_over);
                    Verification verification = VerificationUtil.createVerification(customer.getEmail());

                    savings.setVerification(verification);
                    request.getSession().setAttribute("savings", savings);

                    String a = XHRUtil.getJSONString(new JSONMessage(true, ""));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(a);
                    out.flush();
                } else {
                    String a = XHRUtil.getJSONString(new JSONMessage(false, message));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(a);
                    out.flush();
                }
            } else if (action.equals("create_verification_code")) {
                live.cnpm_web.entity.transaction.savings.Savings savings = (live.cnpm_web.entity.transaction.savings.Savings) request.getSession().getAttribute("savings");
                message = VerificationUtil.createNewCode(savings.getVerification(), "");

                if (!message.equals("")) {
                    String a = XHRUtil.getJSONString(new JSONMessage(false, message));
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(a);
                    out.flush();
                }
            } else if (action.equals("check_verification_code")) {
                String code = json.get("verification_code").getAsString();
                live.cnpm_web.entity.transaction.savings.Savings savings = (live.cnpm_web.entity.transaction.savings.Savings) request.getSession().getAttribute("savings");
                message = VerificationUtil.verify(savings.getVerification(), code);

                if (message.equals("")) {
                    // make transfer here
                    TransactionUtil.savings(savings);

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

        // non XHR
        else if (action.equals("break")) {
            long id = Long.parseLong(request.getParameter("id"));
            live.cnpm_web.entity.transaction.savings.Savings savings = TransactionDB.selectById(id, live.cnpm_web.entity.transaction.savings.Savings.class);

            TransactionUtil.breakSavings(savings);
            response.sendRedirect("/savings");
        }
    }
}
