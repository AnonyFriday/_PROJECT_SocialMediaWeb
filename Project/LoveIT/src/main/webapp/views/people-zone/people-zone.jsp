<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/26/2024
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="DN" tagdir="/WEB-INF/tags/" %>

<DN:GenericPage
    pageStyleUrl="${pageContext.request.contextPath}/css/people-zone/people-zone.css">

    <main>
        <div class="search-bar">
            <form action="${pageContext.request.contextPath}/people-zone" method="get">
                <input type="hidden" name="action" value="search">
                <label>
                    <input type="text" name="keyword" placeholder="e.g. Sarah">
                </label>
            </form>
        </div>
        <jsp:include page="c-create-post.jsp" />
        <hr>
        <div id="posts-container">
            <c:forEach var="post" items="${posts}">
                <c:set var="is_favorite" value="${post.isFavorite}" scope="request" />
                <jsp:include page="c-post.jsp">
                    <jsp:param name="post_id" value="${post.id}" />
                    <jsp:param name="post_is_favorite" value="${post.isFavorite}" />
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
                </jsp:include>
            </c:forEach>
        </div>
    </main>

    <script>
        let page = 1;
        let isLoading = false;
        const contextPath = "${pageContext.request.contextPath}";
        let fetchedPostIds = [];

        window.onscroll = function() {
            if (!isLoading && (window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
                isLoading = true;
                loadMorePosts();
            }
        };

        function loadMorePosts() {
            console.log('Loading more posts...');
            page++;
            fetch(contextPath + '/people-zone?action=fetch&page=' + page, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(fetchedPostIds),
            })
                .then(response => response.json())
                .then(data => {
                    data.forEach(post => {
                        fetchedPostIds.push(post.id);
                        const newPost = document.createElement('div');
                        newPost.className = 'post';

                        const topArea = document.createElement('div');
                        topArea.className = 'top-area';

                        const topLeftArea = document.createElement('div');
                        topLeftArea.className = 'top-left-area';

                        const userImage = document.createElement('img');
                        userImage.src = post.user.imageUrl;
                        userImage.alt = 'User Image';

                        const info = document.createElement('div');
                        info.className = 'info';

                        const userName = document.createElement('p');
                        userName.textContent = post.user.fullName;

                        const userAge = document.createElement('p');
                        userAge.textContent = 'Age: ' + post.user.age;

                        const userNickName = document.createElement('p');
                        userNickName.textContent = post.user.nickName;

                        info.appendChild(userName);
                        info.appendChild(userAge);
                        info.appendChild(userNickName);

                        topLeftArea.appendChild(userImage);
                        topLeftArea.appendChild(info);

                        const topRightArea = document.createElement('div');
                        topRightArea.className = 'top-right-area';

                        const favoriteButton = document.createElement('button');
                        favoriteButton.type = 'submit';
                        favoriteButton.innerHTML = post.isFavorite ?
                            '<img src="${pageContext.request.contextPath}/assets/img/heart_on.png" alt="Favorite button">' :
                            '<img src="${pageContext.request.contextPath}/assets/img/heart_off.png" alt="Favorite button">';
                        topRightArea.appendChild(favoriteButton);

                        const userGender = document.createElement('p');
                        userGender.textContent = 'I am ' + post.user.gender.name;
                        topRightArea.appendChild(userGender);

                        const userPreferenceGender = document.createElement('p');
                        userPreferenceGender.textContent = 'Looking for ' + post.user.preferenceGender.name;
                        topRightArea.appendChild(userPreferenceGender);

                        topArea.appendChild(topLeftArea);
                        topArea.appendChild(topRightArea);

                        const contentArea = document.createElement('div');
                        contentArea.className = 'content_area';

                        const content = document.createElement('p');
                        content.textContent = post.content;

                        const postImage = document.createElement('img');
                        postImage.src = post.imageUrl;
                        postImage.alt = 'Post image';

                        contentArea.appendChild(content);
                        contentArea.appendChild(postImage);

                        const commentArea = document.createElement('div');
                        commentArea.className = 'comment-area';

                        const topComment = document.createElement('p');
                        topComment.textContent = post.topComment ? post.topComment.content : 'Nobody had commented yet!';
                        commentArea.appendChild(topComment);

                        const discussNowButton = document.createElement('button');
                        discussNowButton.textContent = 'Discuss Now >';
                        commentArea.appendChild(discussNowButton);

                        newPost.appendChild(topArea);
                        newPost.appendChild(contentArea);
                        newPost.appendChild(commentArea);

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