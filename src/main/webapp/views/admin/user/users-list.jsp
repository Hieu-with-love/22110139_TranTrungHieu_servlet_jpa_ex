<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/30/2024
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<a href="${pageContext.request.contextPath}/admin/register">Add Category</a>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
        <th>Day of bird</th>
        <th>Gender</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach var="user" items="${users}" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index + 1}</td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.dayOfBird}</td>
            <td>${user.gender}</td>
            <td>
                <c:if test="${user.active == 1}">
                    <span class="label label-success">Active</span>
                </c:if>
                <c:if test="${user.active == 0}">
                    <span class="label label-danger">Inactive</span>
                </c:if>
            </td>
            <td>
                <a href="<c:url value='/admin/category/edit?id=${user.id}'/>" class="center">Sửa</a>
                |
                <a href="<c:url value='/admin/category/delete?id=${user.id}'/>" class="center">Xóa</a>
            </td>
        </tr>
    </c:forEach>

</table>
