<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/30/2024
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>

<form action="${pageContext.request.contextPath}/admin/register" method="post" enctype="multipart/form-data">
    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username" placeholder="Enter username"><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" placeholder="Enter password"><br>

    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email" placeholder="Enter email"><br>

    <label for="dayOfBird">Day of bird:</label><br>
    <input type="text" id="dayOfBird" name="dayOfBird" placeholder="Enter password"><br>

    <label for="gender">Gender:</label><br>
    <select id="gender" name="gender">
        <option value="Nam">Nam</option>
        <option value="Nữ">Nữ</option>
        <option value="Không xác định">Not</option>
    </select>

    <p>Status:</p>
    <input type="radio" id="statuson" name="active" value="1" checked>
    <label for="statuson">Active</label><br>
    <input type="radio" id="statusoff" name="active" value="0">
    <label for="statusoff">Inactive</label><br><br>

    <button type="submit">Tạo user</button>
</form>
