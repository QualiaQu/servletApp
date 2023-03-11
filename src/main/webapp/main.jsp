<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <title>FileStorage</title>
</head>
<body>
<p>${date}</p>
<h1>${name}</h1>
<hr/>
<img src="https://cdn-icons-png.flaticon.com/512/7778/7778637.png" width="16" height="16"><a href="?path=${back}">Вверх</a>
<table>
    <tr>
        <th></th>
        <th>Файл</th>
        <th>Размер</th>
        <th>Дата</th>
    </tr>
    <c:forEach var="file" items="${temp}">
        <tr>
            <c:choose>
                <c:when test="${file.isDirectory()}">
                    <td>
                        <img src="https://cdn-icons-png.flaticon.com/512/7525/7525173.png" width="16" height="16" alt="directory">
                    </td>
                    <td>
                        <a href="?path=${file.getLink()}">${file.getFileName()}</a>
                    </td>

                </c:when>
                <c:otherwise>
                    <td>
                        <img src="https://cdn-icons-png.flaticon.com/512/7525/7525161.png" width="16" height="16" alt="file">
                    </td>
                    <td>
                        <a href="download?path=${file.getLink()}">${file.getFileName()}</a>
                    </td>

                </c:otherwise>
            </c:choose>
            <td>${file.getFileSize()}</td>
            <td>${file.getDate()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>