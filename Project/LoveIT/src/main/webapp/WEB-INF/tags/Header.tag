<%@tag description="Page Header" pageEncoding="UTF-8" isELIgnored="false"%>

<%-- Constants String --%>
<%
    final String header_people_zone = "People Zones";
    final String url_people_zone = request.getContextPath() + "/people-zone";

    final String header_favorites = "Favorites";
    final String url_favorites = request.getContextPath() + "/favorites";
%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="logoUrl" required="true" %>

<%-- any content can be specified here e.g.: --%>
<header class="h">
    <h1 class="header__logo-box">
        <span class="header__logo-text">LoveIT</span>
        <img class="header__logo-img logo" alt="Website's Logo" src="${logoUrl}" />
    </h1>

    <nav class="header__nav">
        <a class="btn btn--animated" href="<%= url_people_zone%>" ><%= header_people_zone%></a>
        <a class="btn btn--animated" href="<%= url_favorites%>"><%= header_favorites%></a>
    </nav>
</header>