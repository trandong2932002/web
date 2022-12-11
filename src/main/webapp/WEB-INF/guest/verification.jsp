<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
  <title>Xác Nhận</title>

  <%@include file="/WEB-INF/dependencies/meta.html" %>
  <%@include file="/WEB-INF/dependencies/script.jsp" %>
  <%@include file="/WEB-INF/dependencies/style.jsp" %>

</head>
<body class="bg-light">

<section id="verification" class="bg-light">
  <div class="vh-100 d-sm-flex justify-content-sm-center align-items-sm-center">
    <div class="m-sm-5 p-5 w-50 shadow">

      <div class="mb-2">
        <span>[LOGO]</span>
        <span>CONMEO</span>
      </div>

      <div class="mb-3">
        <span class="h3">Xác thực</span>
      </div>

      <c:if test="${not empty message}">
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
      </c:if>

      <form id="main-form" method="post">

        <input type="hidden" name="action" value="verification">
        <input type="hidden" id="action2" name="action2">

        <div id="otp" class="d-flex flex-row justify-content-center mt-2">
          <input class="m-2 text-center form-control rounded" name="otp-first" type="text" id="otp-first" maxlength="1"/>
          <input class="m-2 text-center form-control rounded" name="otp-second" type="text" id="otp-second" maxlength="1"/>
          <input class="m-2 text-center form-control rounded" name="otp-third" type="text" id="otp-third" maxlength="1"/>
          <input class="m-2 text-center form-control rounded" name="otp-fourth" type="text" id="otp-fourth" maxlength="1"/>
          <input class="m-2 text-center form-control rounded" name="otp-fifth" type="text" id="otp-fifth" maxlength="1"/>
          <input class="m-2 text-center form-control rounded" name="otp-sixth" type="text" id="otp-sixth" maxlength="1"/>
        </div>

        <div class="d-flex flex-row justify-content-center mt-2">
          <button id="create-new-otp" class="btn btn-warning me-2">Lấy mã mới</button>
          <button id="verify-otp" class="btn btn-primary">Xác thực</button>
        </div>
      </form>

    </div>
  </div>
</section>

<script>
    const inputs = document.querySelectorAll('#otp > *[id]');
    for (let i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener('keydown', function (e) {
            if (e.key === "Backspace") {
                inputs[i].value = '';
                if (i !== 0)
                    inputs[i - 1].focus();
            } else {
                if (i === inputs.length - 1 && inputs[i].value !== '') {
                    return true;
                } else if (e.keyCode > 47 && e.keyCode < 58) {
                    inputs[i].value = e.key;
                    if (i !== inputs.length - 1) inputs[i + 1].focus();
                    e.preventDefault();
                } else if (e.keyCode > 64 && e.keyCode < 91) {
                    inputs[i].value = String.fromCharCode(e.keyCode);
                    if (i !== inputs.length - 1)
                        inputs[i + 1].focus();
                    e.preventDefault();
                }
            }
        });
    }

    var pathname = window.location.pathname
    document.getElementById("main-form").action = pathname
    document.getElementById("create-new-otp").addEventListener("click", function () {
        document.getElementById("action2").value = "create"
    })
    document.getElementById("verify-otp").addEventListener("click", function () {
        document.getElementById("action2").value = ""
    })
</script>

</body>
</html>