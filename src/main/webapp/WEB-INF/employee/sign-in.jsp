<%--
  Created by IntelliJ IDEA.
  User: 20133
  Date: 11/23/2022
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>

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
                <span class="h3">Đăng nhập</span>
            </div>

            <form action="sign-in-emp" method="post">
                <input name="action" type="hidden" value="signin">
                <div class="mb-2">
                    <label name="username" class="form-label">Tên tài khoản</label>
                    <input type="text" name="username" id=" " class="form-control">
                </div>
                <div class="mb-2">
                    <label name="password" class="form-label">Mật khẩu</label>
                    <input type="password" name="password" id="" class="form-control">
                </div>
                <div class="d-grid gap-2">
                    <button class="btn btn-primary" type="submit">Đăng nhập</button>
                </div>
            </form>
            <form>
                <div class="d-grid gap-2">
                    <input name="action" value="cancel" type="hidden">
                    <button class="btn btn-primary">Hủy</button>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
