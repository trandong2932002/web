<%--
  Created by IntelliJ IDEA.
  User: 20133
  Date: 12/1/2022
  Time: 3:37 PM
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
<section id="payment" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Thông Tin Khách Hàng</span>
      </div>

      <form action="savings-emp" method="post" onsubmit="">

        <div id="source">
          <p class="h5 mb-3">Bên thanh toán</p>


          <div class="mb-2">
            <div class="row">
              <div class="col-sm-6">
                <label for="account" class="form-label">Số tài khoản thanh toán</label>
                <input type="text" name="" id="" class=" form-control text-success" value="${account.phoneNumber}" disabled>
                <input type="hidden" name="account" id="" class=" form-control text-success" value="${account.phoneNumber}" >
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
              <div class="col-sm-6">
                <label for="firstname" class="form-label">Họ và tên lót</label>
                <input type="text" name="firstname" id="" class=" form-control text-success" value="${account.firstname}" disabled>
              </div>

              <div class="col-sm-6">
                <label for="lastname" class="form-label">Tên</label>
                <input type="text" name="lastname" id="" class=" form-control text-success" value="${account.lastname}" disabled>
              </div>

            </div>
            <div class="mb-2">
              <div class="row">
                <div class="col-sm-6">
                  <label for="address" class="form-label">Địa chỉ</label>
                  <input type="text" name="address" id="" class=" form-control text-success" value="${account.address}" disabled>
                </div>

                <div class="col-sm-6">
                  <label for="dob" class="form-label">Ngày Sinh</label>
                  <input type="date" name="dob" id="" class=" form-control text-success" value="${dob}" disabled>
                </div>

              </div>
              <div class="mb-2">
                <div class="row">
                  <div class="col-sm-6">
                    <label for="number_phone" class="form-label">Số điện thoại</label>
                    <input type="text" name="number-phone" id="" class=" form-control text-success" value="${account.phoneNumber}" disabled>
                  </div>

                  <div class="col-sm-6">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" name="email" id="" class=" form-control text-success" value="${account.email}" disabled>
                  </div>

                </div>
              </div>
            </div>
            <div class="d-flex justify-content-sm-end justify-content-center mt-3">
              <button class="btn btn-primary me-2" name="action" value="cancel">Hủy</button>
              <button class="btn btn-primary" name="action" value="deposit-info-customer">Tiếp tục</button>
            </div>
          </div>
        </div>
      </form>

    </div>
  </div>
</section>
</body>
</html>
