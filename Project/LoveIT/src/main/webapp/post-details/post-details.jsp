<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 3/2/2024
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <style>
        .post, .top-area, .top-left-area, .top-right-area, .info > div:first-child, .top-right-area div, .content_area, .comment-area {
            display: flex;
        }

        .post {
            border: 1px solid black;
            padding: 20px;
            width: 50%;
            margin: auto;
            flex-direction: column;
        }

        .top-area, .content_area {
            justify-content: space-between;
        }

        .top-left-area, .top-right-area {
            justify-content: center;
        }

        .top-left-area {
            flex-direction: row;
        }

        .top-left-area img, .comment-area img {
            width: 100px;
            height: 100px;
            margin-right: 20px;
        }

        .top-left-area p, .top-right-area p {
            margin-right: 10px;
        }

        .info {
            flex-direction: column;
        }

        .top-right-area {
            flex-direction: column;
            align-items: flex-end;
        }

        .top-right-area button {
            border: none;
        }

        .content_area img {
            width: 200px;
            height: 200px;
        }

        hr {
            border-top: 1px solid black;
            color: #333;
            background-color: #333;
            height: 1px;
            width: 100%;
        }

        .comment-area {
            align-items: center;
            margin-top: 20px;
        }

        .comment-area .text {
            border: 1px solid black;
            padding: 10px;
            margin: 0 20px;
        }

        .comment-area button {
            margin-left: auto;
        }
    </style>

    <script>
        function toggleImage(img) {
            img.src = img.src.endsWith("heart_off.png") ? "${pageContext.request.contextPath}/assets/heart_on.png" : "${pageContext.request.contextPath}/assets/heart_off.png";
        }
    </script>

    <div class="post">
        <div class="top-area">
            <div class="top-left-area">
                <img src="${pageContext.request.contextPath}/assets/KhanhLeTutTut.png" alt="Avatar">
                <div class="info">
                    <div>
                        <p>${param.user_name}</p>
                        <p>${param.user_age}</p>
                    </div>
                    <div>
                        <p>The king of fingers</p>
                    </div>
                </div>
            </div>
            <div class="top-right-area">
                <button style="align-self: flex-end;" onclick="toggleImage(this.children[0])">
                    <img src="${pageContext.request.contextPath}/assets/heart_off.png" alt="Heart">
                </button>
                <div>
                    <p>I am</p>
                    <p>Demi-guy</p>
                    <p>Looking for</p>
                    <p>Bigender</p>
                </div>
            </div>
        </div>
        <div class="content_area">
            <p>${param.content}</p>
            <img src="${param.image_url}" alt="Post image">
        </div>
        <hr>
        <div class="comment-area">
            <img src="${pageContext.request.contextPath}/assets/miley_cyrus.png" alt="Comment avatar">
            <div class="text">
                <p>Miley Cyrus</p>
                <p>Anh dep trai nha o dau vay?</p>
            </div>
        </div>
    </div>
</body>
</html>
