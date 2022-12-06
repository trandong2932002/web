<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
  <title>Đổi mật khẩu</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

  <%@include file="../dependencies/nav.jsp"%>

  <section id="change-password">
    <div class="d-sm-flex justify-content-sm-center align-items-sm-center">
      <div class="m-sm-5 p-5 shadow">

        <div class="mb-3">
          <span class="h3">Đổi mật khẩu</span>
        </div>

        <c:if test="${not empty message}">
          <c:if test="${status}">
            <div class="alert alert-success" role="alert">
                ${message}
            </div>
          </c:if>
          <c:if test="${not status}">
            <div class="alert alert-danger" role="alert">
                ${message}
            </div>
          </c:if>
        </c:if>

        <form action="" method="post">

          <div class="mb-2">
            <label for="old-password" class="form-label">Mật khẩu cũ</label>
            <div class="input-group">
              <input type="password" name="old-password" id="old-password" class="form-control">
              <span class="input-group-text"><i class="codicon codicon-eye"></i></span>
            </div>
          </div>

          <div class="mb-2">
            <label for="new-password" class="form-label">Mật khẩu mới</label>
            <div class="input-group">
              <input type="password" name="new-password" id="new-password" class="form-control">
              <span class="input-group-text"><i class="codicon codicon-eye"></i></span>
            </div>
          </div>

          <div class="mb-2">
            <label for="retype-new-password" class="form-label">Nhập lại mật khẩu</label>
            <div class="input-group">
              <input type="password" name="retype-new-password" id="retype-new-password" class="form-control">
              <span class="input-group-text"><i class="codicon codicon-eye"></i></span>
            </div>
          </div>

          <div class="d-flex justify-content-sm-end justify-content-center mt-3">
            <button class="btn btn-secondary me-2" formaction="/">Hủy</button>
            <button class="btn btn-primary" formaction="/change-password">Sửa</button>
          </div>
        </form>

      </div>
    </div>
  </section>

</body>
</html>