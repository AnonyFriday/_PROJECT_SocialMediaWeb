<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/27/2024
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    .component, .sub-components-wrapper, .left-area, .right-area, .info > div:first-child, .right-area div, .content_area, .comment-area {
        display: flex;
    }

    .component {
        border: 1px solid black;
        padding: 20px;
        width: 50%;
        margin: auto;
        flex-direction: column;
    }

    .sub-components-wrapper, .content_area {
        justify-content: space-between;
    }

    .left-area, .right-area {
        justify-content: center;
    }

    .left-area {
        flex-direction: row;
    }

    .left-area img, .comment-area img {
        width: 100px;
        height: 100px;
        margin-right: 20px;
    }

    .left-area p, .right-area p {
        margin-right: 10px;
    }

    .info {
        flex-direction: column;
    }

    .right-area {
        flex-direction: column;
        align-items: flex-end;
    }

    .right-area button {
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
        img.src = img.src.endsWith("heart_off.png") ? "../assets/heart_on.png" : "../assets/heart_off.png";
    }
</script>

<div class="component">
    <div class="sub-components-wrapper">
        <div class="left-area">
            <img src="../assets/KhanhLeTutTut.png" alt="Avatar">
            <div class="info">
                <div>
                    <p>Khanh Le Tut Tut</p>
                    <p>Age: 65</p>
                </div>
                <div>
                    <p>The king of fingers</p>
                </div>
            </div>
        </div>
        <div class="right-area">
            <button style="align-self: flex-end;" onclick="toggleImage(this.children[0])">
                <img src="../assets/heart_off.png" alt="Heart">
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
        <p>Them bun long qua! Bye Bye!</p>
        <img src="../assets/post_image.png" alt="Post image">
    </div>
    <hr>
    <div class="comment-area">
        <img src="../assets/miley_cyrus.png" alt="Comment avatar">
        <div class="text">
            <p>Miley Cyrus</p>
            <p>Anh dep trai nha o dau vay?</p>
        </div>
        <button>Discuss Now ></button>
    </div>
</div>