<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Employees</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Employees</h2>
    <a href="employee?action=create">Add Employee</a><br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>№</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Birthday</th>
            <th>Date of start</th>
            <th>Developer level</th>
            <th>English level</th>
            <th>Skype</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${employees}" var="empl">
            <jsp:useBean id="empl" scope="page" type="com.kosshit.anderse.task2_3.model.Employee"/>
            <tr>
                <td>${empl.employeeId}</td>
                <td>${empl.firstName} ${empl.lastName} ${empl.middleName}</td>
                <td>${empl.email}</td>
                <td>${empl.phoneNumber}</td>
                <td>${empl.birthday}</td>
                <td>${empl.dateOfStart}</td>
                <td>${empl.empLevel}</td>
                <td>${empl.englishLevel}</td>
                <td>${empl.skype}</td>
                <td><a href="employee?action=update&employeeId=${empl.employeeId}">Update</a></td>
                <td><a href="employee?action=delete&employeeId=${empl.employeeId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>