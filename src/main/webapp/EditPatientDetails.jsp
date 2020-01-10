<%@ page import="global.coda.hopsitalmanagement.patientdetails.model.Patient" %>
<%@ page import="global.coda.hopsitalmanagement.enums.ImplEnum" %>
<%@ page import="global.coda.hopsitalmanagement.svc.PatientServices" %><%--
  Created by IntelliJ IDEA.
  User: VC
  Date: 1/7/2020
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<jsp:include page="Logout.jsp"/>

<jsp:useBean id="patient" scope="request" class="global.coda.hopsitalmanagement.patientdetails.model.Patient" type="global.coda.hopsitalmanagement.patientdetails.model.Patient"/>

<h1 align="center">Edit Patient Details</h1>
<form action="UpdateServlet" method="post">
    <table align="center">
        <tr>
            <td>
                Username
            </td>
            <td>
                <input type="text" name="username" value= ${patient.username}>
            </td>
        </tr>
        <tr>
            <td>
                email
            </td>
            <td>
                <input type="text" name="email" value= ${patient.email}>>
            </td>
        </tr>
        <tr>
            <td>
                Password
            </td>
            <td>
                <input type="password" name="password" value= ${patient.password}>
            </td>
        </tr>
        <tr>
            <td>
                Age
            </td>
            <td>
                <input type="text" name="age" value= ${patient.age}>
            </td>
        </tr>
        <tr>
            <td>
                Area
            </td>
            <td>
                <input type="text" name="area" value= ${patient.area}>
            </td>
        </tr>
        <tr>
            <td>
                City
            </td>
            <td>
                <input type="text" name="city" value= ${patient.city}>
            </td>
        </tr>
        <tr>
            <td>
                State
            </td>
            <td>
                <input type="text" name="state" value= ${patient.state}>
            </td>
        </tr>

    </table>
    <input type="submit">

</form>

</body>
</html>
