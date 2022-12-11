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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "savings", value = "/savings-emp")
public class Savings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("username-emp") == null){
            resp.sendRedirect("/page-employee");
        }
        else {
            String url = "/WEB-INF/employee/deposit/deposit.jsp";
            List<live.cnpm_web.entity.transaction.savings.Savings> list_savings = TransactionDB.selectAll(live.cnpm_web.entity.transaction.savings.Savings.class);
            req.setAttribute("list_savings", list_savings);
            System.out.println(list_savings);
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("cancel")){
            resp.sendRedirect("/page-employee");
        } else if (action.equals("create-report")) {
            String url = "/WEB-INF/employee/deposit/deposit-identify.jsp";
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        } else if (action.equals("deposit-identify")) {
            String url = "/WEB-INF/employee/deposit/deposit-info-customer.jsp";
            String identify = req.getParameter("identify");
            Customer customer = AccountDB.selectBySSN(identify);
            if(customer == null)
            {
                resp.sendRedirect("/savings-emp");
            }
            else {
                TransactionAccount transactionAccount = customer.getTransactionAccount();
                DecimalFormat df = new DecimalFormat("####.##");
                Double tmp = Double.valueOf(transactionAccount.getBalance());
                req.setAttribute("account", customer);
                req.setAttribute("tran_acc", transactionAccount);
                req.setAttribute("balance", df.format(tmp));
                getServletContext().getRequestDispatcher(url)
                        .forward(req, resp);
            }
        }
        else if (action.equals("deposit-info-customer")) {
            String url = "/WEB-INF/employee/deposit/deposit-create-request.jsp";
            String balance = req.getParameter("balance");
            String account = req.getParameter("account");
            Customer customer = AccountDB.selectByPhoneNumber(account);
            TransactionAccount transactionAccount = customer.getTransactionAccount();
            req.setAttribute("balance", balance);
            req.setAttribute("tran_acc", transactionAccount);
            req.setAttribute("account", account);
            getServletContext().getRequestDispatcher(url)
                    .forward(req, resp);
        } else if (action.equals("deposit-create-request")) {

            String savings_name = req.getParameter("savings-name");
            String amount = req.getParameter("amount");
            String account = req.getParameter("account");
            String term = req.getParameter("term");
            String interest = req.getParameter("interest-rate");
            String create_date = req.getParameter("create-date");
            String maturity_date = req.getParameter("maturity-date");
            String renew = req.getParameter("renew");

            System.out.println(term);

            if(interest.equals("5%"))
            {
                interest = "0.05";
            } else if (interest.equals("6%")) {
                interest = "0.06";
            } else if (interest.equals("7%")) {
                interest = "0.07";
            }

            Customer customer = AccountDB.selectByPhoneNumber(account);
            TransactionAccount transactionAccount_src = customer.getTransactionAccount();

            live.cnpm_web.entity.transaction.savings.Savings savings = new live.cnpm_web.entity.transaction.savings.Savings();
            savings.setAmount(Double.valueOf(amount));
            savings.setCreatedTime(LocalDateTime.now());
            savings.setName(savings_name);
            savings.setStatus(live.cnpm_web.entity.transaction.savings.Savings.SavingsStatus.INPROGRESS);
            savings.setTransactionAccountSource(transactionAccount_src);
            savings.setCreatedDate(LocalDate.parse(create_date));
            savings.setEndTime(LocalDateTime.parse(maturity_date +"T00:00:00"));// end day
            savings.setInterest(Double.valueOf(interest));
            savings.setPenaltyInterest(Double.valueOf("0.08"));
            savings.setRolledOver(live.cnpm_web.entity.transaction.savings.Savings.RolledOver.NO);
            savings.setTerm(live.cnpm_web.entity.transaction.savings.Savings.Term.getValue(Integer.parseInt(term)));
            savings.setMaturityDate();

            // trừ tiền trong tài khoản
            transactionAccount_src.setBalance(Double.valueOf(transactionAccount_src.getBalance() - Double.valueOf(amount)));
            TransactionDB.update(transactionAccount_src);
            // tạo savings
            TransactionDB.insert(savings);
            System.out.println("Tạo sổ thành công!");

            resp.sendRedirect("/savings-emp");
        } else if (action.equals("withdraw-deposit")) {
            String url = "/WEB-INF/employee/deposit/withdraw-deposit/wd-identify.jsp";
            getServletContext().getRequestDispatcher(url).forward(req,resp);
        } else if (action.equals("wd-identify")) {
            String identify = req.getParameter("identify");
            Customer customer = AccountDB.selectBySSN(identify);
            if(customer == null){
                resp.sendRedirect("/savings-emp");
            }
            else {
                List<live.cnpm_web.entity.transaction.savings.Savings> list_savings = TransactionDB.selectAllByTransactionAccount(customer.getTransactionAccount(), live.cnpm_web.entity.transaction.savings.Savings.class);
                req.setAttribute("list_savings", list_savings);
                req.setAttribute("account", customer);
                System.out.println(list_savings.toString());
                String url = "/WEB-INF/employee/deposit/withdraw-deposit/wd-detail-customer.jsp";
                getServletContext().getRequestDispatcher(url).forward(req,resp);
            }
        } else if (action.equals("withdraw-savings")) {
            String savings_id = req.getParameter("savings-id");
            String phone_number = req.getParameter("phone-number");

            live.cnpm_web.entity.transaction.savings.Savings savings = TransactionDB.selectById(Long.valueOf(savings_id), live.cnpm_web.entity.transaction.savings.Savings.class);
            Customer customer = AccountDB.selectById(Long.valueOf(savings.getTransactionAccountSource().getId()), Customer.class);
            TransactionAccount transactionAccount = customer.getTransactionAccount();

            LocalDateTime now = LocalDateTime.now();
            if(savings.getEndTime().isAfter(now)){
                savings.setInterest(savings.getInterest() - savings.getPenaltyInterest());

                //Cập nhật tài khoản
                transactionAccount.setBalance(transactionAccount.getBalance() + savings.getAmount()*(1+savings.getInterest()));
                TransactionDB.update(transactionAccount);

                //Tạo 1 transfer
                Transfer transfer = new Transfer();
                transfer.setCreatedTime(LocalDateTime.now());
                transfer.setName("Withdraw Savings");
                transfer.setTransactionAccountDestination(transactionAccount);
                transfer.setStatus(Transfer.TransferStatus.SUCCESS);
                transfer.setAmount(savings.getAmount()*savings.getInterest());
                transfer.setContent("Rut So Tiet Kiem");
                TransactionDB.insert(transfer);

                //Xóa sổ tiết kiệm
                TransactionDB.delete(savings);
            }
            else {
                //Cập nhật tài khoản
                transactionAccount.setBalance(transactionAccount.getBalance() + savings.getAmount()*savings.getInterest());
                TransactionDB.update(transactionAccount);

                //Tạo 1 transfer
                Transfer transfer = new Transfer();
                transfer.setCreatedTime(LocalDateTime.now());
                transfer.setName("Withdraw Savings");
                transfer.setTransactionAccountDestination(transactionAccount);
                transfer.setStatus(Transfer.TransferStatus.SUCCESS);
                transfer.setAmount(savings.getAmount()*savings.getInterest());
                transfer.setContent("Rut So Tiet Kiem");
                TransactionDB.insert(transfer);

                //Xóa sổ tiết kiệm
                TransactionDB.delete(savings);
            }
            resp.sendRedirect("/savings-emp");
        }
    }
}
