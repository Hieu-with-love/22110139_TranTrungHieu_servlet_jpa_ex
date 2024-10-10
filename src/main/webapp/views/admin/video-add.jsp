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
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title"><br>

    <label for="poster">Link poster:</label><br>
    <input type="text" id="poster" name="poster"><br>
    <label for="imageUpload">Upload poster:</label><br>
    <input type="file" onchange="chooseFile(this)" id="imageUpload" name="imageUpload"><br><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="title"><br>
    <p>Status:</p>
    <input type="radio" id="statuson" name="status" value="1" checked>
    <label for="statuson">Active</label><br>
    <input type="radio" id="statusoff" name="status" value="0">
    <label for="statusoff">Inactive</label><br><br>
    <p>View:</p>
    <input type="radio" id="viewon" name="status" value="1" checked>
    <label for="viewon">Active</label><br>
    <input type="radio" id="viewoff" name="status" value="0">
    <label for="viewoff">Inactive</label><br><br>


    <input type="submit" value="Insert">
</form>
