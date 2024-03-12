<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 3/2/2024
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/post-details/c-comment.css">
        <script>
            function showReplyForm(event) {
                const replyButton = event.target;
                const commentDiv = replyButton.parentElement;
                const replyForm = commentDiv.nextElementSibling;
                replyForm.style.display = replyForm.style.display === 'block' ? 'none' : 'block';
            }
        </script>
        <title></title>
    </head>
    <body>
        <div class="comment-div">
            <img src="${param.user_image_url}" alt="User Image">
            <div>
                <p>${param.user_name}</p>
                <p>${param.content}</p>
            </div>
            <button type="button" class="reply-button" onclick="showReplyForm(event)">Reply</button>
        </div>
        <div class="reply-form" style="display: none;">
            <jsp:include page="c-create-reply.jsp">
                <jsp:param name="post_id" value="${param.post_id}"/>
                <jsp:param name="comment_id" value="${param.comment_id}"/>
            </jsp:include>
        </div>
        <div class="replies">
            <c:forEach var="reply" items="${replies}">
                <jsp:include page="c-reply.jsp">
                    <jsp:param name="rep_u_img_url" value="${reply.user.imageUrl}" />
                    <jsp:param name="rep_usn" value="${reply.user.fullName}" />
                    <jsp:param name="rep_content" value="${reply.content}" />
                </jsp:include>
            </c:forEach>
        </div>
    </body>
</html>