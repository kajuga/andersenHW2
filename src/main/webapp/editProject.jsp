<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Employee</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create project' : 'Edit project'}</h2>
    <jsp:useBean id="project" scope="request" type="com.kosshit.anderse.task2_3.model.Project"/>
    <form method="post" action="project">
            <dd><input type="hidden" value="${project.projectId}" name="id"></dd>
        <dl>
            <dt>Project Name:</dt>
            <dd><input type="text" value="${project.nameOfProject}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Customer:</dt>
            <dd><input type="text" value="${project.customer}" name="customer" required></dd>
        </dl>
        <dl>
            <dt>Duration:</dt>
            <dd><input type="text" value="${project.duration}" name="duration" required></dd>
        </dl>
        <dl>
            <dt>Methodology:</dt>
            <dd><input type="text" value="${project.methodology}" name="methodology" required></dd>
        </dl>
        <dl>
            <dt>№ Project Manager:</dt>
            <dd><input type="text" value="${project.projectManager.getEmployeeId()}" name="projectManagerId" required></dd>
        </dl>
        <dl>
            <dt>№ Team:</dt>
            <dd><input type="text" value="${project.team.getTeamId()}" name="teamId" required></dd>
        </dl>

        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>