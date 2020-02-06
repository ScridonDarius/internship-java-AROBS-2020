<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

		</tr>
		<c:forEach items="${products}" var="product" >
			<tr>

                <td>${product.id }</td>
				<td>${product.name }</td>
				<td>${product.price }</td>
				<td>${product.quantity }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>