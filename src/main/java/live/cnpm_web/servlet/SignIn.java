package live.cnpm_web.servlet;

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
import java.io.IOException;

@WebServlet(name = "SignIn", value = "/sign-in")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // check activity session
        Activity activity = (Activity) request.getSession().getAttribute("activity");
        if (activity == null) {
            url = "/WEB-INF/guest/sign-in.jsp";
        } else {
            response.sendRedirect("/");
            return;
        }
        // end check

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String message = "";
        String state = request.getParameter("action");
        String phoneNumber = request.getParameter("phone-number");
        String password = request.getParameter("password");

        if (state.equals("sign-in")) {

            ImmutableTriple<String, BaseAccount, Class<?>> validate = ValidateAccountUtil.validateSignInAccount(phoneNumber, password);
            message = validate.getLeft();
            Class<?> accountType = validate.getRight();
            BaseAccount account = validate.getMiddle();

            if (message.equals("")) {
                url = "/WEB-INF/guest/verification.jsp";

                Verification verification = VerificationUtil.createVerification(account.getEmail());

                request.getSession().setAttribute("temp-verification", verification);
                request.getSession().setAttribute("temp-account", account);
                request.getSession().setAttribute("accountType", accountType.getSimpleName());
            } else {
                url = "/WEB-INF/guest/sign-in.jsp";
                request.setAttribute("message", "Sai thông tin đăng nhập");
            }
        } else if (state.equals("verification")) {
            String action2 = request.getParameter("action2");

            if (action2.equals("create")) {
                url = "/WEB-INF/guest/verification.jsp";

                Verification verification = (Verification) request.getSession().getAttribute("temp-verification");
                BaseAccount account = (BaseAccount) request.getSession().getAttribute("temp-account");
                message = VerificationUtil.createNewCode(verification, account.getEmail());

                if (!message.equals("")) {
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

                Verification verification = (Verification) request.getSession().getAttribute("temp-verification");
                message = VerificationUtil.verify(verification, code);
                boolean verify = message.equals("");

                if (verify) {
                    BaseAccount account = (BaseAccount) request.getSession().getAttribute("temp-account");
                    ActivityUtil.createActivity(account, verification, request.getSession().getId());

                    response.sendRedirect("/");
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
