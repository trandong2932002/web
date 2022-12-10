package live.cnpm_web.servlet.Employee;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.account.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.System.out;

@WebServlet(name = "sign-in-emp", value = "/sign-in-emp")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/WEB-INF/employee/sign-in.jsp";
        getServletContext().getRequestDispatcher(url)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Employee employee = AccountDB.authenticate(username,password, Employee.class);

        if(employee != null){
            HttpSession session = req.getSession();
//            session.setMaxInactiveInterval(1800);
            session.setAttribute("username-emp", username);
            resp.sendRedirect("/page-employee");
        }
        else {
            out.print("Sorry UserName or Password Error!");
            resp.sendRedirect("/sign-in-emp");
        }

    }
}
