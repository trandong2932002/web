package live.cnpm_web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class Activity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String sign_in_result = request.getParameter("sign_in_result");

        // validate activity session
        // ...

        if (sign_in_result == null || sign_in_result.isEmpty() /* || session expired */ ) {
            url = "/WEB-INF/guest/main-page.jsp";
        } else if (true /* session still alive */ ) {
            url = "/WEB-INF/customer/main-page.jsp";
        }



        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
