package live.cnpm_web.servlet.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "page-employee", value = "/page-employee")
public class PageMain extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String url = "";
        if(session.getAttribute("username-emp") == null){
            url = "/WEB-INF/employee/main.jsp";
            getServletContext().getRequestDispatcher(url).forward(req,resp);
        }
        else {
            url = "/WEB-INF/employee/page-main.jsp";
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
