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
    <h2>${param.action == 'create' ? 'Create feedback' : 'Edit feedback'}</h2>
    <jsp:useBean id="fb" scope="request" type="com.kosshit.anderse.task2_3.model.Feedback"/>
    <form method="post" action="feedback">
            <dd><input type="hidden" value="${fb.feedId}" name="id"></dd>
        <dl>
            <dt>First Name:</dt>
            <dd><input type="text" value="${fb.description}" name="description" required></dd>
        </dl>
        <dl>
            <dt>Date:</dt>
            <dd><input type="date" value="${fb.date}" name="date" required></dd>
        </dl>
<%--        <dl>--%>
<%--            <dt>№ team:</dt>--%>
<%--            <dd><input type="text" name="" required></dd>--%>
<%--        </dl>--%>

        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>