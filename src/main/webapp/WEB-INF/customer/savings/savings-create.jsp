<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html lang="en">
<head>
  <title>Gửi Tiết Kiệm</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

  <script src="${pageContext.request.contextPath}/assets/scripts/another/customer/savings/savings-create.js"
          defer></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/another/otp-input-savings.js" defer></script>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="savings-create" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Tạo khoản gửi tiết kiệm</span>
      </div>


      <div id="create-message">
      </div>

      <form action="" id="main-form" method="post">
        <%--        onsubmit="return validate()" conflict with xhr--%>

        <input type="hidden" id="action" name="action" value="create">

        <div class="mb-2">
          <label for="savings-name" class="form-label">Tên sổ tiết kiệm</label>
          <input type="text" name="savings-name" id="savings-name" class="form-control">
        </div>

        <div class="mb-2">
          <div class="row">
            <div class="col-sm-6 mb-sm-0 mb-3">
              <label for="amount" class="form-label">Số tiền</label>
              <input type="number" name="amount" id="amount" class="form-control" step="1000">
            </div>

            <div class="col-sm-6">
              <label for="available-amount" class="form-label">Số dư khả dụng</label>
              <input type="text" name="available-amount" id="available-amount" class=" form-control text-success"
                     disabled value="<fmt:formatNumber value="${src.balance}" maxFractionDigits="0"/>">
            </div>
          </div>
        </div>

        <div class="mb-2">
          <div class="row">
            <div class="col-sm-6 mb-sm-0 mb-3">
              <label for="term" class="form-label">Kỳ hạn</label>
              <select name="term" id="term" class="form-select"></select>
            </div>

            <div class="col-sm-6">
              <label for="interest-rate" class="form-label">Lãi suất</label>
              <input type="text" name="interest-rate" id="interest-rate" class="form-control" disabled>
            </div>
          </div>
        </div>

        <div class="mb-2">
          <div class="row">
            <div class="col-sm-6 mb-sm-0 mb-3">
              <label for="create-date" class="form-label">Ngày mở sổ</label>
              <input type="date" name="create-date" id="create-date" class="form-control" disabled>
            </div>

            <div class="col-sm-6">
              <label for="maturity-date" class="form-label">Ngày đáo hạn</label>
              <input type="date" name="maturity-date" id="maturity-date" class="form-control" disabled>
            </div>
          </div>
        </div>

        <div class="mb-2">
          <label for="rolled-over" class="form-label">Tự động tái tục</label>
          <select name="rolled-over" id="rolled-over" class="form-select">
            <option value="0" selected>Không</option>
            <option value="1">Tái tục gốc</option>
            <option value="2">Tái tục gốc và lãi</option>
          </select>
        </div>

        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <a class="btn btn-secondary me-2" href="/savings">Hủy</a>
          <button type="button" class="btn btn-primary" id="create-savings">Tạo</button>
          <button type="button" style="display: none" id="create-modal" data-bs-toggle="modal"
                  data-bs-target="#otp-static"/>
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