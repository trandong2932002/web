package live.cnpm_web.servlet.Employee;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.account.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "sign-up-emp", value = "/sign-up-emp")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/WEB-INF/employee/signup.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if( action ==null)
        {
            resp.sendRedirect("/page-employee");
        }
        else {
            String email = req.getParameter("email");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String password = req.getParameter("password");
            String phone_number = req.getParameter("phone_number");
            String ssn = req.getParameter("ssn");

            Employee employee = new Employee();
            employee.setEmail(email);
            employee.setFirstname(firstname);
            employee.setLastname(lastname);
            employee.setPassword(password);
            employee.setPhoneNumber(phone_number);
            employee.setSsn(ssn);

            AccountDB.insert(employee);
            resp.sendRedirect("sign-in-emp");
        }
    }
}
