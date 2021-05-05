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
<%--    <jsp:useBean id="empl" type="com.kosshit.anderse.task2_3.model.Employee" scope="request"/>--%>
    <form method="post" action="employee">
        <input type="hidden" name="employeeId" value="${empl.employeeId}">
        <dl>
            <dt>First Name:</dt>
            <dd><input type="text" value="${empl.firstName}" name="firstName" required></dd>
        </dl>
        <dl>
            <dt>Last Name:</dt>
            <dd><input type="text" value="${empl.lastName}" size=40 name="lastName" required></dd>
        </dl>
        <dl>
            <dt>Middle Name:</dt>
            <dd><input type="text" value="${empl.middleName}" name="middleName" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="text" value="${empl.email}" name="email" required></dd>
        </dl>
        <dl>
            <dt>Phone Number:</dt>
            <dd><input type="text" value="${empl.phoneNumber}" name="phoneNumber" required></dd>
        </dl>
        <dl>
            <dt>Birthday:</dt>
            <dd><input type="date" value="${empl.birthday}" name="birthday" required></dd>
        </dl>
        <dl>
            <dt>Date of Start:</dt>
            <dd><input type="date" value="${empl.dateOfStart}" name="dateOfStart" required></dd>
        </dl>
        <dl>
            <dt>Developer level:</dt>
            <dd><input type="text" value="${empl.devLevel}" name="devLevel" required></dd>
        </dl>
        <dl>
            <dt>English level:</dt>
            <dd><input type="text" value="${empl.englishLevel}" name="englishLevel" required></dd>
        </dl>
        <dl>
            <dt>Skype:</dt>
            <dd><input type="text" value="${empl.skype}" name="skype" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>