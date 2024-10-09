<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/30/2024
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>

<form action="${pageContext.request.contextPath}/admin/category/add" method="post" enctype="multipart/form-data">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>

    <label for="image">Link Image:</label><br>
    <input type="text" id="image" name="image"><br><br>
    <label for="imageUpload">Upload Image:</label><br>
    <input type="file" onchange="chooseFile(this)" id="imageUpload" name="imageUpload"><br><br>

    <p>Status:</p>
    <input type="radio" id="statuson" name="status" value="1" checked>
    <label for="statuson">Active</label><br>
    <input type="radio" id="statusoff" name="status" value="0">
    <label for="statusoff">Inactive</label><br><br>

    <input type="submit" value="Insert">
</form>
