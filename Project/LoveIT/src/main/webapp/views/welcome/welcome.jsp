<%-- 
    Document   : welcome
    Created on : Feb 27, 2024, 2:53:22 PM
    Author     : duyvu
--%>

<%@taglib prefix="DN" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<DN:GenericPage pageTitle="welcome" 
                pageStyleUrl="${pageContext.request.contextPath}/css/index.css">

    <main>
        <a href="/login">Already a member?</a>

        <div>
            <img src="/assets/KhanhLeTutTut.png" alt="profile-picture" width="200" height="300"/>

            <form method="GET" action="WelcomeController">
                <div>
                    <label class="">I'm a woman</label>
                    <input type="text" name="gender" value="woman">I'm a woman</input>
                </div>

                <div>
                    <label>I'm a man</label>
                    <input type="text" name="gender" value="man">I'm a woman</input>
                </div>
            </form>

            <a href="">Others</a>
        </div>
    </main>

</DN:GenericPage>

