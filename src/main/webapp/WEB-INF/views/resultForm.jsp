<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Results</title>
    <style>
        table, th, td {
            border: 1px solid black;
            align: left;
        }

        div {
            font-size: 25px;
            font-weight: bold;
        }
    </style>
</head>
<c:if test="${not empty agPart}">
    <div>From a to g</div>
    <table>
        <tr>
            <th>Word</th>
            <th>Count</th>
        </tr>
        <c:forEach items="${agPart}" var="entry">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>
    </br>
</c:if>
<c:if test="${not empty hnPart}">
    <div>From h to n</div>
    <table>
        <tr>
            <th>Word</th>
            <th>Count</th>
        </tr>
        <c:forEach items="${hnPart}" var="entry">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>
    </br>
</c:if>
<c:if test="${not empty ouPart}">
    <div>From o to u</div>
    <table>
        <tr>
            <th>Word</th>
            <th>Count</th>
        </tr>
        <c:forEach items="${ouPart}" var="entry">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>
    </br>
</c:if>
<c:if test="${not empty vzPart}">
    <div>From v to z</div>
    <table>
        <tr>
            <th>Word</th>
            <th>Count</th>
        </tr>
        <c:forEach items="${vzPart}" var="entry">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</br></br>
<form action="/" method="get">
    <button type="submit">Upload new files</button>
</form>

</body>
</html>