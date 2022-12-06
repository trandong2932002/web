package live.cnpm_web.servlet;

import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.data.verification.VerificationDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.account.BaseAccount;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.util.ActivityUtil;
import live.cnpm_web.util.ValidateAccountUtil;
import live.cnpm_web.util.VerificationUtil;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SignIn", value = "/sign-in")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/guest/sign-in.jsp";

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String state = request.getParameter("action");
        String message;

        String phoneNumber = request.getParameter("phone-number");
        String password = request.getParameter("password");

        if (state.equals("sign-in")) {
            BaseAccount baseAccount;
            Class<?> accountType;

            ImmutableTriple<String, BaseAccount, Class<?>> validate = ValidateAccountUtil.validateSignInAccount(phoneNumber, password);
            message = validate.getLeft();
            accountType = validate.getRight();
            baseAccount = validate.getMiddle();

            boolean auth = message.equals("");

            if (auth) {
                url = "/WEB-INF/guest/verification.jsp";

                Verification verification = new Verification();
                VerificationDB.insert(verification);

                request.getSession().setAttribute("temp-verification", verification);
                request.getSession().setAttribute("temp-account", baseAccount);
            } else {
                url = "/WEB-INF/guest/sign-in.jsp";
                request.setAttribute("message", "Sai thông tin đăng nhập");
            }
        } else if (state.equals("verification")) {
            String action2 = request.getParameter("action2");

            if (action2.equals("create")) {
                url = "/WEB-INF/guest/verification.jsp";

                Verification verification = (Verification)request.getSession().getAttribute("temp-verification");
                message = VerificationUtil.createNewCode(verification);
                boolean createCode = message.equals("");

                if (createCode) {

                } else {
                    request.setAttribute("message", message);
                }
            } else {
                // verify
                String[] codeArray = new String[6];
                codeArray[0] = request.getParameter("otp-first");
                codeArray[1] = request.getParameter("otp-second");
                codeArray[2] = request.getParameter("otp-third");
                codeArray[3] = request.getParameter("otp-fourth");
                codeArray[4] = request.getParameter("otp-fifth");
                codeArray[5] = request.getParameter("otp-sixth");

                String code = "";
                for (String x : codeArray) {
                    code += x;
                }

                Verification verification = (Verification)request.getSession().getAttribute("temp-verification");
                message = VerificationUtil.verify(verification, code);
                boolean verify = message.equals("");

                if (verify) {
                    url = "/";
                    HttpSession session = request.getSession();
                    BaseAccount account = (BaseAccount) session.getAttribute("temp-account");

                    Activity activity = ActivityUtil.createActivity(account, verification, session.getId());

                    // session will lost after redirect
                    // solution 1: use filter
                    // solution 2: save data in database (my choice)

                    ActivityUtil.clearSession(session);
                    response.sendRedirect(url);
                    return;
                } else {
                    url = "/WEB-INF/guest/verification.jsp";
                    request.setAttribute("message", message);
                }
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
