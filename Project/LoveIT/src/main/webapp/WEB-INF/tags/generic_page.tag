<%-- 
    Document   : wrapper
    Created on : Mar 3, 2024, 5:39:18 PM
    Author     : duyvu
--%>

<%@tag description="Overall Page Template" pageEncoding="UTF-8"%>
<%@attribute name="pageTitle" required="true" %>
<%@attribute name="pageStyleUrl" required="true" %>

<!DOCTYPE html>
<html>

    <!-- Head-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${pageTitle}</title>
        <link rel="stylesheet" href="${pageStyleUrl}"/>
    </head>

    <!-- Body -->
    <body>

        <!-- Include header -->
        <jsp:include page="../../header.html" flush="true"></jsp:include>

        <!-- Body content -->
        <jsp:doBody />

        <!-- Include footer -->
        <jsp:include page="../../footer.jsp" flush="true"></jsp:include>
    </body>
</html>