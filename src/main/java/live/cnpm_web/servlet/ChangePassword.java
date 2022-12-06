package live.cnpm_web.servlet;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.util.ValidateAccountUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/change-password")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/customer-information/password.jsp";

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/customer-information/password.jsp";
        String message;

        Customer customer = (Customer) ActivityDB.selectBySessionId(request.getSession().getId()).getAccount();

        String oldPassword = request.getParameter("old-password");
        String newPassowrd = request.getParameter("new-password");
        String retypeNewPassowrd = request.getParameter("retype-new-password");

        message = ValidateAccountUtil.validateChangePassword(customer, oldPassword, newPassowrd, retypeNewPassowrd);
        boolean validateData = message.equals("");

        if (validateData) {
            customer.setPassword(newPassowrd);
            AccountDB.update(customer);

            message = "Thành công";
        }

        request.setAttribute("status", validateData);
        request.setAttribute("message", message);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
