<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
  <title>Gửi Tiết Kiệm</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

  <script src="${pageContext.request.contextPath}/assets/scripts/another/customer/savings/savings.js" defer></script>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="savings-list" class="overflow-auto">
  <div class="mx-5 my-4">

    <div class="mb-3">
      <span class="h3">Các khoản gửi tiết kiệm</span>
    </div>


    <div>
      <c:choose>
        <c:when test="${empty savingsList}">
          <div class="container-fluid">
            <div class="row flex-nowrap overflow-auto">
              <p>Bạn không có khoản gửi tiết kiệm nào</p>
            </div>
          </div>
        </c:when>

        <c:otherwise>
          <div class="container-fluid">
            <div class="row flex-nowrap overflow-auto">
              <c:forEach items="${savingsList}" var="savings">
                <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">
                  <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">${savings.name}</h5>
                      <p class="card-text d-flex justify-content-between">
                        <span>Ngày mở sổ:</span>
                        <span>${savings.createdDate}</span>
                      </p>
                      <p class="card-text d-flex justify-content-between">
                        <span>Ngày đáo hạn:</span>
                        <span id="maturity-date">${savings.maturityDate}</span>
                      </p>
                      <p class="card-text d-flex justify-content-between">
                        <span>Ngày rút:</span>
                        <span id="end-date">
                          <c:choose>
                            <c:when test="${not empty savings.endTime}">
                              <fmt:parseDate value="${savings.endTime}" pattern="y-M-dd'T'H:m" var="parseEndTime"></fmt:parseDate>
                              <fmt:formatDate value="${parseEndTime}" pattern="yyyy-MM-dd" />
                            </c:when>
                            <c:otherwise>Chưa rút</c:otherwise>
                          </c:choose>
                        </span>
                      </p>
                      <p class="card-text d-flex justify-content-between">
                        <span>Kỳ hạn:</span>
                        <span>
                          <c:choose>
                            <c:when test="${savings.term == 'ONEMONTH'}">1</c:when>
                            <c:when test="${savings.term == 'THREEMONTHS'}">3</c:when>
                            <c:when test="${savings.term == 'SIXMONTHS'}">6</c:when>
                            <c:when test="${savings.term == 'TWELVEMONTHS'}">12</c:when>
                            <c:when test="${savings.term == 'TWENTYFOURMONTHS'}">24</c:when>
                            <c:when test="${savings.term == 'THIRTYSIXMONTHS'}">36</c:when>
                          </c:choose>
                          tháng</span>
                      </p>
                      <p class="card-text d-flex justify-content-between">
                        <span>Lãi suất:</span>
                        <span>${savings.interest} năm</span>
                      </p>
                      <a href="/savings?action=information&id=${savings.id}"
                         class="btn btn-outline-primary d-flex justify-content-center">Thông
                        tin chi tiết</a>
                    </div>

                    <div class="card-footer">
                      <small class="d-flex justify-content-between">
                        <span>Trạng thái:</span>
                        <span id="savings-state">
                          <c:choose>
                            <c:when test="${savings.status == 'FINISH'}">Đã hoàn tất</c:when>
                            <c:when test="${savings.status == 'INPROGRESS'}">Đang chờ</c:when>
                            <c:when test="${savings.status == 'BREAK'}">Đã rút trước hạn</c:when>
                          </c:choose>
                        </span>
                      </small>
                      <small class="d-flex justify-content-between">
                        <span>Số ngày còn lại:</span>
                        <span id="date-diff">
                        </span>
                      </small>
                    </div>
                  </div>
                </div>
              </c:forEach>

            </div>
          </div>
        </c:otherwise>
      </c:choose>
    </div>

    <%--    <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">--%>
    <%--      <div class="card">--%>
    <%--        <div class="card-body">--%>
    <%--          <h5 class="card-title">Sổ 1</h5>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Ngày mở sổ:</span>--%>
    <%--            <span>1-1-2000</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Ngày đáo hạn:</span>--%>
    <%--            <span>1-2-2000</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Kỳ hạn:</span>--%>
    <%--            <span>1 tháng</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Lãi suất:</span>--%>
    <%--            <span>4% năm</span>--%>
    <%--          </p>--%>
    <%--          <a href="/savings?action=information&id=1" class="btn btn-outline-primary d-flex justify-content-center">Thông--%>
    <%--            tin chi tiết</a>--%>
    <%--        </div>--%>

    <%--        <div class="card-footer">--%>
    <%--          <small class="d-flex justify-content-between">--%>
    <%--            <span>Trạng thái:</span>--%>
    <%--            <span id="savings-state">Đang chờ</span>--%>
    <%--          </small>--%>
    <%--          <small class="d-flex justify-content-between">--%>
    <%--            <span>Số ngày còn lại:</span>--%>
    <%--            <span>100</span>--%>
    <%--          </small>--%>
    <%--        </div>--%>
    <%--      </div>--%>
    <%--    </div>--%>

    <%--    <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">--%>
    <%--      <div class="card">--%>
    <%--        <div class="card-body">--%>
    <%--          <h5 class="card-title">Sổ 1</h5>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Ngày mở sổ:</span>--%>
    <%--            <span>1-1-2000</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Ngày đáo hạn:</span>--%>
    <%--            <span>1-2-2000</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Kỳ hạn:</span>--%>
    <%--            <span>1 tháng</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Lãi suất:</span>--%>
    <%--            <span>4% năm</span>--%>
    <%--          </p>--%>
    <%--          <a href="/savings?action=information&id=1" class="btn btn-outline-primary d-flex justify-content-center">Thông--%>
    <%--            tin chi tiết</a>--%>
    <%--        </div>--%>

    <%--        <div class="card-footer">--%>
    <%--          <small class="d-flex justify-content-between">--%>
    <%--            <span>Trạng thái:</span>--%>
    <%--            <span id="savings-state">Đã rút trước hạn</span>--%>
    <%--          </small>--%>
    <%--          <small class="d-flex justify-content-between">--%>
    <%--            <span>Số ngày còn lại:</span>--%>
    <%--            <span>20</span>--%>
    <%--          </small>--%>
    <%--        </div>--%>
    <%--      </div>--%>
    <%--    </div>--%>

    <%--    <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">--%>
    <%--      <div class="card">--%>
    <%--        <div class="card-body">--%>
    <%--          <h5 class="card-title">Sổ 1</h5>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Ngày mở sổ:</span>--%>
    <%--            <span>1-1-2000</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Ngày đáo hạn:</span>--%>
    <%--            <span>1-2-2000</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Kỳ hạn:</span>--%>
    <%--            <span>1 tháng</span>--%>
    <%--          </p>--%>
    <%--          <p class="card-text d-flex justify-content-between">--%>
    <%--            <span>Lãi suất:</span>--%>
    <%--            <span>4% năm</span>--%>
    <%--          </p>--%>
    <%--          <a href="/savings?action=information&id=1" class="btn btn-outline-primary d-flex justify-content-center">Thông--%>
    <%--            tin chi tiết</a>--%>
    <%--        </div>--%>

    <%--        <div class="card-footer">--%>
    <%--          <small class="d-flex justify-content-between">--%>
    <%--            <span>Trạng thái:</span>--%>
    <%--            <span id="savings-state">Đã hoàn tất</span>--%>
    <%--          </small>--%>
    <%--          <small class="d-flex justify-content-between">--%>
    <%--            <span>Số ngày còn lại:</span>--%>
    <%--            <span>0</span>--%>
    <%--          </small>--%>
    <%--        </div>--%>
    <%--      </div>--%>
    <%--    </div>--%>


  </div>
  <div></div>
</section>

<section id="create-savings" class="overflow-auto">
  <div class="mx-5 my-4">

    <div class="d-flex justify-content-center">
      <a href="/savings?action=create" class="btn btn-primary">Tạo khoản gửi tiết kiệm mới</a>
    </div>

  </div>
  <div></div>
</section>

</body>
</html>