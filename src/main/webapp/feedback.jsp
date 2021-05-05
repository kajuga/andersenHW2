<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>FeedBack</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>FeedBacks</h2>
    <a href="feedback?action=create">Add Feedback</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Description</th>
            <th>Date</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${feedbacks}" var="fb">
            <jsp:useBean id="fb" scope="page" type="com.kosshit.anderse.task2_3.model.Feedback"/>
            <td>${fb.description}</td>
            <td>${fb.date}</td>
            <td><a href="employee?action=update&id=${fb.feedId}">Update</a></td>
            <td><a href="employee?action=delete&id=${fb.feedId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>