<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07.07.2018
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal List</title>
    <style>
        .exceeded {
            color: red;
        }

        .normal {
            color: green;
        }
    </style>
</head>
<body>
<section>
    <table cellpadding="5" border="black">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded': 'normal'}">
                <th><%=TimeUtil.toString(meal.getDateTime())%> </th>
                <th>${meal.description}</th>
                <th>${meal.calories}</th>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
