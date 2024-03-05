<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>LoveIt</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/people-zone/people-zone.css">
    </head>
    <body>
        <jsp:include page="../../header.html" />
        <div class="search-bar">
            <form action="${pageContext.request.contextPath}/search" method="get">
                <label>
                    <input type="text" name="query" placeholder="e.g. Sarah">
                </label>
            </form>
        </div>
        <jsp:include page="c-create-post.jsp" />
        <hr>
        <c:forEach var="post" items="${posts}">
            <jsp:include page="c-post.jsp">
                <jsp:param name="post_id" value="${post.id}" />
                <jsp:param name="user_image_url" value="${post.user.imageUrl}" />
                <jsp:param name="user_name" value="${post.user.fullName}" />
                <jsp:param name="user_nickname" value="${post.user.nickName}" />
                <jsp:param name="user_age" value="${post.user.age}" />
                <jsp:param name="user_gender" value="${post.user.gender.name}" />
                <jsp:param name="user_preference_gender" value="${post.user.preferenceGender.name}" />
                <jsp:param name="content" value="${post.content}" />
                <jsp:param name="image_url" value="${post.imageUrl}" />
                <jsp:param name="tcomment_u_img_url" value="${post.topComment.user.imageUrl}" />
                <jsp:param name="tcomment_usn" value="${post.topComment.user.fullName}" />
                <jsp:param name="tcomment_content" value="${post.topComment.content}" />
            </jsp:include>
        </c:forEach>
    </body>
</html>