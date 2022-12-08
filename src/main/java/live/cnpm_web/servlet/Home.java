package live.cnpm_web.servlet;

import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.transaction.Savings;
import live.cnpm_web.util.ActivityUtil;
import live.cnpm_web.util.CheckSavingsEachDay;
import live.cnpm_web.util.DBUtil;
import live.cnpm_web.util.TransactionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;


public class Home extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
//        DBUtil.getEMFactory().createEntityManager();

        // run savings check each day
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(
                Calendar.DAY_OF_WEEK,
                Calendar.SUNDAY
                );
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        timer.schedule(
                new CheckSavingsEachDay(),
                date.getTime(),
                1000 * 60 * 60 * 24
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // validate activity session
        HttpSession session = request.getSession();
        Activity activity = ActivityDB.selectBySessionId(session.getId());

        String message = ActivityUtil.checkActivity(activity);
        boolean activityResult = message.equals("");

        if (!activityResult) {
            url = "/WEB-INF/guest/main-page.jsp";
        } else {
            url = "/WEB-INF/customer/main-page.jsp";
            request.setAttribute("message", message);
        }

        // clear all session data
        ActivityUtil.clearSession(session);


        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
