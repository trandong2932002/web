<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
  <title>Quản Lý Nhân Viên</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

<%@include file="dependencies/nav.jsp" %>

<section id="transaction-history">
  <div class="mx-5 my-4">

    <c:choose>
    <c:when test="${empty employeeList}">
      <div class="container-fluid">
        <div class="row flex-nowrap overflow-auto">
          <p>Không có nhân viên</p>
        </div>
      </div>
    </c:when>
    <c:otherwise>

    <table class="table" id="transaction-history-table">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Email</th>
        <th scope="col">Họ và tên lót</th>
        <th scope="col">Tên</th>
        <th scope="col">Số điện thoại</th>
        <th scope="col">CCCD</th>
      </tr>
      </thead>
      <tbody>

      <c:forEach items="${employeeList}" var="employee" varStatus="loop">
        <tr>
          <th scope="row">${loop.index}</th>
          <td>${employee.email}</td>
          <td>${employee.lastname}</td>
          <td>${employee.firstname}</td>
          <td>${employee.phoneNumber}</td>
          <td>${employee.ssn}</td>
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