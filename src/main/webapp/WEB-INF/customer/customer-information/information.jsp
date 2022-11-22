<!doctype html>
<html lang="en">
<head>
  <title>Document</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

  <%@include file="dependencies/nav.jsp"%>

  <section id="customer-information">
    <div class="d-sm-flex justify-content-sm-center align-items-sm-center">
      <div class="m-sm-5 p-5 shadow">

        <div class="mb-3">
          <span class="h3">Thông tin cá nhân</span>
        </div>

        <form action="" method="post">

          <div class="container p-0 mb-2">
            <div class="row">
              <div class="col-sm-6">
                <label for="lastname" class="form-label">Họ và tên lót</label>
                <input type="text" name="lastname" id="" class="form-control">
              </div>

              <div class="col-sm-6">
                <label for="firstname" class="form-label">Tên</label>
                <input type="text" name="firstname" id="" class="form-control">
              </div>
            </div>
          </div>

          <div class="mb-2">
            <label for="dob" class="form-label">Ngày sinh</label>
            <input type="date" name="dob" id="" class="form-control">
          </div>

          <div class="mb-2">
            <label for="username" class="form-label">Địa chỉ</label>
            <div class="container p-0">
              <div class="row mb-2">
                <div class="">
                  <input type="text" name="address" id="" class="form-control">
                </div>
              </div>

              <div class="row">
                <div class="col-sm-6">
                  <label for="district" class="form-label">Quận/Huyện</label>
                  <input type="text" name="district" id="" class="form-control">
                </div>
                <div class="col-sm-6">
                  <label for="city-province" class="form-label">Thành phố/Tỉnh</label>
                  <input type="text" name="city-province" id="" class="form-control">
                </div>
              </div>
            </div>
          </div>

          <div class="container p-0 mb-2">
            <div class="row">
              <div class="col-sm-8">
                <label for="email" class="form-label">Email</label>
                <input type="email" name="email" id="" class="form-control">
              </div>

              <div class="col-sm-4">
                <label for="phone-number" class="form-label">Số điện thoại</label>
                <input type="number" name="phone-number" id="" class="form-control">
              </div>
            </div>
          </div>

          <div class="mb-2">
            <label for="username" class="form-label">Tài khoản</label>
            <input type="username" name="username" id="" class="form-control">
          </div>

          <div class="d-flex justify-content-sm-end justify-content-center mt-3">
            <button class="btn btn-primary me-2" formaction="/">Hủy</button>
            <button class="btn btn-primary" formaction="/change-information">Sửa</button>
          </div>
        </form>

      </div>
    </div>
  </section>

</body>
</html>