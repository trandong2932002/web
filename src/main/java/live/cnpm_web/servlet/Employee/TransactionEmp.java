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

@WebServlet(name = "transaction_emp", value = "/transaction_emp")
public class TransactionEmp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("username-emp") == null){
            resp.sendRedirect("/page-employee");
        }
        else {
            String url = "/WEB-INF/employee/transaction/tran-identify.jsp";
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("cancel")){
            resp.sendRedirect("/page-employee");
        } else if (action.equals("tran-identify")) {
            String url = "/WEB-INF/employee/transaction/tran-info-customer.jsp";
            String identify = req.getParameter("identify");
            Customer customer = AccountDB.selectBySSN(identify);
            TransactionAccount transactionAccount = customer.getTransactionAccount();
            if (customer == null){
                resp.sendRedirect("/transaction_emp");
            }
            else {
                DecimalFormat df = new DecimalFormat("#,###.##");
                req.setAttribute("account", customer);
                req.setAttribute("balance",df.format((Double) transactionAccount.getBalance()) );
                getServletContext().getRequestDispatcher(url)
                        .forward(req, resp);
            }
        }
        else if (action.equals("tran-info-customer")) {
            String url = "/WEB-INF/employee/transaction/tran-transfer.jsp";
            String account = req.getParameter("account");
            String balance = req.getParameter("balance");

            req.setAttribute("account", account);
            req.setAttribute("balance", balance);
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        } else if (action.equals("tran-transfer")) {
            Transfer transfer = new Transfer();

            String account_src = req.getParameter("account");
            String balance = req.getParameter("balance");
            String account_dist = req.getParameter("account-dist");
            String value = req.getParameter("select_money");
            Double amount = Double.valueOf(0);
            String content = req.getParameter("content");

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

            Double fee = amount*0.01;
            Date now = new Date();

            Customer customer_src = AccountDB.selectByPhoneNumber(account_src);
            Customer customer_dest = AccountDB.selectByPhoneNumber(account_dist);

            TransactionAccount transactionAccount_src = customer_src.getTransactionAccount();
            TransactionAccount transactionAccount_dest = customer_dest.getTransactionAccount();

            Double balance_src = transactionAccount_src.getBalance();
            Double balance_dist = transactionAccount_dest.getBalance();

            balance_src = balance_src - amount - fee;
            balance_dist = balance_dist + amount;

            transactionAccount_src.setBalance(balance_src);
            transactionAccount_dest.setBalance(balance_dist);

            TransactionDB.update(transactionAccount_src);
            TransactionDB.update(transactionAccount_dest);

            transfer.setName("Chuyển khoản");
            transfer.setCreatedTime(LocalDateTime.now());
            transfer.setAmount(amount);
            transfer.setContent(content);
            transfer.setStatus(Transfer.TransferStatus.SUCCESS);
            transfer.setTransactionAccountSource(transactionAccount_src);
            transfer.setTransactionAccountDestination(transactionAccount_dest);
            transfer.setContent("Transfer");
            TransactionDB.insert(transfer);

            resp.sendRedirect("/transaction_emp");
        }
    }
}
