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
    </main>
</DN:GenericPage>