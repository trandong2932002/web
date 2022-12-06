package live.cnpm_web.servlet;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.transaction.Savings;
import live.cnpm_web.entity.transaction.Transfer;
import live.cnpm_web.util.ActivityUtil;
import live.cnpm_web.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;


public class Home extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        DBUtil.getEMFactory().createEntityManager();
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
//        JsonObject json = XHRUtil.parseJSONObject(request);
//
//        String action = json.get("action").getAsString();
//
//        if (action.equals("create_verification_code")) {
//            System.out.println("create");
//        } else if (action.equals("check_verification_code")) {
//            System.out.println(json.get("verification_code").getAsString());
//            // verify
//        }
//
//        String message = "Hello World";
//        String cus = new Gson().toJson(message);
//
//        PrintWriter out = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        out.print(cus);
//        out.flush();

        doGet(request, response);
    }
}
