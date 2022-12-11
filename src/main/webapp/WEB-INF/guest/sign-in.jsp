<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
  <title>Đăng Nhập</title>

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

        <c:if test="${not empty message}">
          <div class="alert alert-danger" role="alert">
              ${message}
          </div>
        </c:if>

        <form id="main-form" method="post"1>

          <input type="hidden" name="action" value="sign-in">

          <div class="mb-2">
            <label for="phone-number" class="form-label">Số điện thoại</label>
            <input type="text" name="phone-number" id="phone-number" class="form-control">
          </div>
          <div class="mb-2">
            <label for="password" class="form-label">Mật khẩu</label>
            <input type="password" name="password" id="password" class="form-control">
          </div>
          <div class="d-flex justify-content-sm-end justify-content-center mt-3">
            <button class="btn btn-secondary me-2" formaction="/">Hủy</button>
            <button class="btn btn-primary me-2" formaction="/sign-in">Đăng nhập</button>
          </div>
        </form>

      </div>
    </div>
  </section>

</body>
</html>