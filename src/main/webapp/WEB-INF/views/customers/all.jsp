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
<div><input type="button" onclick="location.href='/customer/add';" value="NOWY KLIENT" /></div>
<div style="display: flex">
    <div id = "table">
    <table border="1" bgcolor = #b0c4de>
    <tr style="background-color: darkblue; color: white">
        <th>L.p.</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Nr karnetu</th>
        <th>Rodzaj karnetu</th>
        <th>Data zakupu</th>
        <th>Data ważności</th>
        <th>Pozostało wejść</th>
        <th>Ostatnia wizyta</th>
        <th>Uwagi</th>
        <th>
            <div style="display: flex">
                <div><input type="button" onclick="location.href='/trainer/all';" value="TRENERZY" /></div>
                <div><input type="button" onclick="location.href='/bucklet/all';" value="KARNETY" /></div>
                <div><input type="button" value="OBECNOŚĆ" onclick="window.open('/customer/presence')" /></div>
            </div>

        </th>
    </tr>
    <c:forEach items="${customers}" var="customer" begin="0" end="${size}" varStatus="row">
        <c:choose>
        <c:when test="${empty customer.visitsLeft}">
        <tr align="center" bgcolor="red">
            <td>${row.index + 1}</td>
            <td align="left">${customer.name}</td>
            <td align="left">${customer.surname}</td>
            <td>${customer.cartNumber}</td>
            <td>${customer.bucklet.name}</td>
            <td>${customer.purchaseDate}</td>
            <td>${customer.expiryDate}</td>
            <td>${customer.visitsLeft}</td>
            <td>${customer.lastVisit}</td>
            <td>${customer.comment}</td>
            <td>
                <input type="button" onclick="location.href='/customer/details/${customer.clientId}';" value="SZCZEGÓŁY"/>
                <input type="button" onclick="location.href='/customer/renew/${customer.clientId}';" value="ODNÓW"/>
                <input type="button" onclick="location.href='/customer/delete/${customer.clientId}';" value="USUŃ"/>
            </td>
        </tr>
        </c:when>
        <c:otherwise>
            <tr align="center">
                <td>${row.index + 1}</td>
                <td align="left">${customer.name}</td>
                <td align="left">${customer.surname}</td>
                <td>${customer.cartNumber}</td>
                <td>${customer.bucklet.name}</td>
                <td>${customer.purchaseDate}</td>
                <td>${customer.expiryDate}</td>
                <td>${customer.visitsLeft}</td>
                <td>${customer.lastVisit}</td>
                <td>${customer.comment}</td>
                <td>
                    <input type="button" onclick="location.href='/customer/details/${customer.clientId}';" value="SZCZEGÓŁY"/>
                    <input type="button" onclick="location.href='/customer/renew/${customer.clientId}';" value="ODNÓW"/>
                    <input type="button" onclick="location.href='/customer/delete/${customer.clientId}';" value="USUŃ"/>
                </td>
            </tr>
        </c:otherwise>
        </c:choose>
    </c:forEach>
    </table>
    </div>
</div>
</body>
</html>
