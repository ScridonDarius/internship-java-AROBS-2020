<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
    <title>Product Page</title>
</head>
<body>

<table cellpadding="2" cellspacing="2" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <td>Quantity</td>
        <th>Buy</th>
    </tr>

    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.product.id}</td>
            <td>${item.product.name}</td>
            <td>${item.product.price}</td>
           <td><input type ="text" placeholder="quantity" required="required"></td>

            <td align="center">
                <a href="${pageContext.request.contextPath}/cart?&action=buy&id=${item.product.id}">Add To Cart</a>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>