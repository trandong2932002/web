<!doctype html>
<html lang="en">
<head>
  <title>Lịch Sử Giao Dịch</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="transaction-history-table">
  <div class="mx-5 my-4">
    <table class="table">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Loại giao dịch</th>
        <th scope="col">Ngày giao dịch</th>
        <th scope="col">Tài khoản nguồn</th>
        <th scope="col">Tài khoản đích</th>
        <th scope="col">Số tiền</th>
        <th scope="col">Thông tin chi tiết</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <th scope="row">1</th>
        <td>Chuyển khoản</td>
        <td>1-1-2000</td>
        <td>123789</td>
        <td>890543</td>
        <td>100000000</td>
        <td>Mua hàng</td>
      </tr>
      <tr>
        <th scope="row">2</th>
        <td>Chuyển khoản</td>
        <td>1-1-2000</td>
        <td>123789</td>
        <td>890543</td>
        <td>100000000</td>
        <td>Thanh toán hoá đơn</td>
      </tr>
      <tr>
        <th scope="row">3</th>
        <td>Chuyển khoản</td>
        <td>1-1-2000</td>
        <td>123789</td>
        <td>890543</td>
        <td>100000000</td>
        <td>Thanh toán hoá đơn</td>
      </tr>
      <tr>
        <th scope="row">4</th>
        <td>Gửi tiết kiệm</td>
        <td>1-1-2000</td>
        <td>123789</td>
        <td>890543</td>
        <td>100000000</td>
        <td>Khoản tiết kiệm ABC</td>
      </tr>
      <tr>
        <th scope="row">5</th>
        <td>Trả nợ vay</td>
        <td>1-1-2000</td>
        <td>123789</td>
        <td>890543</td>
        <td>100000000</td>
        <td>Khoản vay OKM</td>
      </tr>
      </tbody>
    </table>
  </div>
</section>

</body>
</html>