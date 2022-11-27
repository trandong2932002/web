package live.cnpm_web.servlet;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.account.TransactionAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;


public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String sign_in_result = request.getParameter("sign_in_result");

        // validate activity session
        // ...

        if (sign_in_result == null || sign_in_result.isEmpty() /* || session expired */) {
            url = "/WEB-INF/guest/main-page.jsp";
        } else if (true /* session still alive */) {
            url = "/WEB-INF/customer/main-page.jsp";
        }

        TransactionAccount transactionAccount = new TransactionAccount();
        Customer customer = new Customer();
        customer.setFirstname(LocalDateTime.now().toString());

        // transactionAccount.setAccount(account);

        customer.addTransactionAccount(transactionAccount);

        AccountDB.insert(customer);
        System.out.println(AccountDB.selectById(1L, Customer.class).getFirstname());

//        Activity activity = new Activity();
//        activity.setAccount(AccountDB.getById(1L));

//        ActivityDB.insert(activity);


        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
