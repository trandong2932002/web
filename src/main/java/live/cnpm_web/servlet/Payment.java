package live.cnpm_web.servlet;

import com.google.gson.JsonObject;
import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.account.ActivityDB;
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

@WebServlet(name = "Payment", value = "/payment")
public class Payment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/transaction/payment/payment-create.jsp";

        String sessionId = request.getSession().getId();
        Activity activity = ActivityDB.selectBySessionId(sessionId);
        Customer customer = (Customer) activity.getAccount();
        TransactionAccount src = customer.getTransactionAccount();

        request.setAttribute("src", src);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
