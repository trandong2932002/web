<!doctype html>
<html lang="en">
<head>
  <title>Document</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

  <section id="sign-in" class="bg-light">
    <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
      <div class="m-sm-5 p-5 shadow">
  
        <div class="mb-2">
          <span>[LOGO]</span>
          <span>CONMEO</span>
        </div>
  
        <div class="mb-3">
          <span class="h3">Đăng nhập</span>
        </div>
  
        <form action="" method="post">
          <div class="mb-2">
            <label for="username" class="form-label">Tên tài khoản</label>
            <input type="text" name="" id="" class="form-control">
          </div>
          <div class="mb-2">
            <label for="password" class="form-label">Mật khẩu</label>
            <input type="password" name="password" id="" class="form-control">
          </div>
          <div class="d-flex justify-content-sm-end justify-content-center mt-3">
            <button class="btn btn-primary me-2" formaction="/">Hủy</button>
            <button class="btn btn-primary" formaction="/sign-in">Đăng nhập</button>
          </div>
        </form>
  
      </div>
    </div>
  </section>

</body>
</html>