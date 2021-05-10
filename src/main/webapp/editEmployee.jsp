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
    <h2>${param.action == 'create' ? 'Create employee' : 'Edit employee'}</h2>
    <jsp:useBean id="employee" scope="request" type="com.kosshit.anderse.task2_3.model.Employee"/>
    <form method="post" action="employee">
            <dd><input type="hidden" value="${employee.employeeId}" name="id"></dd>
        <dl>
            <dt>First Name:</dt>
            <dd><input type="text" value="${employee.firstName}" name="firstName" required></dd>
        </dl>
        <dl>
            <dt>Last Name:</dt>
            <dd><input type="text" value="${employee.lastName}" name="lastName" required></dd>
        </dl>
        <dl>
            <dt>Middle Name:</dt>
            <dd><input type="text" value="${employee.middleName}" name="middleName" required></dd>
        </dl>
        <dl>
            <dt>Short Name:</dt>
            <dd><input type="text" value="${employee.shortName}" name="middleName" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="text" value="${employee.email}" name="email" required></dd>
        </dl>
        <dl>
            <dt>Phone Number:</dt>
            <dd><input type="text" value="${employee.phoneNumber}" name="phoneNumber" required></dd>
        </dl>
        <dl>
            <dt>Birthday:</dt>
            <dd><input type="date" value="${employee.birthday}" name="birthday" required></dd>
        </dl>
        <dl>
            <dt>Date of Start:</dt>
            <dd><input type="date" value="${employee.dateOfStart}" name="dateOfStart" required></dd>
        </dl>
        <dl>
            <dt>Developer level:</dt>
            <dd><input type="text" value="${employee.devLevel}" name="devLevel" required></dd>
        </dl>
        <dl>
            <dt>English level:</dt>
            <dd><input type="text" value="${employee.englishLevel}" name="englishLevel" required></dd>
        </dl>
        <dl>
            <dt>Skype:</dt>
            <dd><input type="text" value="${employee.skype}" name="skype" required></dd>
        </dl>
        <dl>
            <dt>№ Team:</dt>
            <dd><input type="text" name="teamId" value="${employee.team.getTeamId()}" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>