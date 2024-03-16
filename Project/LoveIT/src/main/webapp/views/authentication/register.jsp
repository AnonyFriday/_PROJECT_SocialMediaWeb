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
    <form action="register" method="POST">
        <div>
            <!-- Gender dropdown -->
            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <c:forEach items="${requestScope.genders}" var="gender">
                    <option value="${gender.id}">${gender.name}</option>
                </c:forEach>
            </select>

            <!-- Preference Gender dropdown -->
            <label for="preferenceGender">Preference Gender:</label>
            <select id="gender" name="preferenceGender">
                <c:forEach items="${requestScope.genders}" var="gender">
                    <option value="${gender.id}">${gender.name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Full Name text field -->
        <div>
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" placeholder="Full Name">
        </div>

        <div>
            <label for="fullName">Nick Name:</label>
            <input type="text" id="fullName" name="nickName" placeholder="Nick Name">
        </div>

        <!-- Email text field -->
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Email">
        </div>

        <!-- Password text field -->
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Password">
        </div>

        <!-- Retype Password text field -->
        <div>
            <label for="retypePassword">Retype Password:</label>
            <input type="password" id="retypePassword" name="retypePassword" placeholder="Retype Password">
        </div>

        <!-- Birthdate calendar -->
        <div>
            <label for="birthdate">Birthdate:</label>
            <input type="date" id="birthdate" name="dob">
        </div>

        <!-- Button to trigger the registration -->
        <input name="action" value="check" hidden="true"/>
        <button type="submit">Register</button>

        <c:if test="${not empty requestScope.success}" >
            <p>${requestScope.success}</p>
        </c:if>
    </form>

</DN:GenericPage>
