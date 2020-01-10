<%--
  Created by IntelliJ IDEA.
  User: VC
  Date: 1/8/2020
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hospital Management</title>
</head>
<body>
<h1 align="center">Login Form</h1>
<form action="LoginServlet" method="post">
    <table align="center">
        <tr>
            <td>
                Email
            </td>
            <td>
                <input type="text" name="email">
            </td>
        </tr>
        <tr>
            <td>
                Password
            </td>
            <td>
                <input type="password" name="password">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
