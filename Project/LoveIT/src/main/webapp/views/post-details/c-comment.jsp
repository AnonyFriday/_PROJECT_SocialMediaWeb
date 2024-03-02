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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/post-details/c-comment.css">
        <title></title>
    </head>
    <body>
        <div class="comment-div">
            <img src="${param.user_image_url}" alt="User Image">
            <div>
                <p>${param.user_name}</p>
                <p>${param.content}</p>
            </div>
            <button type="submit">Reply</button>
        </div>
    </body>
</html>