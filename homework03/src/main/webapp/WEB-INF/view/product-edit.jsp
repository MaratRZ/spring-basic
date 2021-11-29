<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit product..</title>
</head>
    <body>
        <h1>Edit product:</h1>
        <%--@elvariable id="product" type="ru.gb.entity.Product"--%>
        <form method="post" action="edit">
            <input type="hidden" name="id" value="${product.id}">
            <dl>
                <dt>Title</dt>
                <dd><input type="text" name="title" value="${product.title}"></dd>
            </dl>
            <dl>
                <dt>Price</dt>
                <dd><input type="number" step="0.01" min="0" name="cost" value="${product.cost}"></dd>
            </dl>
            <button type="submit">Save</button>
        </form>
<%--        <form:form action="edit" modelAttribute="product">--%>
<%--            <form:hidden path="id" value="${product.id}"/>--%>
<%--            <dl>--%>
<%--                <dt>Title:</dt>--%>
<%--                <dd><form:input path="title"/></dd>--%>
<%--            </dl>--%>
<%--            <dl>--%>
<%--                <dt>Cost:</dt>--%>
<%--                <dd><form:input path="cost"/></dd>--%>
<%--            </dl>--%>
<%--            <button type="submit">Save</button>--%>
<%--        </form:form>--%>
    </body>
</html>
