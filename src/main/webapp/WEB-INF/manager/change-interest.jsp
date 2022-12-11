<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
  <title>Điều Chỉnh Lãi Suất</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

<%@include file="dependencies/nav.jsp" %>

<section id="change-interest">
  <div class="d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 shadow">

      <div class="mb-3">
        <span class="h3">Lãi suất gửi tiết kiệm</span>
      </div>

      <c:if test="${not empty message}">
        <c:if test="${status}">
          <div class="alert alert-success" role="alert">
              ${message}
          </div>
        </c:if>
        <c:if test="${not status}">
          <div class="alert alert-danger" role="alert">
              ${message}
          </div>
        </c:if>
      </c:if>

      <form action="" method="post">
        <div class="container p-0 mb-2">
          <c:forEach items="${interestList}" var="interest" varStatus="loop">

            <div class="row">
              <div class="col-sm-4">
                <label for="term${loop.index}" class="form-label">Kỳ hạn</label>
                <input type="text" name="term${loop.index}" id="term${loop.index}" class="form-control"
                       value="${interest.name}" disabled>
              </div>

              <div class="col-sm-4">
                <label for="interest${loop.index}" class="form-label">Lãi suất</label>
                <input type="text" name="interest${loop.index}" id="interest${loop.index}" class="form-control"
                       value="${interest.interest}">
              </div>

              <div class="col-sm-4">
                <label for="penaltyInterest${loop.index}" class="form-label">Lãi suất phạt</label>
                <input type="text" name="penaltyInterest${loop.index}" id="penaltyInterest${loop.index}"
                       class="form-control" value="${interest.penaltyInterest}">
              </div>
            </div>

          </c:forEach>
        </div>

        <div class="d-flex justify-content-sm-end justify-content-center mt-3">
          <button class="btn btn-secondary me-2" formaction="/">Hủy</button>
          <button class="btn btn-primary" formaction="/_manager/interest">Sửa</button>
        </div>
      </form>

    </div>
  </div>
</section>

</body>
</html>