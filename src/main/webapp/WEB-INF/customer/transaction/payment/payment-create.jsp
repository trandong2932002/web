<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
  <title>Thanh Toán Hóa Đơn</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

<%@include file="../../dependencies/nav.jsp" %>

<section id="payment" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Thanh toán hóa đơn</span>
      </div>

      <c:if test="${not empty message}">
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
      </c:if>

      <form action="" method="post" onsubmit="">

        <input type="hidden" name="action" value="create">

        <div id="source">
          <p class="h5 mb-3">Bên thanh toán</p>


          <div class="mb-2">
            <div class="row">
              <div class="col-sm-6">
                <label for="srcNumber" class="form-label">Số tài khoản thanh toán</label>
                <input type="text" name="srcNumber" class="form-control" id="srcNumber" value="${src.accountNumber}" disabled/>
              </div>

              <div class="col-sm-6">
                <label for="available-amount" class="form-label">Số dư khả dụng</label>
                <input type="text" name="available-amount" id="available-amount" class="form-control text-success" value="<fmt:formatNumber value="${src.balance}" maxFractionDigits="0"/>" disabled/>
              </div>

            </div>
          </div>
        </div>

        <hr>

        <div id="destination">
          <p class="h5 mb-3">Bên cung cấp dịch vụ</p>

          <div class="mb-2">
            <label for="service-provider" class="form-label">Loại dịch vụ</label>
            <select name="service-provider" id="service-provider" class="form-control">
              <c:forEach items="${providerList}" var="provider" varStatus="loop">
                <option value="${provider.id}">${provider.name}</option>
              </c:forEach>
            </select>
          </div>

          <div class="mb-2">
            <label for="customer-code" class="form-label">Mã khách hàng</label>
            <input type="text" name="customer-code" id="customer-code" class="form-control" value="${customerCode}">
          </div>
        </div>


        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <button class="btn btn-secondary me-2" formaction="/">Hủy</button>
          <button class="btn btn-primary" formaction="/payment">Tiếp tục</button>
        </div>
      </form>

    </div>
  </div>
</section>

</body>
</html>