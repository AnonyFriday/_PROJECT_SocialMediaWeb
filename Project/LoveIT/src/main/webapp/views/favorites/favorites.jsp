<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/27/2024
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="DN" tagdir="/WEB-INF/tags/" %>

<DN:GenericPage
        pageStyleUrl="${pageContext.request.contextPath}/css/favorites/favorites.css">
    <main>
        <div class="search-bar">
            <form action="${pageContext.request.contextPath}/people-zone" method="get">
                <input type="hidden" name="action" value="search">
                <label>
                    <input type="text" name="keyword" placeholder="e.g. Sarah">
                </label>
            </form>
        </div>
        <div class="favorite-posts-grid">
            <c:forEach var="post" items="${favorite_posts}">
                <jsp:include page="c-favorite-post.jsp">
                    <jsp:param name="p_id" value="${post.id}" />
                    <jsp:param name="p_image_url" value="${post.imageUrl}" />
                    <jsp:param name="p_content" value="${post.content}" />
                    <jsp:param name="u_image_url" value="${post.user.imageUrl}"/>
                    <jsp:param name="u_age" value="${post.user.age}"/>
                    <jsp:param name="u_usn" value="${post.user.fullName}"/>
                    <jsp:param name="u_nickname" value="${post.user.nickName}"/>
                    <jsp:param name="u_gender" value="${post.user.gender.name}"/>
                    <jsp:param name="u_pref_gender" value="${post.user.preferenceGender.name}"/>
                </jsp:include>
            </c:forEach>
        </div>
    </main>
</DN:GenericPage>