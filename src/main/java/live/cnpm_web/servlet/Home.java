package live.cnpm_web.servlet;

import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.util.ActivityUtil;
import live.cnpm_web.util.SavingsInterestUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Home extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        // run savings check each day
//        Timer timer = new Timer();
//        Calendar date = Calendar.getInstance();
//        date.set(
//                Calendar.DAY_OF_WEEK,
//                Calendar.SUNDAY
//        );
//        date.set(Calendar.HOUR, 0);
//        date.set(Calendar.MINUTE, 0);
//        date.set(Calendar.SECOND, 0);
//        date.set(Calendar.MILLISECOND, 0);
//
//        timer.schedule(
//                new CheckSavingsEachDay(),
//                date.getTime(),
//                1000 * 60 * 60 * 24
//        );

        // load default savings interests
        SavingsInterestUtil.loadSavingsInterestList();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // check activity session
        ImmutablePair<Activity, String> activityResult = ActivityUtil.checkGuestSessionActivity(request.getSession().getId());
        String message = activityResult.getRight();
        Activity activity = activityResult.getLeft();
        if (message.equals("")) {
            String accountType = (String) request.getSession().getAttribute("accountType");
            ActivityUtil.clearTempSession(request.getSession());
            request.getSession().setAttribute("activity", activity);
            request.getSession().setAttribute("accountType", accountType);
            if (accountType.equals("Customer")) {
                url = "/WEB-INF/customer/main-page.jsp";
            } else if (accountType.equals("Employee")) {
                // url = employee main page
            } else if (accountType.equals("Manager")) {
                url = "/WEB-INF/manager/main-page.jsp";
            }
        } else {
            url = "/WEB-INF/guest/main-page.jsp";
            request.setAttribute("message", message);
        }
        // end check

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
