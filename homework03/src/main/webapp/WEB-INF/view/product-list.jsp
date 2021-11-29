<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Product list..</title>
    </head>
    <body>
        <h1>View product list..</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>TITLE</th>
                <th>PRICE</th>
                <th></th>
                <th></th>
            </tr>
            <%--@elvariable id="product" type="ru.gb.entity.Product"--%>
            <jsp:useBean id="products" scope="request" type="java.util.List"/>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td style="min-width:50px">${product.id}</td>
                    <td style="min-width:120px">${product.title}</td>
                    <td style="min-width:80px">${product.cost.doubleValue()}</td>
                    <td style="min-width:80px">
                        <c:url var="editUrl" value="/product/edit">
                            <c:param name="id" value="${product.id}"/>
                        </c:url>
                        <a href="${editUrl}">Edit</a>
                    </td>
                    <td style="min-width:80px">
                        <c:url var="deleteUrl" value="/product/delete">
                            <c:param name="id" value="${product.id}"/>
                        </c:url>
                        <a href="${deleteUrl}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <c:url var="createUrl" value="/product/create"/>
        <a href="${createUrl}">Add new product</a>
    </body>
</html>