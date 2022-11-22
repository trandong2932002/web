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

  <section id="change-password">
    <div class="d-sm-flex justify-content-sm-center align-items-sm-center">
      <div class="m-sm-5 p-5 shadow">

        <div class="mb-3">
          <span class="h3">Đổi mật khẩu</span>
        </div>

        <form action="" method="post">

          <div class="mb-2">
            <label for="old-password" class="form-label">Mật khẩu cũ</label>
            <div class="input-group">
              <input type="password" name="old-password" id="" class="form-control">
              <span class="input-group-text"><i class="codicon codicon-eye"></i></span>
            </div>
          </div>

          <div class="mb-2">
            <label for="new-password" class="form-label">Mật khẩu mới</label>
            <div class="input-group">
              <input type="password" name="new_password" id="" class="form-control">
              <span class="input-group-text"><i class="codicon codicon-eye"></i></span>
            </div>
          </div>

          <div class="mb-2">
            <label for="retype-new-password" class="form-label">Nhập lại mật khẩu</label>
            <div class="input-group">
              <input type="password" name="retype-new-password" id="" class="form-control">
              <span class="input-group-text"><i class="codicon codicon-eye"></i></span>
            </div>
          </div>

          <div class="d-flex justify-content-sm-end justify-content-center mt-3">
            <button class="btn btn-primary me-2" formaction="/">Hủy</button>
            <button class="btn btn-primary" formaction="/change-password">Sửa</button>
          </div>
        </form>

      </div>
    </div>
  </section>

</body>
</html>