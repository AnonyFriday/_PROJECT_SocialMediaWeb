<%-- 
    Document   : wrapper
    Created on : Mar 3, 2024, 5:39:18 PM
    Author     : duyvu
--%>

<%@tag description="Overall Page Template" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="DN"%>

<%@attribute name="pageTitle" required="true" %>
<%@attribute name="pageStyleUrl" required="true" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- Meta Tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

        <!-- Page Title -->
        <title>Love IT</title>

        <!-- Loto Font -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Madimi+One&display=swap" rel="stylesheet">

        <!-- Bootstrap and Custom SCSS style -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">

    </head>

    <!-- Body -->
    <body>

        <!-- Include header -->
        <DN:Header logoUrl="${pageContext.request.contextPath}/assets/heart_off.png" />

        <!-- Body content -->
        <jsp:doBody />

        <!-- Include footer -->
        <DN:Footer />

        <!-- Include Bootstrap -->
        <script src="${pageContext.request.contextPath}/assets/bootstrap/bootstrap.min.js"></script>
    </body>
</html>