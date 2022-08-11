<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ben10
  Date: 10.08.2022
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Odnów karnet</title>
</head>
<body bgcolor="#6495ed">
<h3 style="display: flex">Odnów karnet dla <div style="color: white; margin: 0 10px">${name} ${surname}</div></h3>

<form method="post">
    <input type="number" name="clientId" value="${clientId}" hidden>
    Ustaw typ karnetu :
    <select name="buckletType">
        <c:forEach var="b" items="${bucklets}">
            <option value="${b.buckletId}">${b.name}</option>
        </c:forEach>
    </select>
    Ustaw date
    <input type="date" name="date">

        <input type="submit" value="WYŚLIJ">

</form>
</body>
</html>
