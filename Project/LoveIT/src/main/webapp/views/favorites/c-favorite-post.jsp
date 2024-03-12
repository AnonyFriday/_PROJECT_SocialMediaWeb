<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 3/11/2024
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/favorites/c-favorite-post.css">
    <title></title>
</head>
<body>
    <div class="post-content">
        <form action="${pageContext.request.contextPath}/favorites?action=remove_fav&post_id=${param.p_id}" method="post">
            <button class="img-button" type="submit">
                <img src="${pageContext.request.contextPath}/assets/img/heart_on.png" alt="Favorite button">
            </button>
        </form>
        <div class="top-div">
            <div class="left-div">
                <img src="${param.u_image_url}" alt="User image">
                <p class="age">Age ${param.u_age}</p>
            </div>
            <div class="right-div">
                <div class="name-nickname">
                    <p>${param.u_usn}</p>
                    <p>${param.u_nickname}</p>
                </div>
                <p class="iam">I am ${param.u_gender}</p>
                <p class="partner">Looking for ${param.u_pref_gender}</p>
            </div>
        </div>
        <div class="post-div">
            <img src="${param.p_image_url}" alt="Post image">
            <p>${param.p_content}</p>
        </div>
        <form action="${pageContext.request.contextPath}/favorites" method="get">
            <input type="hidden" name="action" value="post_details">
            <input type="hidden" name="post_id" value="${param.p_id}">
            <button class="more-button" type="submit">See more</button>
        </form>
    </div>
</body>
</html>