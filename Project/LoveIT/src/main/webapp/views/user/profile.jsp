<%-- 
    Document   : profile
    Created on : Mar 10, 2024, 11:34:43 AM
    Author     : duyvu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="DN" tagdir="/WEB-INF/tags/" %>

<!DOCTYPE html>
<DN:GenericPage
    pageStyleUrl="">
    <main>
        <div class="container">

            <div class="post__avatar">
                <img src="${pageScope.request.contextPath}/assets/img/post_image.png" width="300" height="300" alt="alt"/>
            </div>

            <form class="row">
                <div class="mb-3">
                    <label for="exampleInputFullName" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="exampleInputFullName" name="fullName">
                </div>
                <div class="mb-3">
                    <label for="exampleInputNickName" class="form-label">Nick Name</label>
                    <input type="text" class="form-control" id="exampleInputNickName" name="nickName">
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
                </div>
                <div class="mb-3">
                    <label for="exampleInputOldPassword1" class="form-label">Old Password</label>
                    <input type="password" class="form-control" id="exampleInputOldPassword1" name="old-pasword">
                </div>
                <div class="mb-3">
                    <label for="exampleInputNewPassword1" class="form-label">New Password</label>
                    <input type="text" class="form-control" id="exampleInputNewPassword1" name="new-pasword">
                </div>
                <div class="mb-3">
                    <label for="exampleInputRetypeNewPassword1" class="form-label">Retype New Password</label>
                    <input type="password" class="form-control" id="exampleInputRetypeNewPassword1" name="retype-new-pasword">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="exampleRange">Age <span id="ageValue">${requestScope.usersession.age}</span></label>
                    <div class="range">
                        <input type="range" class="form-range" id="exampleRange" 
                               min="0" max="100" oninput="updateRangeValue(this.value)"
                               name="age" />
                    </div>
                </div>

<!--                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown link
                    </a>

                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <c:forEach items="${request.genders}" var="gender">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </c:forEach>
                    </ul>
                </div>-->


                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

            <script>
                function updateRangeValue(value) {
                    document.getElementById("ageValue").innerText = value;
                }
            </script>

        </div>
    </main>

</DN:GenericPage>

