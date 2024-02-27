<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/27/2024
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>People Zone</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        .search-bar {
            text-align: right;
        }
    </style>
</head>
<body>
    <jsp:include page="../header.html" />
    <div class="search-bar">
        <form action="/search" method="get">
            <input type="text" name="query" placeholder="e.g. Sarah">
        </form>
    </div>
    <jsp:include page="create_post.jsp" />
    <hr>
    <jsp:include page="post.jsp" />
    <jsp:include page="post.jsp" />
    <jsp:include page="post.jsp" />
    <jsp:include page="post.jsp" />
</body>
</html>