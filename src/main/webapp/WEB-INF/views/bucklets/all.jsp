<%--
  Created by IntelliJ IDEA.
  User: ben10
  Date: 03.08.2022
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <title>Rodzaje karnetów</title>
</head>
<body bgcolor="#6495ed">
<h2> Rodzaje karnetów</h2>
<input type="button" onclick="location.href='/bucklet/add';" value="DODAJ KARNET" />
<input type="button" onclick="location.href='/customer/all';" value="LISTA KLIENTÓW"/>
</p>
<table border="1" bgcolor = #b0c4de id = "buckletTable" class ="display">
    <thead>
    <tr style="background-color: darkblue; color: white">
        <th>L.p.</th>
        <th>Nazwa</th>
        <th>Cena</th>
        <th>Okres ważnosci</th>
        <th>Ilość wejść</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bucklets}" var="bucklet" begin="0" end="${size}" varStatus="row">
        <tr align="center">
            <td>${row.index + 1}</td>
            <td align="left">${bucklet.name}</td>
            <td>${bucklet.price}</td>
            <td>${bucklet.validity}</td>
            <td>${bucklet.numberOfVisits}</td>
            <td><input type="button" onclick="location.href='/bucklet/edit/${bucklet.buckletId}';" value="EDYTUJ"/>
            <input type="button" onclick="location.href='/bucklet/delete/${bucklet.buckletId}';" value="USUŃ"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script>
    $(document).ready( function () {
        $('#buckletTable').DataTable();
    });
</script>
</body>
</html>
