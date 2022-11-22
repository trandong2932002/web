<!doctype html>
<html lang="en">
<head>
  <title>Document</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

  <section id="sign-up-1" class="bg-light">
    <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
      <div class="m-sm-5 p-5 shadow">

        <div class="mb-2">
          <span>[LOGO]</span>
          <span>CONMEO</span>
        </div>

        <div class="mb-3">
          <span class="h3">Đăng ký</span>
        </div>

        <form action="" method="post">
          
          <input type="hidden" name="action" value="basic-information">

          <div class="mb-2">
            <div class="row">
              <div class="col-sm-6 mb-sm-0 mb-3">
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
                <div class="col-sm-6 mb-sm-0 mb-3">
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

          <div class="d-flex justify-content-sm-end justify-content-center mt-3">
            <button class="btn btn-primary me-2" formaction="/">Hủy</button>
            <button class="btn btn-primary" formaction="/sign-up">Tiếp theo</button>
          </div>
        </form>

      </div>
    </div>
  </section>

</body>
</html>