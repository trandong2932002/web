package live.cnpm_web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignUp", value = "/sign-up")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/guest/sign-up/basic-information.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String state = request.getParameter("action");

        if (state.equals("basic-information")) {
            // validate data 1
            // ...

            if ( /* validate data */ true) {
                url = "/WEB-INF/guest/sign-up/contact-information.jsp";
            } else if (false) {
                url = "/WEB-INF/guest/sign-up/basic-information.jsp";
                // fail massage
            }
        }
        else if (state.equals("contact-information")) {
            // validate data 2
            // ...

            if ( /* validate data */ true) {
                url = "/sign-in";
                response.sendRedirect(url);
                // create user
                return;
            } else if (false)
            {
                url = "/WEB-INF/guest/sign-up/contact-information.jsp";
                // fail message
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
