<%--
  Created by IntelliJ IDEA.
  User: 20133
  Date: 12/3/2022
  Time: 12:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Trang chủ</title>
  <link rel="stylesheet" href="assets/styles/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="assets/styles/codicons/codicon.css">
  <link rel="stylesheet" href="assets/styles/codicons/codicon.ttf">

  <script src="assets/scripts/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-md sticky-top shadow bg-light">
  <div class=" container-fluid">
    <a class="navbar-brand" href="#">[LOGO]</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="/page-employee">Ngân Hàng Con Mèo</a></li>
        <li class="nav-item"><a class="nav-link" href="/transaction_emp">Chuyển Khoản</a></li>
        <li class="nav-item"><a class="nav-link" href="/withdrawal">Rút Tiền</a></li>
        <li class="nav-item"><a class="nav-link" href="/savings-emp">Gửi Tiết Kiệm</a></li>
      </ul>
      <div class="d-md-flex justify-content-md-end flex-grow-1">
        <div class="d-md-inline d-none dropdown-center">
          <div class="codicon codicon-account fs-2 d-none d-md-block" data-bs-toggle="dropdown"></div>
          <ul class="dropdown-menu dropdown-menu-end">
            <li><a href="/sign-out-emp" class="dropdown-item">Đăng xuất</a></li>
          </ul>
        </div>
        <div class="d-md-none d-inline">
          <ul class="navbar-nav">
            <li class="nav-item"><a href="/sign-out-emp" class="nav-link">Đăng xuất</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</nav>
</body>
</html>
