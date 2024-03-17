<%-- 
    Document   : admin-post
    Created on : Mar 17, 2024, 2:57:48 PM
    Author     : duyvu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="DN" tagdir="/WEB-INF/tags/" %>

<DN:GenericPage
    pageStyleUrl="${pageContext.request.contextPath}/css/admin/admin-posts.css">

    <main class="management">
        <DN:SideBar />

        <section class="management__table">
            <h2>Posts Management Data Table</h2>
            <table>
                <thead>
                    <tr>
                        <th>Post ID</th>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Content</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${posts}" var="post">
                        <tr>
                            <td>${post.id}</td>
                            <td>${post.user.id}</td>
                            <td>${post.user.fullName}</td>
                            <td>${post.content}</td>
                            <td>${post.status}</td>
                            <td>${post.createdAt}</td>
                            <td>
                                <form action="posts?action=delete_p&id=${post.id}" method="post">
                                    <button type="submit">Delete</button>
                                </form>
                                <form action="posts?action=flag_p&id=${post.id}" method="post">
                                    <button type="submit">${post.status == 'ACTIVE' ? 'Flag' : 'Unflag'}</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <h2>Comments Management Data Table</h2>
            <table>
                <thead>
                    <tr>
                        <th>Comment ID</th>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Content</th>
                        <th>Post ID</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${comments}" var="comment">
                        <tr>
                            <td>${comment.id}</td>
                            <td>${comment.user.id}</td>
                            <td>${comment.user.fullName}</td>
                            <td>${comment.content}</td>
                            <td>${comment.post.id}</td>
                            <td>${comment.status}</td>
                            <td>${comment.createdAt}</td>
                            <td>
                                <form action="posts?action=delete_cmt&id=${comment.id}" method="post">
                                    <button type="submit">Delete</button>
                                </form>
                                <form action="posts" method="get">
                                    <input type="hidden" name="action" value="flag_cmt" />
                                    <input type="hidden" name="id" value="${comment.id}" />
                                    <button type="submit">${comment.status == 'Active' ? 'Flag' : 'Unflag'}</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>
</DN:GenericPage>