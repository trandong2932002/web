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
import java.time.LocalDate;

@WebServlet(name = "ChangeInformation", value = "/change-information")
public class ChangeInformation extends HttpServlet {
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
            url = "/WEB-INF/customer/customer-information/information.jsp";
        }
        // end check

        request.setAttribute("customer", activity.getAccount());
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

        if (message.equals("")) {
            Customer customer = (Customer) ((Activity) request.getSession().getAttribute("activity")).getAccount();
            customer.setFirstname(firstname);
            customer.setLastname(lastname);
            customer.setDob(LocalDate.parse(dob));
            customer.setAddress(address);
            customer.setEmail(email);
            AccountDB.update(customer);

            message = "Thành công";

            request.setAttribute("customer", customer);
        }

        request.setAttribute("status", message.equals(""));
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
