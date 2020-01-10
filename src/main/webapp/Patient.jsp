<%@ page import="global.coda.hopsitalmanagement.patientdetails.model.User" %>
<%@ page import="global.coda.hopsitalmanagement.patientdetails.model.Patient" %>
<%@ page import="global.coda.hopsitalmanagement.svc.PatientServices" %>
<%@ page import="global.coda.hopsitalmanagement.enums.ImplEnum" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="global.coda.hopsitalmanagement.applicationconstants.Constants" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="global.coda.hopsitalmanagement.svc.AuthenticationServices" %>
<%@ page import="global.coda.hopsitalmanagement.exception.InvalidException" %><%--
  Created by IntelliJ IDEA.
  User: VC
  Date: 1/7/2020
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PATIENT</title>
</head>
<body>
<jsp:include page="Logout.jsp"/>
<jsp:useBean id="patient" scope="request" class="global.coda.hopsitalmanagement.patientdetails.model.Patient" type="global.coda.hopsitalmanagement.patientdetails.model.Patient"/>

<h1 align="center">Patient Data</h1>
<form action="EditServlet" method="post">
    <table align="center" border=1>
        <tr>
            <td>
                UserId
            </td>
            <td>
                    ${patient.userId}
            </td>
        </tr>
        <tr>
        <td>
            Username
        </td>
        <td>
            ${patient.username}
        </td>
        </tr>
        <tr>
        <td>
            Email
        </td>
        <td>
            ${patient.email}
        </td>
        </tr>
        <tr>
        <td>
            RoleId
        </td>
        <td>
            ${patient.roleId}
        </td>
        </tr>
        <tr>
        <td>
            BranchId
        </td>
        <td>
            ${patient.branchId}
        </td>
        </tr>
        <tr>
        <td>
            Age
        </td>
        <td>
            ${patient.age}
        </td>
        </tr>
        <tr>
        <td>
            Area
        </td>
        <td>
            ${patient.area}
        </td>
        </tr>
        <tr>
        <td>
            City
        </td>
        <td>
            ${patient.city}
        </td>
        </tr>
        <tr>
        <td>
            State
        </td>
        <td>
            ${patient.state}
        </td>
        </tr>

    </table>
    <input type="submit" align="center" value="Edit">


</form>

</body>
</html>
