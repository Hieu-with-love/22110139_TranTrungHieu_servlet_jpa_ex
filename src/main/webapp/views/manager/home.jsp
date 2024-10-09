<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 9/10/2024
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Manager Home</h1>
    <c:if test="${message==false}"> Login fail</c:if>
    <c:if test="${message==true}"> Hello ${username}</c:if>
    <li>
        <a href="${pageContext.request.contextPath }/logout">Đăng Xuất</a>
    </li>
</body>
</html>
