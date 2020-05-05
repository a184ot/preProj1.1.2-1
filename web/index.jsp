<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User List</title>
</head>
<body>

<div align="center">
    <table >
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
              <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td>
                    <button onclick="location.href='edit?id=<c:out value='${user.id}'/>'" ;>Edit</button>
                    &nbsp;&nbsp;&nbsp;
                    <button onclick="location.href='delete?id=<c:out value='${user.id}'/>'">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div style="text-align: center;">
  <h2>
    <button onclick="location.href='new'" ;>Add New User</button>
    &nbsp;&nbsp;&nbsp;
    <button onclick="location.href='list'" ;>List All Users</button>

  </h2>
</div>
</body>
</html>