<%--
  Created by IntelliJ IDEA.
  User: ben10
  Date: 03.08.2022
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Wszyscy trenerzy</title>
</head>
<body bgcolor="#6495ed">
<h2>Lista trenerów</h2>
</p>
<div>
<input type="button" onclick="location.href='/trainer/add';" value="DODAJ TRENERA" />
<input type="button" onclick="location.href='/customer/all';" value="LISTA KLIENTÓW"/>
</div>
</p>
<table border="1" bgcolor = #b0c4de>
    <tr>
        <th>L.p.</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Opis</th>
    </tr>

        <c:forEach items="${trainers}" var="trainer" begin="0" end="${size-1}" varStatus="row">
            <tr align="center">
                <td>${row.index + 1}</td>
                <td align="left">${trainer.name}</td>
                <td align="left">${trainer.surname}</td>
                <td>${trainer.descryption}</td>
                <td><input type="button" onclick="location.href='/trainer/edit/${trainer.trainerId}';" value="EDYTUJ"/></td>
                <td><input type="button" onclick="location.href='/trainer/delete/${trainer.trainerId}';" value="USUŃ"/></td>
            </tr>
        </c:forEach>

</table>
</body>
</html>
