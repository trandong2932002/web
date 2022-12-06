package live.cnpm_web.servlet;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.verification.VerificationDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.util.ValidateAccountUtil;
import live.cnpm_web.util.VerificationUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@WebServlet(name = "SignUp", value = "/sign-up")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/guest/sign-up/basic-information.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String url = "";
        String state = request.getParameter("action");
        String message;

        if (state.equals("basic-information")) {
            // validate data 1
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            String district = request.getParameter("district");
            String city = request.getParameter("city-province");

            message = ValidateAccountUtil.validateBasicInformation(firstname, lastname, dob, address, district, city);
            boolean validateData = message.equals("");

            if (validateData) {
                url = "/WEB-INF/guest/sign-up/contact-information.jsp";

                Customer customer = new Customer();
                customer.setFirstname(firstname);
                customer.setLastname(lastname);
                customer.setDob(LocalDate.parse(dob));
                customer.setAddress(address);

                request.getSession().setAttribute("temp-customer", customer);
            } else {
                url = "/WEB-INF/guest/sign-up/basic-information.jsp";
                request.setAttribute("message", message);
                request.setAttribute("firstname", new String(firstname.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                request.setAttribute("lastname", new String(lastname.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                request.setAttribute("dob", dob);
                request.setAttribute("address", address);
                request.setAttribute("district", district);
                request.setAttribute("city", city);
            }
        } else if (state.equals("contact-information")) {
            // validate data 2
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phone-number");
            String password = request.getParameter("password");
            String retypePassword = request.getParameter("retype-password");

            message = ValidateAccountUtil.validateContactInformation(email, phoneNumber, password, retypePassword);
            boolean validateData = message.equals("");

            if (validateData) {
                url = "/WEB-INF/guest/verification.jsp";

                Customer customer = (Customer) request.getSession().getAttribute("temp-customer");
                customer.setEmail(email);
                customer.setPhoneNumber(phoneNumber);
                customer.setPassword(password);

                Verification verification = new Verification();
                VerificationDB.insert(verification);

                request.getSession().setAttribute("temp-customer", customer);
                request.getSession().setAttribute("temp-verification", verification);
            } else {
                url = "/WEB-INF/guest/sign-up/contact-information.jsp";
                request.setAttribute("message", message);
                request.setAttribute("email", email);
                request.setAttribute("phoneNumber", phoneNumber);
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
                    url = "/sign-in";
                    HttpSession session = request.getSession();

                    Customer customer = (Customer) session.getAttribute("temp-customer");

                    // create transaction account
                    TransactionAccount transactionAccount = new TransactionAccount();
                    customer.addTransactionAccount(transactionAccount);

                    AccountDB.insert(customer);

                    // clear session
                    session.removeAttribute("temp-customer");
                    session.removeAttribute("temp-verification");

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
