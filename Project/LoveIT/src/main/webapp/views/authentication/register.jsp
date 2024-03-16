<%-- 
    Document   : register
    Created on : Mar 12, 2024, 3:06:45 AM
    Author     : duyvu
--%>

<%@page import="com.group03.loveit.models.gender.GenderDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="DN" tagdir="/WEB-INF/tags/" %>

<DN:GenericPage pageStyleUrl="${pageContext.request.contextPath}/css/welcome/welcome.css">

    <!-- Your registration form -->
    <section class="position-relative py-4 py-xl-5">
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-8 col-xl-6 text-center mx-auto">
                    <h1 class="txt-main-col">LoveIT</h1>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-xl-8 offset-xl-2">
                    <h1 class="mx-auto txt-main-col mb-3">Sign up</h1>
                    <form class="text-center p-4 pb-3 shadow border border-3 border-dark-subtle rounded" action="register" method="post">
                        <div class="input-group mb-3"><span class="input-group-text input-group-text input-group-text border-none">I am a</span>
                            <select class="form-select" aria-label="Team options">
                                <option value="" selected="">Choose</option>
                                <c:forEach items="${requestScope.genders}" var="gender">
                                    <option value="${gender.id}">${gender.name}</option>
                                </c:forEach>
                            </select><span class="input-group-text input-group-text input-group-text border-none">, my preference is</span>
                            <select class="form-select" aria-label="Preferences options">
                                <option value="">Choose</option>
                                <c:forEach items="${requestScope.genders}" var="gender">
                                    <option value="${gender.id}">${gender.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="row">
                            <div class="input-group mb-3">
                                <span class="input-group-text required col-3">Full Name</span>
                                <input class="form-control" type="text" name="fullName" placeholder="Enter your name here">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text required col-3">Email</span>
                                <input class="form-control" type="text" name="email" placeholder="Enter your email here">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text required col-3">Date of birth</span>
                                <input class="form-control"  type="date" name="dob">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text required col-3">Password</span>
                                <input class="form-control" type="password" name="password" placeholder="Enter your password here">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text required col-3">Re-type password</span>
                                <input class="form-control" type="password" name="retypePassword" placeholder="Re-type your password here">
                            </div>

                        </div>
                        <div class="row">
                            <button class="btn btn-primary d-block w-100 btn-pink shadow" type="submit">Sign up</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</DN:GenericPage>
