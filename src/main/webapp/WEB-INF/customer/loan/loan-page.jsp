<!doctype html>
<html lang="en">
<head>
  <title>Tạo Khoản Vay</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

  <script src="${pageContext.request.contextPath}/assets/scripts/another/customer/savings/savings-create.js"
          defer></script>

</head>
<body class="bg-light">

<%@include file="../dependencies/nav.jsp" %>

<section id="loan-list" class="overflow-auto">
  <div class="mx-5 my-4">

    <div class="mb-3">
      <span class="h3">Các khoản vay</span>
    </div>

    <div class="container-fluid">
      <div class="row flex-nowrap overflow-auto">

        <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Khoản vay 1</h5>
              <p class="card-text d-flex justify-content-between">
                <span>Lãi suất vay</span>
                <span>8%</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Kỳ hạn vay</span>
                <span>3 tháng</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Ngày bắt đầu trả nợ</span>
                <span>1-2-2000</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Thời gian trả nợ</span>
                <span>12 tháng</span>
              </p>
              <a href="./loan-information.html" class="btn btn-outline-primary d-flex justify-content-center">Thông tin
                chi tiết</a>
            </div>
            <div class="card-footer bg-success text-light">
              <small class="d-flex justify-content-between">
                <span>Trạng thái:</span>
                <span id="loan-state">Chấp nhận cho vay</span>
              </small>
              <small class="d-flex justify-content-between">
                <span>Giải ngân:</span>
                <span id="loan-disbursement">1-1-2000</span>
              </small>
            </div>
          </div>
        </div>

        <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Khoản vay 2</h5>
              <p class="card-text d-flex justify-content-between">
                <span>Lãi suất vay</span>
                <span>8%</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Kỳ hạn vay</span>
                <span>3 tháng</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Ngày bắt đầu trả nợ</span>
                <span>Không</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Thời gian trả nợ</span>
                <span>12 tháng</span>
              </p>
              <a href="./loan-information.html" class="btn btn-outline-primary d-flex justify-content-center">Thông tin
                chi tiết</a>
            </div>
            <div class="card-footer">
              <small class="d-flex justify-content-between">
                <span>Trạng thái:</span>
                <span id="loan-state">Từ chối cho vay</span>
              </small>
              <small class="d-flex justify-content-between">
                <span>Giải ngân:</span>
                <span id="loan-disbursement">Không</span>
              </small>
            </div>
          </div>
        </div>

        <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Khoản vay 3</h5>
              <p class="card-text d-flex justify-content-between">
                <span>Lãi suất vay</span>
                <span>8%</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Kỳ hạn vay</span>
                <span>3 tháng</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Ngày bắt đầu trả nợ</span>
                <span>Không</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Thời gian trả nợ</span>
                <span>12 tháng</span>
              </p>
              <a href="./loan-information.html" class="btn btn-outline-primary d-flex justify-content-center">Thông tin
                chi tiết</a>
            </div>
            <div class="card-footer bg-warning">
              <small class="d-flex justify-content-between">
                <span>Trạng thái:</span>
                <span id="loan-state">Đã nộp hồ sơ</span>
              </small>
              <small class="d-flex justify-content-between">
                <span>Giải ngân:</span>
                <span id="loan-disbursement">Không</span>
              </small>
            </div>
          </div>
        </div>

        <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Khoản vay 4</h5>
              <p class="card-text d-flex justify-content-between">
                <span>Lãi suất vay</span>
                <span>8%</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Kỳ hạn vay</span>
                <span>3 tháng</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Ngày bắt đầu trả nợ</span>
                <span>Không</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Thời gian trả nợ</span>
                <span>12 tháng</span>
              </p>
              <a href="./loan-information.html" class="btn btn-outline-primary d-flex justify-content-center">Thông tin
                chi tiết</a>
            </div>
            <div class="card-footer bg-warning">
              <small class="d-flex justify-content-between">
                <span>Trạng thái:</span>
                <span id="loan-state">Đang trong quá trình thẩm định</span>
              </small>
              <small class="d-flex justify-content-between">
                <span>Giải ngân:</span>
                <span id="loan-disbursement">Không</span>
              </small>
            </div>
          </div>
        </div>

        <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Khoản vay 5</h5>
              <p class="card-text d-flex justify-content-between">
                <span>Lãi suất vay</span>
                <span>8%</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Kỳ hạn vay</span>
                <span>3 tháng</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Ngày bắt đầu trả nợ</span>
                <span>Không</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Thời gian trả nợ</span>
                <span>12 tháng</span>
              </p>
              <a href="./loan-information.html" class="btn btn-outline-primary d-flex justify-content-center">Thông tin
                chi tiết</a>
            </div>
            <div class="card-footer bg-warning">
              <small class="d-flex justify-content-between">
                <span>Trạng thái:</span>
                <span id="loan-state">Đang trong quá trình thẩm định</span>
              </small>
              <small class="d-flex justify-content-between">
                <span>Giải ngân:</span>
                <span id="loan-disbursement">Không</span>
              </small>
            </div>
          </div>
        </div>

        <div class="col-xxl-2 col-lg-3 col-md-4 col-sm-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Khoản vay 6</h5>
              <p class="card-text d-flex justify-content-between">
                <span>Lãi suất vay</span>
                <span>8%</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Kỳ hạn vay</span>
                <span>3 tháng</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Ngày bắt đầu trả nợ</span>
                <span>1-2-2000</span>
              </p>
              <p class="card-text d-flex justify-content-between">
                <span>Thời gian trả nợ</span>
                <span>12 tháng</span>
              </p>
              <a href="./loan-information.html" class="btn btn-outline-primary d-flex justify-content-center">Thông tin
                chi tiết</a>
            </div>
            <div class="card-footer bg-danger text-light">
              <small class="d-flex justify-content-between">
                <span>Trạng thái:</span>
                <span id="loan-state">Quá hạn</span>
              </small>
              <small class="d-flex justify-content-between">
                <span>Giải ngân:</span>
                <span id="loan-disbursement">1-1-2000</span>
              </small>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</section>

<section id="request-loan" class="overflow-auto">
  <div class="mx-5 my-4">

    <div class="d-flex justify-content-center">
      <form method="post">
        <input type="hidden" name="action" value="request">
        <button class="btn btn-primary" formaction="/loan">Tạo yêu cầu vay</button>
      </form>
    </div>

  </div>
  <div></div>
</section>

</body>
</html>