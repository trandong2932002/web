package live.cnpm_web.servlet.manager;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.account.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerManagement", value = "/_manager/customer")
public class CustomerManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // check activity session
        Activity activity = (Activity) request.getSession().getAttribute("activity");
        String accountType = (String) request.getSession().getAttribute("accountType");
        if (activity == null || !accountType.equals("Manager")) {
            response.sendRedirect("/");
            return;
        } else {
            url = "/WEB-INF/manager/customer-management.jsp";
        }
        // end check

        List<Customer> customerList = AccountDB.selectAll(Customer.class);

        request.setAttribute("customerList", customerList);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
