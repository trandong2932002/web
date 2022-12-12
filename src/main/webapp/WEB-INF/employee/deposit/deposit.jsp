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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
        <div class="mb-2">
          <div class="row">
            <div class="col-sm-3">
              <label for="account-number-source" class="form-label">Số tài khoản</label>
              <input type="text" name="available-amount" id="account-number" class=" form-control text-success"
                     value="" disabled>
            </div>
            <div class="col-sm-3">
              <label for="account-number-source" class="form-label">Tên sổ</label>
              <input type="text" name="available-amount" id="name" class=" form-control text-success"
                     value="" disabled>
            </div>
            <div class="col-sm-3">
              <label for="account-number-source" class="form-label">Ngày đáo hạn</label>
              <input type="text" name="available-amount" id="maturity-date" class=" form-control text-success"
                     value="" disabled>
            </div>
            <div class="col-sm-3">
              <label for="account-number-source" class="form-label">Ngày tạo</label>
              <input type="date" name="available-amount" id="created-date" class=" form-control text-success" value="" disabled>
            </div>
          </div>
          <div class="mb-2">
            <div class="row">
              <div class="col-sm-3">
                <label for="account-number-source" class="form-label">Trạng thái</label>
                <input type="text" name="available-amount" id="status" class=" form-control text-success" value="" disabled>
              </div>
              <div class="col-sm-3">
                <label for="account-number-source" class="form-label">Tái tục</label>
                <input type="text" name="available-amount" id="rolled-over" class=" form-control text-success" value="" disabled>
              </div>
              <div class="col-sm-3">
                <label for="account-number-source" class="form-label">Số lượng</label>
                <input type="text" name="available-amount" id="amount" class=" form-control text-success" value="" disabled>
              </div>
              <div class="col-sm-3">
                <label for="account-number-source" class="form-label">Ngày kết thúc</label>
                <input type="date" name="available-amount" id="end-date" class=" form-control text-success" value="" disabled>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-sm-end justify-content-center mt-3">
            <button class="btn btn-primary me-2" name="action" value="create-report">Tạo Yêu Cầu</button>
            <button class="btn btn-primary me-2" name="action" value="withdraw-deposit">Rút sổ tiết kiệm</button>
          </div>

          <div class="mb-2">
            <div class="row">
              <div class="col-sm-6">
                <input type="text" id="myInput" class="form-control" placeholder="Tìm kiếm...">
              </div>
            </div>
          </div>

          <div class="mb-2">
            <div class="row">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Số tài khoản</th>
                  <th scope="col">Ngày tạo</th>
                  <th scope="col">Trạng thái</th>
                  <th scope="col">Tên sổ</th>
                  <th scope="col">Số lượng(VND)</th>
                  <th scope="col">Tái tục</th>
                  <th scope="col">Lãi xuất(%)</th>
                  <th scope="col">Ngày đáo hạn</th>
                  <th scope="col">Ngày kết thúc</th>
                </tr>
                </thead>
                <tbody id="myTable">
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <c:forEach var="savings" items="${list_savings}">
                  <tr>
                    <td>${savings.id}</td>
                    <td>${savings.transactionAccountSource.accountNumber}</td>
                    <td>${savings.createdDate}</td>
                    <td>${savings.status}</td>
                    <td>${savings.name}</td>
                    <td><fmt:formatNumber value="${savings.amount}" maxFractionDigits="0"/></td>
                    <td>${savings.rolledOver}</td>
                    <td>${savings.interest}</td>
                    <td>${savings.maturityDate}</td>
                    <td>${savings.endTime}</td>
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
  <script>
      $(document).ready(function () {
          $("#myInput").on("keyup", function () {
              var value = $(this).val().toLowerCase();
              $("#myTable tr").filter(function () {
                  $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
              });
          });
      });
      function row_click(e) {
          let temp = this.querySelectorAll("td")
          document.getElementById("account-number").value = temp[1].innerHTML
          document.getElementById("name").value = temp[4].innerHTML
          document.getElementById("maturity-date").value = temp[8].innerHTML
          document.getElementById("created-date").value = temp[2].innerHTML
          document.getElementById("status").value = temp[3].innerHTML
          document.getElementById("rolled-over").value = temp[6].innerHTML
          document.getElementById("amount").value = temp[5].innerHTML
          document.getElementById("end-date").value = temp[9].innerHTML.split("T")[0]
      }
      document.getElementById("myTable").querySelectorAll("tr").forEach((e) => {
          e.onclick = row_click
      })
  </script>
</section>
</body>
</html>
