<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ben10
  Date: 11.08.2022
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Obecność</title>
</head>
<body>
<h3>Wpisz numer karnetu</h3>
<form  method="post" id="form">
    <label>
        <input name="cartNumber" type="text" id="cartNumber" onchange="this.form.submit()" autofocus>
    </label>
    <input type="submit" value="WYŚLIJ">
</form>
<p></p>
<c:out value="${info}"/>
</body>
</html>


