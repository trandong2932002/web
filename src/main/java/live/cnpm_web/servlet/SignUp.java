package live.cnpm_web.servlet;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.util.ValidateAccountUtil;
import live.cnpm_web.util.VerificationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignUp", value = "/sign-up")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // check activity session
        Activity activity = (Activity) request.getSession().getAttribute("activity");
        if (activity == null) {
            url = "/WEB-INF/guest/sign-up/basic-information.jsp";
        } else {
            response.sendRedirect("/");
            return;
        }
        // end check

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String url = "";
        String message = "";
        String state = request.getParameter("action");

        if (state.equals("basic-information")) {
            // validate data 1
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            String district = request.getParameter("district");
            String city = request.getParameter("city-province");

            message = ValidateAccountUtil.validateBasicInformation(firstname, lastname, dob, address, district, city);

            if (message.equals("")) {
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
                request.setAttribute("firstname", firstname);
                request.setAttribute("lastname", lastname);
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

            if (message.equals("")) {
                url = "/WEB-INF/guest/verification.jsp";

                Customer customer = (Customer) request.getSession().getAttribute("temp-customer");
                customer.setEmail(email);
                customer.setPhoneNumber(phoneNumber);
                customer.setPassword(password);

                Verification verification = VerificationUtil.createVerification(email);

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

                Verification verification = (Verification) request.getSession().getAttribute("temp-verification");
                Customer customer = (Customer) request.getSession().getAttribute("temp-customer");
                message = VerificationUtil.createNewCode(verification, customer.getEmail());

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

                if (message.equals("")) {
                    Customer customer = (Customer) request.getSession().getAttribute("temp-customer");

                    // create transaction account
                    TransactionAccount transactionAccount = new TransactionAccount();
                    customer.addTransactionAccount(transactionAccount);
                    AccountDB.insert(customer);

                    // clear session
                    request.getSession().removeAttribute("temp-customer");
                    request.getSession().removeAttribute("temp-verification");

                    response.sendRedirect("/sign-in");
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
