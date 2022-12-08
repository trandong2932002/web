<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
  <title>Lịch Sử Giao Dịch</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="transaction-history-table">
  <div class="mx-5 my-4">
    <table class="table">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Loại giao dịch</th>
        <th scope="col">Ngày giao dịch</th>
        <th scope="col">Tài khoản nguồn</th>
        <th scope="col">Tài khoản đích</th>
        <th scope="col">Số tiền</th>
        <th scope="col">Thông tin chi tiết</th>
      </tr>
      </thead>
      <tbody>

      <c:choose>
        <c:when test="${empty transactionList}">
          <div class="container-fluid">
            <div class="row flex-nowrap overflow-auto">
              <p>Bạn không có giao dịch nào</p>
            </div>
          </div>
        </c:when>

        <c:otherwise>
          <c:forEach items="${transactionList}" var="transaction" varStatus="loop">
            <tr>
              <th scope="row">${loop.index}</th>
              <td></td>
              <td>
                <fmt:parseDate value="${transaction.createdTime}" pattern="y-M-dd'T'H:m" var="parseCreatedTime"></fmt:parseDate>
                <fmt:formatDate value="${parseCreatedTime}" pattern="yyyy/MM/dd" />
              </td>
              <td>${transaction.transactionAccountSource.accountNumber}</td>
              <td>${transaction.transactionAccountDestination.accountNumber}</td>
              <td>${transaction.amount}</td>
              <td>${transaction.name}</td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
      </tbody>
    </table>
  </div>
</section>

</body>
</html>