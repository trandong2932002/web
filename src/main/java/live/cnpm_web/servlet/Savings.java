package live.cnpm_web.servlet;

import live.cnpm_web.data.transaction.TransactionDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Savings", value = "/savings")
public class Savings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String action = request.getParameter("action");

        if (action == null) {
            url = "/WEB-INF/customer/savings/savings-page.jsp";
        } else if (action.equals("create")) {
            url = "/WEB-INF/customer/savings/savings-create.jsp";
        } else if (action.equals("information")) {
            String id = request.getParameter("id");

            // get savings (0 or more) by id and account id
//            TransactionDB.selectAllByTransactionAccountId()

            // set parameters

            url = "/WEB-INF/customer/savings/savings-information.jsp";
        }



        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String action = request.getParameter("action");
    }
}
