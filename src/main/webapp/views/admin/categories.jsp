<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/30/2024
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<a href="${pageContext.request.contextPath}/admin/category/add">Add Category</a>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Image</th>
        <th>Id</th>
        <th>Name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <c:forEach var="cate" items="${categories}" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index + 1}</td>
            <td>
                <c:if test="${cate.image.substring(0,5) != 'https'}">
                    <c:url var="imgUrl" value="/downloadFile?fname=${cate.image}"></c:url>
                </c:if>
                <c:if test="${cate.image.substring(0,5) == 'https'}">
                    <c:url var="imgUrl" value="${cate.image}"></c:url>
                </c:if>
                <img height="150" width="200" src="${imgUrl}"/>
            </td>
            <td>${cate.id}</td>
            <td>${cate.name}</td>
            <td>
                <c:if test="${cate.status == 1}">
                    <span class="label label-success">Active</span>
                </c:if>
                <c:if test="${cate.status == 0}">
                    <span class="label label-danger">Inactive</span>
                </c:if>
            </td>
            <td>
                <a href="<c:url value='/admin/category/edit?id=${cate.id}'/>" class="center">Sửa</a>
                |
                <a href="<c:url value='/admin/category/delete?id=${cate.id}'/>" class="center">Xóa</a>
            </td>
        </tr>
    </c:forEach>

</table>
