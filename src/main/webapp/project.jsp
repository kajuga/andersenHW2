<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Projects</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Projects</h2>
    <a href="project?action=create">Add Project</a><br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>№</th>
            <th>Project Name</th>
            <th>Customer</th>
            <th>Duration</th>
            <th>Methodology</th>
            <th>№ Project Manager</th>
            <th>№ Team</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${projects}" var="proj">
            <jsp:useBean id="proj" scope="page" type="com.kosshit.anderse.task2_3.model.Project"/>
            <tr>
            <td>${proj.projectId}</td>
            <td>${proj.nameOfProject}</td>
            <td>${proj.customer}</td>
            <td>${proj.duration}</td>
            <td>${proj.methodology}</td>
            <td>${proj.projectManager.getEmployeeId()}</td>
            <td>${proj.team.getTeamId()}</td>
            <td><a href="project?action=update&projectId=${proj.projectId}">Update</a></td>
            <td><a href="project?action=delete&projectId=${proj.projectId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>