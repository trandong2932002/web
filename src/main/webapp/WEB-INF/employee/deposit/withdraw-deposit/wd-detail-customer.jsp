<%--
  Created by IntelliJ IDEA.
  User: 20133
  Date: 12/1/2022
  Time: 3:34 PM
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
    <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
        <div class="m-sm-5 p-5 shadow">

            <div class="mb-2">
                <span>[LOGO]</span>
                <span>CONMEO</span>
            </div>

            <div class="mb-3">
                <span class="h3">Gửi Tiết Kiệm</span>
            </div>

            <form action="savings-emp" method="post">
                <input type="hidden" name="action" value="withdraw-savings">
                <div>
                    <div class="mb-2">
                        <div class="row">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Số tài khoản</th>
                                    <th scope="col">Họ</th>
                                    <th scope="col">Tên</th>
                                    <th scope="col">Ngày tạo</th>
                                    <th scope="col">Trạng thái</th>
                                    <th scope="col">Tên sổ</th>
                                    <th scope="col">Số lượng(VND)</th>
                                    <th scope="col">Tái tục</th>
                                    <th scope="col">Lãi xuất(%)</th>
                                    <th scope="col">Ngày đáo hạn</th>
                                    <th scope="col">Ngày kết thúc</th>
                                    <th scope="col">Rút Sổ</th>
                                </tr>
                                </thead>
                                <tbody id="myTable">
                                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                                <c:forEach var="savings" items="${list_savings}">
                                    <tr>
                                        <td>${savings.id}</td>
                                        <td>${account.phoneNumber}</td>
                                        <td>${account.firstname}</td>
                                        <td>${account.lastname}</td>
                                        <td>${savings.createdDate}</td>
                                        <td>${savings.status}</td>
                                        <td>${savings.name}</td>
                                        <td><fmt:formatNumber value="${savings.amount}" maxFractionDigits="0"/></td>
                                        <td>${savings.rolledOver}</td>
                                        <td>${savings.interest}</td>
                                        <td>${savings.maturityDate}</td>
                                        <td>${savings.endTime}</td>
                                        <td><button name="savings-id" value="${savings.id}" class="btn btn-primary">Rút Sổ</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
