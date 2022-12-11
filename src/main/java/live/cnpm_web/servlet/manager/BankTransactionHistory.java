package live.cnpm_web.servlet.manager;

import com.google.gson.JsonObject;
import live.cnpm_web.data.transaction.TransactionDB;
import live.cnpm_web.entity.account.Activity;
import live.cnpm_web.entity.transaction.BaseTransaction;
import live.cnpm_web.entity.transaction.savings.Savings;
import live.cnpm_web.entity.transaction.Transfer;
import live.cnpm_web.util.XHRUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BankTransactionHistory", value = "/_manager/history")
public class BankTransactionHistory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";

        // check activity session
        Activity activity = (Activity) request.getSession().getAttribute("activity");
        String accountType = (String) request.getSession().getAttribute("accountType");
        if (activity == null || !accountType.equals("Manager")) {
            response.sendRedirect("/");
            return;
        } else {
            url = "/WEB-INF/manager/bank-transaction-history.jsp";
        }
        // end check

        List<BaseTransaction> transactionList = TransactionDB.selectAllByCondition(BaseTransaction.class, "-1", "0");

        request.setAttribute("transactionList", transactionList);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// XHR
        JsonObject json = XHRUtil.parseJSONObject(request);
        String type = json.get("type").getAsString();
        String period = json.get("period").getAsString();
        String amount = json.get("amount").getAsString();
        //
        // parse condition
        // type = {all,transfer,savings,loan}
        // period = {1,3,7} days
        // amount = {<10,10-100,100-1000,>1000}k
        //
        List<? extends BaseTransaction> transactionList = null;
        if (type.equals("0"))
            transactionList = TransactionDB.selectAllByCondition(BaseTransaction.class, period, amount);
        else if (type.equals("1"))
            transactionList = TransactionDB.selectAllByCondition(Transfer.class, period, amount);
        else if (type.equals("2"))
            transactionList = TransactionDB.selectAllByCondition(Savings.class, period, amount);
//        else if (type.equals("3"))
//            transactionList = TransactionDB.selectAllByCondition(transactionAccount, Loan.class, period, amount);

        String a = XHRUtil.getJSONString(transactionList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(a);
        out.flush();
    }
}
