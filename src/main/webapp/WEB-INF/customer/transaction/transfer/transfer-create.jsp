<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
  <title>Chuyển Khoản</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

<%@include file="../../dependencies/nav.jsp" %>

<section id="transfer" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Chuyển tiền</span>
      </div>

      <c:if test="${not empty message}">
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
      </c:if>

      <form action="" method="post" onsubmit="">

        <input type="hidden" name="action" value="create">

        <div id="source">
          <p class="h5 mb-3">Bên chuyển</p>
          <div class="mb-2">
            <div class="row">
              <div class="col-sm-6">
                <label for="srcNumber" class="form-label">Số tài khoản chuyển</label>
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
          <p class="h5 mb-3">Bên nhận</p>

          <div class="mb-2">
            <label for="bank" class="form-label">Ngân hàng</label>
            <select name="bank" id="bank" class="form-control">
              <option value="1">CON MEO</option>
            </select>
          </div>

          <div class="mb-2">
            <label for="destNumber" class="form-label">Số tài khoản nhận</label>
            <input type="text" name="destNumber" id="destNumber" class="form-control" value="${destNumber}">
          </div>

          <div class="mb-2">
            <div class="row">
              <div class="col-sm-6">
                <label for="amount" class="form-label">Số tiền</label>
                <input type="number" name="amount" id="amount" class="form-control" step="1000" min="0"
                       value="${amount}">
              </div>
              <div class="col-sm-6">
                <label for="limit-amount" class="form-label">Hạn mức</label>
                <input type="text" name="limit-amount" id="limit-amount" class="form-control" value="10000000" disabled>
              </div>


            </div>
          </div>

          <div class="mb-2">
            <label for="content" class="form-label">Nội dung chuyển</label>
            <textarea name="content" id="content" class="form-control">${content}</textarea>
          </div>
        </div>


        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <button class="btn btn-secondary me-2" formaction="/">Hủy</button>
          <button class="btn btn-primary" formaction="/transfer">Tiếp tục</button>
        </div>
      </form>

    </div>
  </div>
</section>

</body>
</html>