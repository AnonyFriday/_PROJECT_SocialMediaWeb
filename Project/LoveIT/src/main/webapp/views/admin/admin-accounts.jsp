<%-- 
    Document   : admin-post
    Created on : Mar 17, 2024, 2:57:48 PM
    Author     : duyvu
--%>

<%@page import="com.group03.loveit.models.user.EStatus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="DN" tagdir="/WEB-INF/tags/" %>

<DN:GenericPage
    pageStyleUrl="${pageContext.request.contextPath}/css/admin/admin-accounts.css">

    <main class="management">
        <DN:SideBar />

        <section class="management__table">
            <h2>User Management Data Table</h2>
            <table>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Fullname</th>
                        <th>Nickname</th>
                        <th>Email</th>
                        <th>Age</th>
                        <th>Status</th>
                        <th>Role</th>
                        <th>Gender</th>
                        <th>Preference Gender</th>
                        <th>Created At</th>
                        <th>Image Url</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${accounts}" var="acc">
                        <tr>
                            <td>${acc.id}</td>
                            <td>${acc.fullName}</td>
                            <td>${acc.nickName}</td>
                            <td>${acc.email}</td>
                            <td>${acc.age}</td>
                            <td>${acc.status}</td>
                            <td>${acc.role}</td>
                            <td>${acc.gender.name}</td>
                            <td>${acc.preferenceGender.name}</td>
                            <td>${acc.createdAt}</td>
                            <td>${acc.imageUrl}</td>
                            <td>
                                <form action="accounts?action=flag_acc&id=${acc.id}" method="post">
                                    <button type="submit">${acc.status == EStatus.ACTIVE? "Flag": "Unflag"}</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>



</DN:GenericPage>