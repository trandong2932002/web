<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
  <title>Gửi Tiết Kiệm</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="savings-information" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Thông tin khoản gửi tiết kiệm</span>
      </div>

      <form action="" method="post">

        <input type="hidden" name="action" value="break">

        <div class="mb-2">
          <label for="savings-name" class="form-label">Tên sổ tiết kiệm</label>
          <input type="text" name="savings-name" id="savings-name" class="form-control" value="${savings.name}"
                 disabled>
        </div>

        <div class="mb-2">
          <label for="amount" class="form-label">Số tiền</label>
          <input type="number" name="amount" id="amount" class="form-control" step="1000" value="${savings.amount}"
                 disabled>
        </div>

        <div class="mb-2">
          <div class="row">
            <div class="col-sm-6 mb-sm-0 mb-3">
              <label for="term" class="form-label">Kỳ hạn</label>
              <c:choose>
                <c:when test="${savings.term == 'ONEMONTH'}"><c:set var="term" value="1"></c:set></c:when>
                <c:when test="${savings.term == 'THREEMONTHS'}"><c:set var="term" value="3"></c:set></c:when>
                <c:when test="${savings.term == 'SIXMONTHS'}"><c:set var="term" value="6"></c:set></c:when>
                <c:when test="${savings.term == 'TWELVEMONTHS'}"><c:set var="term" value="12"></c:set></c:when>
                <c:when test="${savings.term == 'TWENTYFOURMONTHS'}"><c:set var="term" value="24"></c:set></c:when>
                <c:when test="${savings.term == 'THIRTYSIXMONTHS'}"><c:set var="term" value="36"></c:set></c:when>
              </c:choose>
              <input type="text" class="form-control"
                     value="${term} tháng" disabled>
            </div>

            <div class="col-sm-6">
              <label for="interest-rate" class="form-label">Lãi suất</label>
              <input type="text" name="interest-rate" id="interest-rate" class="form-control"
                     value="${savings.interest}" disabled>
            </div>
          </div>
        </div>

        <div class="mb-2">
          <div class="row">
            <div class="col-sm-6 mb-sm-0 mb-3">
              <label for="create-date" class="form-label">Ngày mở sổ</label>
              <input type="date" name="create-date" id="create-date" class="form-control" value="${savings.createdDate}"
                     disabled>
            </div>

            <div class="col-sm-6">
              <label for="maturity-date" class="form-label">Ngày đáo hạn</label>
              <input type="date" name="maturity-date" id="maturity-date" class="form-control"
                     value="${savings.maturityDate}" disabled>
            </div>
          </div>
        </div>

        <div class="mb-2">
          <div class="row">
            <div class="col-sm-6 mb-sm-0 mb-3">
              <label for="renew" class="form-label">Tự động tái tục</label>
              <select name="renew" id="renew" class="form-select" onselect="${savings.rolledOver}" disabled>
                <option value="1" selected>Không</option>
                <option value="2">Tái tục gốc</option>
                <option value="3">Tái tục gốc và lãi</option>
              </select>
            </div>
            <div class="col-sm-6">
              <label for="" class="form-label">Ngày rút</label>
              <c:choose>
                <c:when test="${not empty savings.endTime}">
                  <fmt:parseDate value="${savings.endTime}" pattern="y-M-dd'T'H:m" var="parseEndTime"></fmt:parseDate>
                  <fmt:formatDate value="${parseEndTime}" pattern="yyyy-MM-dd" var="endDate"/>
                </c:when>
                <c:otherwise><c:set value="Chưa rút" var="endDate"/></c:otherwise>
              </c:choose>
              <input type="text" class="form-control" value="${endDate}" disabled>
            </div>
          </div>
        </div>

        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <input type="hidden" name="action" value="">
          <a class="btn btn-secondary me-2" href="/savings">Trở về</a>
          <button class="btn btn-primary" formaction="/savings">Rút trước hạn</button>
        </div>
      </form>

    </div>
  </div>
</section>

</body>
</html>