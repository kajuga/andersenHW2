<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Teams</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Teams</h2>
    <a href="team?action=create">Add Team</a><br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>№</th>
            <th>Name</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${teams}" var="team">
            <jsp:useBean id="team" scope="page" type="com.kosshit.anderse.task2_3.model.Team"/>
            <tr>
            <td>${team.teamId}</td>
            <td>${team.teamName}</td>
            <td><a href="team?action=update&teamId=${team.teamId}">Update</a></td>
            <td><a href="team?action=delete&teamId=${team.teamId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>