<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 3/2/2024
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/post-details/c-create-comment.css">
        <title></title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/create-comment" method="post">
            <div class="create-comment-div">
                <label>
                    <input type="text" name="content" placeholder="Your comment...">
                </label>
                <input type="hidden" name="post_id" value="${param.post_id}">
                <button type="submit">Send</button>
            </div>
        </form>
    </body>
</html>