<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/30/2024
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>

<form action="<c:url value="/admin/video/add"/>" method="post">
    <label for="css">Active</label><br>
    <input type="radio" id="on" name="active" value="1">
    <label for="javascript">Hoat dong</label>
    <input type="radio" id="off" name="active" value="0">
    <label for="html">Khoa</label>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description"><br>

    <label for="poster">Poster:</label><br>
    <input type="text" id="poster" name="poster"><br>

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title"><br>

    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views"><br>
    <br><br>

    <label for="cate">Category:</label><br>
    <input type="text" id="cate" name="cate"><br>
    <br><br>

    <input type="submit" value="Insert">
</form>
