<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title>Cart Page</title>
</head>
<body>
<div class="container">
<%--<c:if test="${qtyErrorMessage}">--%>
<%--    <p style="color: red">${qtyErrorMessage}</p>--%>
<%--</c:if>--%>

<table class="table table-dark">
 <thead>
    <tr>
        <th>Option</th>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Sub Total</th>
    </tr>
    </thread>
    <tbody>
    <c:set var="total" value="0"></c:set>
    <c:forEach var="product" items="${sessionScope.cart}">
        <c:set var="total" value="${total + product.price * product.quantity}"></c:set>
        <tr>
            <td align="center">

                <a href="${pageContext.request.contextPath}/cart?action=remove&id=${product.id}"

                   onclick="return confirm('Are you sure?')">Remove</a>
            </td>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
            <td>${product.price * product.quantity}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="6" align="right">Total</td>
        <td>${total}</td>
    </tr>
</table>
</tbody>

<br>
<a href="${pageContext.request.contextPath}/products">Continue Shopping</a>
</div>
</body>
</html>