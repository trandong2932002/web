<!doctype html>
<html lang="en">
<head>
  <title>Thanh toán hóa đơn</title>

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

      <form action="" method="post" onsubmit="">
        <div id="source">
          <p class="h5 mb-3">Bên thanh toán</p>

          <div class="d-flex justify-content-between">
            <p>Số tài khoản thanh toán</p>
            <p>123</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Số dư khả dụng</p>
            <p>100200300</p>
          </div>
        </div>

        <hr>

        <div id="destination">
          <p class="h5 mb-3">Bên cung cấp dịch vụ</p>

          <div class="d-flex justify-content-between">
            <p>Loại dịch vụ</p>
            <p>Điện</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Nhà cung cấp</p>
            <p>Điện lực TPHCM</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Mã khách hàng</p>
            <p>123312</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Số tiền</p>
            <p>1000000</p>
          </div>

          <div class="d-flex justify-content-between">
            <p>Nội dung</p>
            <p class="text-end text-wrap" style="width: 20rem;">Lorem ipsum dolor sit, amet consectetur adipisicing
              elit. Aliquam, distinctio.</p>
          </div>
        </div>

        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <button class="btn btn-primary me-2" formaction="/">Hủy</button>
          <button class="btn btn-primary" formaction="/payment">Tiếp tục</button>
        </div>
      </form>

    </div>
  </div>
</section>

</body>
</html>