package live.cnpm_web.servlet;

import live.cnpm_web.data.account.ActivityDB;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.transaction.BaseTransaction;
import live.cnpm_web.entity.transaction.Transfer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TransactionHistory", value = "/transaction-history")
public class TransactionHistory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/customer/transaction-history/transaction-history.jsp";

        TransactionAccount transactionAccount = ((Customer) ActivityDB.selectBySessionId(request.getSession().getId()).getAccount()).getTransactionAccount();
        List<BaseTransaction> transactionList = TransactionDB.selectAllByTransactionAccount(transactionAccount, BaseTransaction.class);

        request.setAttribute("transactionList", transactionList);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
