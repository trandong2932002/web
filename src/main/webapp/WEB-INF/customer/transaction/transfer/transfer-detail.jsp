<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
  <title>Chuyển Khoản</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

  <script src="${pageContext.request.contextPath}/assets/scripts/another/otp-input.js" defer></script>


</head>
<body class="bg-light">

<%@include file="../../dependencies/nav.jsp" %>

<section id="transfer" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Xác nhận</span>
      </div>

      <form action="" method="post" onsubmit="">

        <input type="hidden" name="action" value="detail">

        <div id="source">
          <p class="h5 mb-3">Bên chuyển</p>

          <div class="d-flex justify-content-between">
            <p>Số tài khoản chuyển</p>
            <p>${src.accountNumber}</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Số dư khả dụng</p>
            <p><fmt:formatNumber value="${src.balance}" maxFractionDigits="0"/></p>
          </div>
        </div>

        <hr>

        <div id="destination">
          <p class="h5 mb-3">Bên nhận</p>

          <div class="d-flex justify-content-between">
            <p>Ngân hàng</p>
            <p>CON MEO</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Số tài khoản nhận</p>
            <p>${destNumber}</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Số tiền</p>
            <p>${amount}</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Nội dung</p>
            <p class="text-end text-wrap" style="width: 20rem;">${content}</p>
          </div>
        </div>

        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <button class="btn btn-secondary me-2" formaction="/">Hủy</button>
          <button type="button" id="create-otp" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#otp-static">Tiếp tục</button>
        </div>
      </form>

    </div>
  </div>
</section>

<%--modal--%>
<section>
  <%@include file="/WEB-INF/dependencies/otp-input.jsp" %>
</section>

</body>
</html>