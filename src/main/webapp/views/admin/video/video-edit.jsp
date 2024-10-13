<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/30/2024
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<form action="${pageContext.request.contextPath}/admin/category/edit" method="post" enctype="multipart/form-data">
    <input type="text" id="id" name="id" value="${video.id}" hidden="hidden">

    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="${video.title}"><br>

    <c:if test="${video.poster.substring(0,5) != 'https'}">
        <c:url value="/downloadFile?fname=${video.poster}" var="imgUrl"></c:url>
    </c:if>
    <c:if test="${video.poster.substring(0,5) == 'https'}">
        <c:url value="${video.poster}" var="imgUrl"></c:url>
    </c:if>

    <label for="image">Link Image:</label><br>
    <input type="text" id="image" name="image" value="${video.poster}"><br><br>
    <label for="imageUpload">Upload Image:</label><br>
    <img height="150" width="200" src="${imgUrl}"/>
    <input type="file" onchange="chooseFile(this)" id="imageUpload" name="imageUpload" value="${video.poster}"><br><br>

    <label for="statuson">Status:</label><br>
    <input type="radio" id="statuson" name="status" value="1" ${video.status==1 ? 'checked' : ''}>
    <label for="statuson">Active</label><br>
    <input type="radio" id="statusoff" name="status" value="0" ${video.status == 0 ? 'checked' : ''}>
    <label for="statusoff">Inactive</label><br><br>

    <label for="statuson">Views:</label><br>
    <input type="radio" id="viewon" name="status" value="1" ${video.views==1 ? 'checked' : ''}>
    <label for="viewon">Active</label><br>
    <input type="radio" id="viewoff" name="status" value="0" ${video.views == 0 ? 'checked' : ''}>
    <label for="viewoff">Inactive</label><br><br>

    <input type="submit" value="Edit">
</form>
