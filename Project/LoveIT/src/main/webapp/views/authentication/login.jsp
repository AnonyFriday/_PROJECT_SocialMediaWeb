
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="DN" tagdir="/WEB-INF/tags/" %>

<DN:GenericPage pageStyleUrl="${pageContext.request.contextPath}/css/welcome/welcome.css">

    <main>
        <div id="login">
            <h3 class="text-center text-white pt-5">Login form</h3>
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">

                            <form id="login-form" class="form" action="login" method="post">
                                <h3 class="text-center text-info">Login</h3>
                                <c:set var="cookie" value="${pageContext.request.cookies}" />
                                <div class="form-group">
                                    <label for="username" class="text-info">Email:</label><br>
                                    <input type="email" name="email" id="username" class="form-control" value="${cookie.COOKIE_EMAIL.value}">
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="remember-me" class="text-info"><span>Remember me</span></label>
                                    <input id="remember-me" name="remember-me" type="checkbox" ${cookie.COOKIE_IS_REMEMBER_ME.value != null? 'checked': ''} />
                                </div>

                                <input name="action" value="check" hidden="true"/>
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">

                                <div id="register-link" class="text-right">
                                    <a href="register" class="text-info">Register here</a>
                                </div>

                                <c:if test="${not empty requestScope.error}">
                                    <p style="color: red">${requestScope.error}</p>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

</DN:GenericPage>
