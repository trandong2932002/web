<%--
  Created by IntelliJ IDEA.
  User: 20133
  Date: 12/7/2022
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="sign-up-emp" method="post">
    <input type="hidden" name="action" value="register">
    <label>email</label><br>
    <input type="email" name="email" id="" ><br>
    <label>firstname</label><br>
    <input type="text" name="firstname" id="" ><br>
    <label>lastname</label><br>
    <input type="text" name="lastname" id="" ><br>
    <label>password</label><br>
    <input type="password" name="password" id="" ><br>
    <label>phone_number</label><br>
    <input type="text" name="phone_number" id="" ><br>
    <label>ssn</label><br>
    <input type="text" name="ssn" id="" ><br>

    <input type="submit" value="Đăng ký">
</form>
</body>
</html>
