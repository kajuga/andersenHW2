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
    <h2>${param.action == 'create' ? 'Create team' : 'Edit team'}</h2>
    <jsp:useBean id="team" scope="request" type="com.kosshit.anderse.task2_3.model.Team"/>
    <form method="post" action="team">
            <dd><input type="hidden" value="${team.teamId}" name="id"></dd>
        <dl>
            <dt>Team Name:</dt>
            <dd><input type="text" value="${team.teamName}" name="name" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>