<%@ page import="service.UserService" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 03.05.2020
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>All Users List</title>
</head>
<body>
    <p><div style="text-align: center;">
        <h1>All User List</h1>
        <%
            UserService userService = new UserService();
            List<User> listUser = userService.getAllUsers();
            System.out.println("<br>hhhg");
            request.setAttribute("listUser", listUser);
            for (User a: listUser) {
                Long id = a.getId();
                String name = a.getName();
                Long age = a.getAge();
                String email = a.getEmail();
                System.out.println("<br> "+id+" "+name+" "+age+" "+email+" ");
            }
        %>
    </div></p>
    <div align="center">
        <table border="1" cellpadding="5">
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
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.age}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
