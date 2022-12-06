package live.cnpm_web.servlet;

import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.entity.account.Activity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "SignOut", value = "/sign-out")
public class SignOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        Activity activity = ActivityDB.selectBySessionId(sessionId);
        if (activity != null) {
            activity.setEndTime(LocalDateTime.now());

            System.out.println(activity.getAccount().getId());
            System.out.println(activity.getVerification().getId());

            ActivityDB.update(activity);

            request.getSession(false).invalidate();
        }
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
