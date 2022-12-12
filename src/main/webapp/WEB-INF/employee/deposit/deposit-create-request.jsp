<%--
  Created by IntelliJ IDEA.
  User: 20133
  Date: 12/1/2022
  Time: 3:36 PM
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
    <script async src="assets/scripts/another/customer/savings/savings-create.js"></script>
</head>
<body>
<section id="payment" class="bg-light">
    <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
        <div class="m-sm-5 p-5 shadow">

            <div class="mb-3">
                <span class="h3">Tạo khoản gửi tiết kiệm</span>
            </div>

            <form action="savings-emp" method="post" onsubmit="return validate()">
                <input type="hidden" name="account" value="${account}">
                <div class="mb-2">
                    <label for="savings-name" class="form-label">Tên sổ tiết kiệm</label>
                    <input type="text" name="savings-name" id="" class="form-control">
                </div>

                <div class="mb-2">
                    <div class="row">
                        <div class="col-sm-6 mb-sm-0 mb-3">
                            <label for="amount" class="form-label">Số tiền</label>
                            <input type="number" name="amount" id="amount" class="form-control" step="1000" value="100000">
                        </div>

                        <div class="col-sm-6">
                            <label for="balance" class="form-label">Số dư khả dụng</label>
                            <input type="text" name="" id="" class=" form-control text-success" value="${balance}">
                            <input type="hidden" name="" id="available-amount" class=" form-control text-success" value="${balance}">
                        </div>
                    </div>
                </div>

                <div class="mb-2">
                    <div class="row">
                        <div class="col-sm-6 mb-sm-0 mb-3">
                            <label for="term" class="form-label">Kỳ hạn</label>
                            <select name="term" id="term" class="form-select"></select>
                        </div>

                        <div class="col-sm-6">
                            <label for="interest-rate" class="form-label">Lãi suất</label>
                            <input type="text" name="interest-rate" id="interest-rate" class="form-control">
                        </div>
                    </div>
                </div>

                <div class="mb-2">
                    <div class="row">
                        <div class="col-sm-6 mb-sm-0 mb-3">
                            <label for="create-date" class="form-label">Ngày mở sổ</label>
                            <input type="date" name="create-date" id="create-date" class="form-control" >
                        </div>

                        <div class="col-sm-6">
                            <label for="maturity-date" class="form-label">Ngày đáo hạn</label>
                            <input type="date" name="maturity-date" id="maturity-date" class="form-control" >
                        </div>
                    </div>
                </div>

                <div class="mb-2">
                    <label for="renew" class="form-label">Tự động tái tục</label>
                    <select name="renew" id="" class="form-select">
                        <option value="1" selected>Không</option>
                        <option value="2">Tái tục gốc</option>
                        <option value="3">Tái tục gốc và lãi</option>
                    </select>
                </div>

                <div class="d-flex justify-content-sm-end justify-content-center mt-3">
                    <button class="btn btn-primary me-2" name="action" value="cancel">Hủy</button>
                    <button class="btn btn-primary" name="action" value="deposit-create-request">Tạo</button>
                </div>
            </form>

        </div>
    </div>
</section>
</body>
</html>
