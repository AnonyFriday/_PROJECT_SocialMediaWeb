<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/27/2024
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/people-zone/c-post.css">
        <title></title>
    </head>
    <body>
        <div class="post">
            <div class="top-area">
                <div class="top-left-area">
                    <img src="${param.user_image_url}" alt="User Image">
                    <div class="info">
                        <div>
                            <p>${param.user_name}</p>
                            <p>Age: ${param.user_age}</p>
                        </div>
                        <div>
                            <p>${param.user_nickname}</p>
                        </div>
                    </div>
                </div>
                <div class="top-right-area">
                    <form action="${pageContext.request.contextPath}/people-zone?action=favorite&post_id=${param.post_id}" method="post">
                        <button type="submit">
                            <c:choose>
                                <c:when test="${is_favorite}">
                                    <img src="${pageContext.request.contextPath}/assets/img/heart_on.png" alt="Favorite button">
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/assets/img/heart_off.png" alt="Favorite button">
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                    <div>
                        <p>I am</p>
                        <p>${param.user_gender}</p>
                        <p>Looking for</p>
                        <p>${param.user_preference_gender}</p>
                    </div>
                </div>
            </div>
            <div class="content_area">
                <p>${param.content}</p>
                <img src="${param.image_url}" alt="Post image">
            </div>
            <hr>
            <div class="comment-area">
                <c:choose>
                    <c:when test="${not empty param.tcomment_u_img_url}">
                        <img src="${param.tcomment_u_img_url}" alt="Comment avatar">
                        <div class="text">
                            <p>${param.tcomment_usn}</p>
                            <p>${param.tcomment_content}</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Nobody had commented yet!</p>
                    </c:otherwise>
                </c:choose>

                <form action="${pageContext.request.contextPath}/people-zone" method="get">
                    <input type="hidden" name="action" value="post_details">
                    <input type="hidden" name="post_id" value="${param.post_id}">
                    <button type="submit">Discuss Now ></button>
                </form>
            </div>
        </div>
    </body>
</html>