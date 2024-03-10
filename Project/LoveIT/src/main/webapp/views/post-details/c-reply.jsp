
<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 3/8/2024
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/post-details/c-reply.css">
        <title></title>
    </head>
    <body>
        <div class="reply-content">
            <img src="${param.rep_u_img_url}" alt="User Image">
            <div>
                <p>${param.rep_usn}</p>
                <p>${param.rep_content}</p>
            </div>
        </div>
    </body>
</html>