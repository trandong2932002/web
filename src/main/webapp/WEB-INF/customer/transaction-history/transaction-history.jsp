<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
  <title>Lịch Sử Giao Dịch</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>
  <script src="${pageContext.request.contextPath}/assets/scripts/popper/js/popper.min.js"></script>
  <script
      src="${pageContext.request.contextPath}/assets/scripts/another/customer/transaction-history/transaction-history.js"
      defer></script>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="transaction-history">
  <div class="mx-5 my-4">
    <table class="table" id="transaction-history-table">
      <thead>
      <tr>
        <form id="main-form" method="post">
          <th scope="col">
            <button class="btn btn-primary" id="get-transaction" type="button">Lọc</button>
          </th>
          <th scope="col">
            <select name="type" id="type" class="form-control">
              <option value="0">Tất cả</option>
              <option value="1">Chuyển tiền</option>
              <option value="2">Gửi tiết kiệm</option>
              <option value="3">Vay</option>
            </select>
          </th>
          <th scope="col">
            <select name="period" id="period" class="form-control">
              <option value="0">1 ngày</option>
              <option value="1">3 ngày</option>
              <option value="2">7 ngày</option>
            </select>
          </th>
          <th scope="col">
            <select name="time" id="time" class="form-control" disabled>
              <option value="1">Tất cả</option>
            </select>
          </th>
          <th scope="col">
            <select name="dest" id="dest" class="form-control" disabled>
              <option value="1">Tất cả</option>
            </select>
          </th>
          <th scope="col">
            <select name="amount" id="amount" class="form-control">
              <option value="0">Tất cả</option>
              <option value="1"><10,000</option>
              <option value="2">10,000-100,000</option>
              <option value="3">100,000-1,000,000</option>
              <option value="4">>1,000,000</option>
            </select>
          </th>
          <th scope="col">
            <select name="information" id="information" class="form-control" disabled>
              <option value="1">Tất cả</option>
            </select>
          </th>
        </form>
      </tr>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Loại giao dịch</th>
        <th scope="col">Ngày giao dịch</th>
        <th scope="col">Thời gian</th>
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
              <td>
                <c:catch var="exception">
                  <c:set var="temp" value="${transaction.rolledOver}"/>
                </c:catch>
                <c:choose>
                  <c:when test="${transaction.name == 'transfer'}">Chuyển tiền</c:when>
                  <c:when test="${not empty exception}">Vay</c:when>
                  <c:otherwise>Tiết kiệm</c:otherwise>
                </c:choose>
              </td>
              <td>
                <fmt:parseDate value="${transaction.createdTime}" pattern="y-M-dd'T'H:m"
                               var="parseCreatedTime"></fmt:parseDate>
                <fmt:formatDate value="${parseCreatedTime}" pattern="yyyy-MM-dd"/>
              </td>
              <td>
                <fmt:parseDate value="${transaction.createdTime}" pattern="y-M-dd'T'H:m:s"
                               var="parseCreatedTime"></fmt:parseDate>
                <fmt:formatDate value="${parseCreatedTime}" pattern="HH:mm:ss"/>
              </td>
              <td>${transaction.transactionAccountDestination.accountNumber}</td>
              <td><fmt:formatNumber value="${transaction.amount}" maxFractionDigits="0"/></td>
              <td>
                <c:catch var="exception">
                  <c:set var="temp" value="${transaction.content}"/>
                </c:catch>
                <c:choose>
                  <c:when test="${empty exception}">${transaction.content}</c:when>
                  <c:otherwise>${transaction.name}</c:otherwise>
                </c:choose>
              </td>
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