<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 3/2/2024
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="DN" tagdir="/WEB-INF/tags/" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<DN:GenericPage 
    pageStyleUrl="${pageContext.request.contextPath}/css/post-details/post-details.css">

    <main>
        <div class="post">
            <div class="top-area">
                <div class="top-left-area">
                    <img src="${post.user.imageUrl}" alt="Avatar">
                    <div class="info">
                        <div>
                            <p>${post.user.fullName}</p>
                            <p>${post.user.age}</p>
                        </div>
                        <div>
                            <p>${post.user.nickName}</p>
                        </div>
                    </div>
                </div>
                <div class="top-right-area">
                    <form action="${pageContext.request.contextPath}/post-details?action=toggle_favorite&post_id=${post.id}" method="post">
                        <button>
                            <c:choose>
                                <c:when test="${post.isFavorite}">
                                    <img src="${pageContext.request.contextPath}/assets/img/heart_on.png" alt="Heart">
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/assets/img/heart_off.png" alt="Heart">
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                    <div>
                        <p>I am</p>
                        <p>${post.user.gender.name}</p>
                        <p>Looking for</p>
                        <p>${post.user.preferenceGender.name}</p>
                    </div>
                </div>
            </div>
            <div class="content_area">
                <p>${post.content}</p>
                <img src="${post.imageUrl}" alt="Post image">
            </div>
            <hr>
            <div class="comment-area">
                <c:forEach var="comment" items="${comments}">
                    <c:set var="replies" value="${comment.replies}" scope="request" />
                    <jsp:include page="c-comment.jsp">
                        <jsp:param name="post_id" value="${post.id}" />
                        <jsp:param name="comment_id" value="${comment.id}" />
                        <jsp:param name="user_image_url" value="${comment.user.imageUrl}" />
                        <jsp:param name="user_name" value="${comment.user.fullName}" />
                        <jsp:param name="content" value="${comment.content}" />
                    </jsp:include>
                </c:forEach>
            </div>
            <jsp:include page="c-create-comment.jsp">
                <jsp:param name="post_id" value="${post.id}" />
            </jsp:include>
        </div>
    </main>
</DN:GenericPage>