<%@page import="com.group03.loveit.models.user.EAccountRole"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/26/2024
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="DN" tagdir="/WEB-INF/tags/" %>


<DN:GenericPage pageStyleUrl="${pageContext.request.contextPath}/assets/css/styles.css">
    <main>
        <!-- Create post -->
        <div class="new-post" id="new-post">
            <div class="card-body my-4">
                <form action="${pageContext.request.contextPath}/people-zone?action=create_post" method="post">
                    <div class="container card-container card-border shadow">
                        <div class="row">
                            <div class="col-md-12 col-lg-12 ps-4 w-75">
                                <c:choose>
                                    <c:when test="${sessionScope.SESSION_USER ne null}">
                                        <a class="nav-link" style="display: inline-block;" href="${pageContext.request.contextPath}/user-profile">
                                            <img class="card-border" height="45px" width="45px" src="${sessionScope.SESSION_USER.imageUrl}">
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="nav-link" style="display: inline-block;" href="${pageContext.request.contextPath}/login">
                                            <img class="card-border" height="45px" width="45px" src="${pageContext.request.contextPath}/assets/img/Default_pfp.svg">
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                                <input type="text" name="content" class="w-75 border-bottom border-3" placeholder="Make a new post...">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-8 offset-lg-1">
                                <p class="fs-6 fw-normal w-auto">Add an image here:<input type="text" name="imageUrl" class="ms-2 mt-2 w-50 border" placeholder="Image Url..."></p>
                            </div>
                            <div class="col">
                                <button class="btn d-flex align-items-center fs-4 p-2 px-3 shadow border border-3 border-dark-subtle" type="submit">
                                    Post <i class="fa fa-paper-plane ms-2"></i>  
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
         <div class="d-flex justify-content-end mt-4" style="margin-right: 15%;">
            <form action="${pageContext.request.contextPath}/people-zone" class="d-flex" method="get">
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyword" placeholder="e.g. Sarah">
                <button type="submit" class="trans-button"><i class="fa fa-search"></i></button>
            </form>
        </div> 
        <!-- Posts -->  
        <div class="mt-5">
            <c:forEach var="post" items="${posts}">
                <jsp:include page="post.jsp">
                    <jsp:param name="post_id" value="${post.id}" />
                    <jsp:param name="user_id" value="${post.user.id}" />
                    <jsp:param name="user_image_url" value="${post.user.imageUrl}" />
                    <jsp:param name="user_name" value="${post.user.fullName}" />
                    <jsp:param name="user_nickname" value="${post.user.nickName}" />
                    <jsp:param name="user_age" value="${post.user.age}" />
                    <jsp:param name="user_gender" value="${post.user.gender.name}" />
                    <jsp:param name="user_preference_gender" value="${post.user.preferenceGender.name}" />
                    <jsp:param name="content" value="${post.content}" />
                    <jsp:param name="image_url" value="${post.imageUrl}" />
                    <jsp:param name="tcomment_u_img_url" value="${post.topComment.user.imageUrl}" />
                    <jsp:param name="tcomment_usn" value="${post.topComment.user.fullName}" />
                    <jsp:param name="tcomment_content" value="${post.topComment.content}" />
                    <jsp:param name="is_favorite" value="${post.isFavorite}" />
                </jsp:include>
            </c:forEach>
        </div>

    </main>

    <script>
        // Initialize variables
        let page = 1;
        let isLoading = false;
        const contextPath = "${pageContext.request.contextPath}";
        let fetchedPostIds = [];

        // Add the IDs of the initial posts to the fetchedPostIds array
        <c:forEach var="post" items="${posts}">
            fetchedPostIds.push(${post.id});
        </c:forEach>

        // Event listener for scroll event
        window.onscroll = function() {
            // Check if the user has scrolled to the bottom of the page
            if (!isLoading && (window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
                isLoading = true;
                loadMorePosts();
            }
        };

        // Function to load more posts
        function loadMorePosts() {
            console.log('Loading more posts...');
            page++;
            // Send a POST request to the server to fetch more posts
            fetch(contextPath + '/people-zone?action=fetch&page=' + page, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(fetchedPostIds),
            })
                .then(response => response.json())
                .then(data => {
                    // Loop through the posts returned by the server
                    data.forEach(post => {
                        fetchedPostIds.push(post.id);
                        // Create the post element
                        const newPost = document.createElement('div');
                        newPost.className = 'post';

                        // Create the top area of the post
                        const topArea = document.createElement('div');
                        topArea.className = 'top-area';

                        // Create the top left area of the post
                        const topLeftArea = document.createElement('div');
                        topLeftArea.className = 'top-left-area';

                        // Create the user image
                        const userImage = document.createElement('img');
                        userImage.src = post.user.imageUrl;
                        userImage.alt = 'User Image';

                        // Create the info area of the post
                        const info = document.createElement('div');
                        info.className = 'info';

                        // Create the user name, age, and nickname elements
                        const userName = document.createElement('p');
                        userName.textContent = post.user.fullName;
                        const userAge = document.createElement('p');
                        userAge.textContent = 'Age: ' + post.user.age;
                        const userNickName = document.createElement('p');
                        userNickName.textContent = post.user.nickName;

                        // Append the user name, age, and nickname to the info area
                        info.appendChild(userName);
                        info.appendChild(userAge);
                        info.appendChild(userNickName);

                        // Append the user image and info area to the top left area
                        topLeftArea.appendChild(userImage);
                        topLeftArea.appendChild(info);

                        // Create the top right area of the post
                        const topRightArea = document.createElement('div');
                        topRightArea.className = 'top-right-area';

                        // Create the favorite button
                        const favoriteButton = document.createElement('button');
                        favoriteButton.type = 'submit';
                        favoriteButton.innerHTML = post.isFavorite ?
                            '<img src="${pageContext.request.contextPath}/assets/img/heart_on.png" alt="Favorite button">' :
                            '<img src="${pageContext.request.contextPath}/assets/img/heart_off.png" alt="Favorite button">';
                        favoriteButton.onclick = function() {
                            // Create form
                            let form = document.createElement('form');
                            form.method = 'POST';
                            form.action = contextPath + '/people-zone?action=favorite&post_id=' + post.id;

                            // Append form to body
                            document.body.appendChild(form);

                            // Submit form
                            form.submit();

                            // Remove form from body
                            document.body.removeChild(form);
                        };
                        topRightArea.appendChild(favoriteButton);

                        // Create the user gender and preference gender elements
                        const userGender = document.createElement('p');
                        userGender.textContent = 'I am ' + post.user.gender.name;
                        const userPreferenceGender = document.createElement('p');
                        userPreferenceGender.textContent = 'Looking for ' + post.user.preferenceGender.name;

                        // Append the user gender and preference gender to the top right area
                        topRightArea.appendChild(userGender);
                        topRightArea.appendChild(userPreferenceGender);

                        // Append the top left and right areas to the top area
                        topArea.appendChild(topLeftArea);
                        topArea.appendChild(topRightArea);

                        // Create the content area of the post
                        const contentArea = document.createElement('div');
                        contentArea.className = 'content_area';

                        // Create the content and post image elements
                        const content = document.createElement('p');
                        content.textContent = post.content;
                        const postImage = document.createElement('img');
                        postImage.src = post.imageUrl;
                        postImage.alt = 'Post image';

                        // Append the content and post image to the content area
                        contentArea.appendChild(content);
                        contentArea.appendChild(postImage);

                        // Create the comment area of the post
                        const commentArea = document.createElement('div');
                        commentArea.className = 'comment-area';

                        // Create the top comment element
                        const topComment = document.createElement('p');
                        topComment.textContent = post.topComment ? post.topComment.content : 'Nobody had commented yet!';
                        commentArea.appendChild(topComment);

                        // Create the discuss now button
                        const discussNowButton = document.createElement('button');
                        discussNowButton.textContent = 'Discuss Now >';
                        discussNowButton.onclick = function() {
                            window.location.href = contextPath + '/people-zone?action=post_details&post_id=' + post.id;
                        };
                        commentArea.appendChild(discussNowButton);

                        // Append the top area, content area, and comment area to the post
                        newPost.appendChild(topArea);
                        newPost.appendChild(contentArea);
                        newPost.appendChild(commentArea);

                        // Append the post to the posts container
                        document.getElementById('posts-container').appendChild(newPost);
                    });
                    return fetchedPostIds;
                })
                .then(fetchedPostIds => {
                    isLoading = false;
                    console.log('Fetched post IDs:', fetchedPostIds);
                });
        }
    </script>
</DN:GenericPage>