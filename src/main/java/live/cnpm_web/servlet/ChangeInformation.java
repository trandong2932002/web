package live.cnpm_web.servlet;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.util.ValidateAccountUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ChangeInformation", value = "/change-information")
public class ChangeInformation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/customer-information/information.jsp";

        String sessionId = request.getSession().getId();
        Activity activity = ActivityDB.selectBySessionId(sessionId);
        Customer customer = (Customer)activity.getAccount();

        request.setAttribute("customer", customer);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String url = "/WEB-INF/customer/customer-information/information.jsp";
        String message;

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String district = request.getParameter("district");
        String city = request.getParameter("city-province");

        message = ValidateAccountUtil.validateBasicInformation(firstname, lastname, dob, address, district, city);
        boolean validateData = message.equals("");

        if (validateData) {
            Customer customer = (Customer) ActivityDB.selectBySessionId(request.getSession().getId()).getAccount();
            customer.setFirstname(firstname);
            customer.setLastname(lastname);
            customer.setDob(LocalDate.parse(dob));
            customer.setAddress(address);
            customer.setEmail(email);
            AccountDB.update(customer);

            message = "Thành công";

            request.setAttribute("customer", customer);
        }

        request.setAttribute("status", validateData);
        request.setAttribute("message", message);

        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("dob", dob);
        request.setAttribute("email", email);
        request.setAttribute("address", address);
        request.setAttribute("district", district);
        request.setAttribute("city-province", city);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
