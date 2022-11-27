package live.cnpm_web.servlet;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignIn", value = "/sign-in")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/guest/sign-in.jsp";

        Customer customer = AccountDB.selectById(1L, Customer.class);
        TransactionAccount transactionAccount = customer.getTransactionAccount();
        System.out.println(transactionAccount.getId());

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // validate data
        // ...

        if ( /* validate data */ true) {
            url = "/";
            // create session activity
            response.sendRedirect(url);
            return;
        } else if (false) {
            url = "/WEB-INF/guest/sign-in.jsp";
            // fail message
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
