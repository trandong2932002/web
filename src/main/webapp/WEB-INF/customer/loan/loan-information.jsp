<!doctype html>
<html lang="en">
<head>
  <title>Tạo Khoản Vay</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

  <script src="${pageContext.request.contextPath}/assets/scripts/another/customer/savings/savings-create.js"
          defer></script>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="create-loan" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Thông tin khoản vay</span>
      </div>

      <form action="" method="post">

        <div class="mb-2">
          <label for="loan-name" class="form-label">Tên khoản vay</label>
          <input type="text" name="loan-name" id="loan-name" class="form-control" disabled>
        </div>

        <div class="mb-2">
          <label for="loan-purpose" class="form-label">Mục đích vay</label>
          <input type="text" name="loan-purpose" id="loan-purpose" class="form-control" disabled>
        </div>

        <div class="mb-2">
          <div class="row">
            <div class="col-sm-6 mb-sm-0 mb-3">
              <label for="loan-total-amount" class="form-label">Tổng số vốn thực hiện</label>
              <input type="text" name="loan-total-amount" id="loan-total-amount" class="form-control" disabled>
            </div>

            <div class="col-sm-6">
              <label for="loan-amount" class="form-label">Đề nghị ngân hàng cho vay</label>
              <input type="text" name="loan-amount" id="loan-amount" class="form-control" disabled>
            </div>
          </div>
        </div>

        <div class="mb-2">
          <div class="row">
            <div class="col-sm-6 mb-sm-0 mb-3">
              <label for="amount" class="form-label">Vốn tự có</label>
              <input type="text" name="amount" id="amount" class="form-control" disabled>
            </div>

            <div class="col-sm-6">
              <label for="available-amount" class="form-label">Số dư khả dụng</label>
              <input type="text" name="available-amount" id="available-amount" class="form-control" disabled>
            </div>
          </div>
        </div>

        <div class="mb-2">
          <label for="period" class="form-label">Thời hạn vay</label>
          <input type="text" name="loan-purpose" id="period" class="form-control" disabled>
        </div>

        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <button class="btn btn-primary" formaction="/loan">Trờ về</button>
        </div>

      </form>

    </div>
  </div>
</section>

</body>
</html>