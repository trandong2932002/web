package live.cnpm_web.servlet.Employee;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.transaction.Transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet(name = "withdrawal", value = "/withdrawal")
public class Withdrawal extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("username-emp") == null){
            resp.sendRedirect("/page-employee");
        }
        else {
            String url = "/WEB-INF/employee/withdrawal/withdrawal-identify.jsp";
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("cancel")){
            resp.sendRedirect("/page-employee");
        }
        else if (action.equals("continue")){
            String url = "/WEB-INF/employee/withdrawal/withdrawal-info-customer.jsp";
            String identify = req.getParameter("identify");

            Customer customer = AccountDB.selectBySSN(identify);
            TransactionAccount transactionAccount = customer.getTransactionAccount();
            if (customer == null){
                resp.sendRedirect("/withdrawal");
            }
            else {
                DecimalFormat df = new DecimalFormat("#,###.##");
                req.setAttribute("account", customer);
                req.setAttribute("balance",df.format((Double) transactionAccount.getBalance()) );
                getServletContext().getRequestDispatcher(url)
                        .forward(req, resp);
            }
        } else if (action.equals("withdrawal-info-customer")) {
            String url = "/WEB-INF/employee/withdrawal/withdrawal.jsp";

            String account = req.getParameter("account");
            String balance = req.getParameter("balance");
            req.setAttribute("account", account);
            req.setAttribute("balance", balance);

            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        } else if (action.equals("withdrawal")) {
            DecimalFormat df = new DecimalFormat("#,###.##");
            String account = req.getParameter("account");
            String value = req.getParameter("select_money");
            Double amount = Double.valueOf(0);
            if(value.equals("7")){
                amount = Double.valueOf(req.getParameter("differ-money"));
            } else if (value.equals("1")) {
                amount = Double.valueOf(100000);
            }else if (value.equals("2")) {
                amount = Double.valueOf(200000);
            }else if (value.equals("3")) {
                amount = Double.valueOf(500000);
            }else if (value.equals("4")) {
                amount = Double.valueOf(1000000);
            }else if (value.equals("5")) {
                amount = Double.valueOf(2000000);
            }else if (value.equals("6")) {
                amount = Double.valueOf(5000000);
            }
            Customer customer = AccountDB.selectByPhoneNumber(account);
            TransactionAccount transactionAccount = customer.getTransactionAccount();
            Double result = Double.valueOf(transactionAccount.getBalance()) - amount;
            transactionAccount.setBalance(result);

            TransactionDB.update(transactionAccount);
            Transfer transfer = new Transfer();

            Date now = new Date();
            transfer.setCreatedTime(LocalDateTime.now());
            transfer.setName("Rút tiền");
            transfer.setAmount(amount);
            transfer.setTransactionAccountSource(transactionAccount);

            TransactionDB.insert(transfer);

            resp.sendRedirect("/withdrawal");
        }
    }
}
