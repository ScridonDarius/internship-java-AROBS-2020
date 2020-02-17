<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<html>
<head>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title>Product Page</title>
</head>
<body>
<div class="container">
 <table class="table table-dark">
 <thead>

    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <td>Quantity</td>
        <th>Buy</th>
    </tr>
 </thead>
  <tbody>
    <c:forEach var="product" items="${products}">
        <form action="cart" method="POST">
            <tr>
                <input type="hidden" value="${product.id}" name="productId">
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td style = width: 50%><input type="text" placeholder="quantity" name="quantity" required="required"></td>


                <td align="center">
                    <button type="submit">Add To Cart</button>

<%--  <a href="${pageContext.request.contextPath}/cart?&action=buy&id=${item.product.id}">Add To Cart</a>--%>
                </td>
            </tr>
        </form>
    </c:forEach>
 </tbody>
</table>
</div>
</body>
</html>
