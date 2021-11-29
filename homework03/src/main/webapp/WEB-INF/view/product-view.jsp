<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View product..</title>
</head>
    <body>
        <h1>View product:</h1>
        <%--@elvariable id="product" type="ru.gb.entity.Product"--%>
        <dl>
            <dt>Id:</dt>
            <dd>${product.id}</dd>
        </dl>
        <dl>
            <dt>Title:</dt>
            <dd>${product.title}</dd>
        </dl>
        <dl>
            <dt>Cost:</dt>
            <dd>${product.cost.doubleValue()}</dd>
        </dl>
        <br/>
        <c:url var="editUrl" value="/product/edit">
            <c:param name="id" value="${product.id}"/>
        </c:url>
        <a href="${editUrl}">Edit</a>
        <br/>
        <c:url var="deleteUrl" value="/product/delete">
            <c:param name="id" value="${product.id}"/>
        </c:url>
        <a href="${deleteUrl}">Delete</a>
    </body>
</html>
