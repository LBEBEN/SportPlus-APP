<%--
  Created by IntelliJ IDEA.
  User: ben10
  Date: 05.08.2022
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Wszyscy klienci</title>
</head>
<body bgcolor="#6495ed">
<h2>Wszyscy klienci</h2>
</p>
<table border="1" bgcolor = #b0c4de>
    <tr>
        <th>L.p.</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Numer karnetu</th>
        <th>Opłacono depozyt</th>
        <th>Adres email</th>
        <th>Nr telefonu</th>
        <th>Data zakupu</th>
        <th>Ostatnia wizyta</th>
        <th>Trener</th>
        <th>Rodzaj karnetu</th>
        <th>Uwagi</th>
        <th>
            <input type="button" onclick="location.href='/trainer/all';" value="TRENERZY" />
            <input type="button" onclick="location.href='/bucklet/all';" value="KARNETY" />
        </th>
    </tr>
    <c:forEach items="${customers}" var="customer" begin="0" end="${size-1}" varStatus="row">
        <tr align="center">
            <td>${row.index + 1}</td>
            <td align="left">${customer.name}</td>
            <td align="left">${customer.surname}</td>
            <td>${customer.cartNumber}</td>
            <td>${customer.cartDeposit}</td>
            <td>${customer.email}</td>
            <td>${customer.phoneNumber}</td>
            <td>${customer.purchaseDate}</td>
            <td>${customer.lastVisit}</td>
            <td>${customer.trainer.surname}</td>
            <td>${customer.bucklet.name}</td>
            <td>${customer.comment}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
