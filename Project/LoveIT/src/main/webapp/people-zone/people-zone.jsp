<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/27/2024
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>People Zone</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        .search-bar {
            text-align: right;
        }
    </style>
</head>
<body>
    <jsp:include page="../header.html" />
    <div class="search-bar">
        <form action="/search" method="get">
            <input type="text" name="query" placeholder="e.g. Sarah">
        </form>
    </div>
    <jsp:include page="create-post.jsp" />
    <hr>
    <c:forEach var="post" items="${posts}">
        <jsp:include page="post.jsp">
            <jsp:param name="user_image_url" value="${post.user.imageUrl}" />
            <jsp:param name="user_name" value="${post.user.fullName}" />
            <jsp:param name="user_nickname" value="${post.user.nickName}" />
            <jsp:param name="user_age" value="${post.user.age}" />
            <jsp:param name="user_gender" value="${post.user.gender.name}" />
            <jsp:param name="user_preference_gender" value="${post.user.preferenceGender.name}" />
            <jsp:param name="content" value="${post.content}" />
            <jsp:param name="image_url" value="${post.imageUrl}" />
        </jsp:include>
    </c:forEach>
</body>
</html>