<%--
  Created by IntelliJ IDEA.
  User: ben10
  Date: 05.08.2022
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dodaj trenera</title>
</head>
<body bgcolor="#6495ed">
<h2>Dodaj nowego trenera</h2>
<form:form method="post" modelAttribute="trainer">
    Imię</br>
    <form:input path="name"/></br>
    <form:errors path="name" cssStyle="color: red"/></br>
    Nazwisko</br>
    <form:input path="surname"/></br>
    <form:errors path="surname" cssStyle="color: red"/></br>
    Opis</br>
    <form:select path="descryption">
        <form:options items="${descryptions}"/>
    </form:select></br>
    </p>
    <input type="submit" value="WYŚLIJ">
</form:form>

</body>
</html>
