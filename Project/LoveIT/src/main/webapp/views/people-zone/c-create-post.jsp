<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/27/2024
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/people-zone/c-create-post.css">
        <title></title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/people-zone?action=create_post" method="post">
            <div class="create_post">
                <label>
                    <input type="text" name="content" placeholder="Enter text here...">
                </label>
                <div class="sub-component">
                    <label>Upload an image:</label>
                    <label>
                        <input type="text" name="imageUrl" placeholder="Image URL...">
                    </label>
                    <button type="button">Browse...</button>
                    <input type="submit" value="Post">
                </div>
            </div>
        </form>
    </body>
</html>