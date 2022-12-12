<%--
  Created by IntelliJ IDEA.
  User: 20133
  Date: 12/1/2022
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Gửi Tiết Kiệm</title>
  <link rel="stylesheet" href="assets/styles/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="assets/styles/codicons/codicon.css">
  <link rel="stylesheet" href="assets/styles/codicons/codicon.ttf">

  <script src="assets/scripts/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<section id="sign-in" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-2">
        <span>[LOGO]</span>
        <span>CONMEO</span>
      </div>

      <div class="mb-3">
        <span class="h3">Điền Thông Tin</span>
      </div>

      <form action="savings-emp" method="post">
        <div class="mb-2">
          <label for="identify" class="form-label">Nhập Mã Căn Cước(CMND)</label><br>
          <input type="text" name="identify" id="" class="form-control">
        </div>
        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <button class="btn btn-primary me-2" name="action" value="cancel">Hủy</button>
          <button class="btn btn-primary" name="action" value="wd-identify">Tiếp Tục</button>
        </div>
      </form>
    </div>
  </div>
</section>
</body>
</html>
