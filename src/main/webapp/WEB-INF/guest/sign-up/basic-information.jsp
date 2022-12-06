<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
  <title>Đăng Ký</title>

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

        <c:if test="${not empty message}">
          <div class="alert alert-danger" role="alert">
              ${message}
          </div>
        </c:if>

        <form action="" method="post">
          
          <input type="hidden" name="action" value="basic-information">

          <div class="mb-2">
            <div class="row">
              <div class="col-sm-6 mb-sm-0 mb-3">
                <label for="lastname" class="form-label">Họ và tên lót</label>
                <input type="text" name="lastname" id="lastname" class="form-control" value="${lastname}">
              </div>

              <div class="col-sm-6">
                <label for="firstname" class="form-label">Tên</label>
                <input type="text" name="firstname" id="firstname" class="form-control" value="${firstname}">
              </div>
            </div>
          </div>

          <div class="mb-2">
            <label for="dob" class="form-label">Ngày sinh</label>
            <input type="date" name="dob" id="dob" class="form-control" value="${dob}">
          </div>

          <div class="mb-2">
            <label for="address" class="form-label">Địa chỉ</label>
            <div class="container p-0">
              <div class="row mb-2">
                <div class="">
                  <input type="text" name="address" id="address" class="form-control" value="${address}">
                </div>
              </div>

              <div class="row">
                <div class="col-sm-6 mb-sm-0 mb-3">
                  <label for="district" class="form-label">Quận/Huyện</label>
                  <input type="text" name="district" id="district" class="form-control" value="${district}">
                </div>
                <div class="col-sm-6">
                  <label for="city-province" class="form-label">Thành phố/Tỉnh</label>
                  <input type="text" name="city-province" id="city-province" class="form-control" value="${city}">
                </div>
              </div>
            </div>
          </div>

          <div class="d-flex justify-content-sm-end justify-content-center mt-3">
            <button class="btn btn-secondary me-2" formaction="/">Hủy</button>
            <button class="btn btn-primary" formaction="/sign-up">Tiếp theo</button>
          </div>
        </form>

      </div>
    </div>
  </section>

</body>
</html>