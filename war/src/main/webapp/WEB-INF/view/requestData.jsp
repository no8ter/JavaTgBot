<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Введите даты для поиска</p>
<form:form method="POST" action="/maven-servlet/mvc/result" modelAttribute="inputDate">
    <table>
        <tr>
            <tr/>
            <form:radiobutton path="range" value="true" />Выбрать диапазон
            <table bgcolor="#faebd7">
                <tr>
                    <td><form:label path="startDate">Start range</form:label></td>
                    <td><form:input path="startDate"/></td>
                </tr>
                <tr>
                    <td><form:label path="endDate">End range</form:label></td>
                    <td><form:input path="endDate"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Go to result"/></td>
                </tr>
            </table>
        </tr>
        <tr>
            <tr/>
            <form:radiobutton path="range" value="false"/>Выбрать дату
            <table bgcolor="#5f9ea0">
                <tr>
                    <td><form:label path="singleDate">Enter date</form:label></td>
                    <td><form:input path="singleDate" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Go to result"/></td>
                </tr>
            </table>
        </tr>
    </table>
</form:form>
</body>
</html>