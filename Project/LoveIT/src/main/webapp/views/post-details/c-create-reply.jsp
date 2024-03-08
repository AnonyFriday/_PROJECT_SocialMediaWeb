<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 3/8/2024
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/post-details/c-create-reply.css">
        <title></title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}\post-details?action=create_reply&post_id=${param.post_id}&parent_cmt_id=${param.comment_id}" method="post">
            <label>
                <input type="text" name="reply_content" placeholder="Your reply...">
            </label>
            <button type="submit">Send</button>
        </form>
    </body>
</html>