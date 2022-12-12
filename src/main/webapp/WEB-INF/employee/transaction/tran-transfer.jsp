<%--
  Created by IntelliJ IDEA.
  User: 20133
  Date: 12/1/2022
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chuyển Tiền</title>
    <link rel="stylesheet" href="assets/styles/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/styles/codicons/codicon.css">
    <link rel="stylesheet" href="assets/styles/codicons/codicon.ttf">

    <script src="assets/scripts/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<section id="payment" class="bg-light">
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

            <div class="mb-3">
                <span class="h3">Chuyển khoản</span>
            </div>

            <form action="transaction_emp" method="post" onsubmit="">

                <div id="source">
                    <p class="h5 mb-3">Chuyển Khoản</p>


                    <div class="mb-2">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="account" class="form-label">Số tài khoản thanh toán</label>
                                <input type="text" name="" id="" class=" form-control text-success" value="${account}" disabled>
                                <input type="hidden" name="account" id="" class=" form-control text-success" value="${account}" >
                            </div>

                            <div class="col-sm-6">
                                <label for="balance" class="form-label">Số dư khả dụng</label>
                                <input type="text" name="" id="" class=" form-control text-success" value="${balance}" disabled>
                                <input type="hidden" name="balance" id="" class=" form-control text-success" value="${balance}" >
                            </div>

                        </div>
                    </div>
                    <div class="mb-2">
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="account-dist" class="form-label">Số tài khoản đích</label>
                                <input type="text" name="account-dist" id="" class=" form-control text-success" value="" >
                            </div>
                        </div>
                    </div>
                    <div class="mb-1">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="select_money" class="form-label">Chọn số tiền</label>
                                <select name="select_money" class="form-control" id="id_select_money">
                                    <option value="1">100.000 VND</option>
                                    <option value="2">200.000 VND</option>
                                    <option value="3">500.000 VND</option>
                                    <option value="4">1.000.000 VND</option>
                                    <option value="5">2.000.000 VND</option>
                                    <option value="6">5.000.000 VND</option>
                                    <option value="7">Số tiền khác</option>
                                </select>
                            </div>
                            <div class="col-sm-6" id="other_option" style="display: none;">
                                <label class="form-label"> Nhập số tiền</label>
                                <input name="differ-money" class="form-control" type="text" id="w_other_option">
                            </div>
                        </div>
                    </div>
                    <div class="mb-2">
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="content" class="form-label">Nội dung chuyển khoản</label>
                                <textarea name="content" id="content" class="form-control"></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="d-flex justify-content-sm-end justify-content-center mt-3">
                        <button class="btn btn-primary me-2" name="action" value="cancel">Hủy</button>
                        <button class="btn btn-primary" name="action" value="tran-transfer">Tiếp tục</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
    <script>
        $(document).on( function (e) {
            e.preventDefault();
        });
        $('select[name=select_money]').on('change', function() {
            if (this.value == '7') {
                $("#other_option").show();
            } else {
                $("#other_option").hide();
            }
        });
    </script>
</section>
</body>
</html>
