<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/30/2024
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<a href="${pageContext.request.contextPath}/admin/video/add">Add Video</a>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Title</th>
        <th>Poster</th>
        <th>Description</th>
        <th>Status</th>
        <th>Views</th>
        <th>Action</th>
    </tr>

    <c:forEach var="vid" items="${videos}" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index + 1}</td>
            <td>${vid.id}</td>
            <td>${vid.title}</td>
            <td>
                <c:if test="${vid.poster.substring(0,5) != 'https'}">
                    <c:url var="imgUrl" value="/downloadFile?fname=${vid.poster}"></c:url>
                </c:if>
                <c:if test="${vid.poster.substring(0,5) == 'https'}">
                    <c:url var="imgUrl" value="${vid.poster}"></c:url>
                </c:if>
                <img height="150" width="200" src="${imgUrl}"/>
            </td>
            <td>${vid.desciption}</td>
            <td>
                <c:if test="${vid.status == 1}">
                    <span class="label label-success">Active</span>
                </c:if>
                <c:if test="${vid.status == 0}">
                    <span class="label label-danger">Inactive</span>
                </c:if>
            </td>
            <td>
                <a href="<c:url value='/admin/video/edit?id=${vid.id}'/>" class="center">Sửa</a>
                |
                <a href="<c:url value='/admin/video/delete?id=${vid.id}'/>" class="center">Xóa</a>
            </td>
        </tr>
    </c:forEach>

</table>
