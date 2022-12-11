package live.cnpm_web.servlet.manager;

import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.util.SavingsInterestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

@WebServlet(name = "ChangeInterest", value = "/_manager/interest")
public class ChangeInterest extends HttpServlet {
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
            url = "/WEB-INF/manager/change-interest.jsp";
        }
        // end check

        request.setAttribute("interestList", SavingsInterestUtil.getSavingsInterestList());
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String interest0 = request.getParameter("interest0");
        String interest1 = request.getParameter("interest1");
        String interest2 = request.getParameter("interest2");
        String interest3 = request.getParameter("interest3");
        String interest4 = request.getParameter("interest4");
        String interest5 = request.getParameter("interest5");
        String penaltyInterest0 = request.getParameter("penaltyInterest0");
        String penaltyInterest1 = request.getParameter("penaltyInterest1");
        String penaltyInterest2 = request.getParameter("penaltyInterest2");
        String penaltyInterest3 = request.getParameter("penaltyInterest3");
        String penaltyInterest4 = request.getParameter("penaltyInterest4");
        String penaltyInterest5 = request.getParameter("penaltyInterest5");

        String message = SavingsInterestUtil.validateSavingsInterest(interest0, interest1, interest2, interest3, interest4, interest5,
                penaltyInterest0, penaltyInterest1, penaltyInterest2, penaltyInterest3, penaltyInterest4, penaltyInterest5);
        if (message.equals("")) {
            try {
                SavingsInterestUtil.writeSavingsInterest(interest0, interest1, interest2, interest3, interest4, interest5,
                        penaltyInterest0, penaltyInterest1, penaltyInterest2, penaltyInterest3, penaltyInterest4, penaltyInterest5);

                request.setAttribute("status", true);
                request.setAttribute("message", "Thành công");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

        } else {
            request.setAttribute("status", false);
            request.setAttribute("message", message);
        }

        request.setAttribute("interestList", SavingsInterestUtil.getSavingsInterestList());
        String url = "/WEB-INF/manager/change-interest.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
