package live.cnpm_web.servlet;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.util.ValidateAccountUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/change-password")
public class ChangePassword extends HttpServlet {
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
            url = "/WEB-INF/customer/customer-information/password.jsp";
        }
        // end check

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/customer-information/password.jsp";
        String message;

        Customer customer = (Customer) ((Activity) request.getSession().getAttribute("activity")).getAccount();
        String oldPassword = request.getParameter("old-password");
        String newPassowrd = request.getParameter("new-password");
        String retypeNewPassowrd = request.getParameter("retype-new-password");

        message = ValidateAccountUtil.validateChangePassword(customer, oldPassword, newPassowrd, retypeNewPassowrd);

        if (message.equals("")) {
            customer.setPassword(newPassowrd);
            AccountDB.update(customer);

            message = "Thành công";
        }

        request.setAttribute("status", message.equals(""));
        request.setAttribute("message", message);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
