<!doctype html>
<html lang="en">
<head>
  <title>Gửi Tiết Kiệm</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

  <script src="${pageContext.request.contextPath}/assets/scripts/another/customer/savings/savings-create.js" defer></script>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="create-savings" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Tạo khoản gửi tiết kiệm</span>
      </div>

      <form action="" method="post" onsubmit="return validate()">

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
              <input type="text" name="available-amount" id="available-amount"" class=" form-control text-success"
              disabled value="123456789">
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
          <label for="renew" class="form-label">Tự động tái tục</label>
          <select name="renew" id="renew" class="form-select">
            <option value="1" selected>Không</option>
            <option value="2">Tái tục gốc</option>
            <option value="3">Tái tục gốc và lãi</option>
          </select>
        </div>

        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <input type="hidden" name="action" value="">
          <button class="btn btn-primary me-2" formaction="/savings">Hủy</button>
          <button class="btn btn-primary" formaction="/savings">Tạo</button>
        </div>
      </form>

    </div>
  </div>
</section>

</body>
</html>