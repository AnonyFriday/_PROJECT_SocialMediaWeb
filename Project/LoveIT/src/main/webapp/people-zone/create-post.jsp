<%--
  Created by IntelliJ IDEA.
  User: Nhat
  Date: 2/27/2024
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    .create_post {
        border: 1px solid black;
        width: 50%;
        margin: auto;
        padding: 20px;
    }
    .create_post > input {
        width: 100%;
        display: block;
        margin: auto;
    }
    .sub-component {
        display: flex;
        justify-content: flex-end;
    }
</style>

<form action="create-post" method="post">
    <div class="create_post">
        <label>
            <input type="text" name="content" placeholder="Enter text here...">
        </label>
        <div class="sub-component">
            <label>Upload an image:</label>
            <label>
                <input type="text" name="imageUrl" placeholder="Image URL...">
            </label>
            <button type="button">Browse...</button>
            <input type="submit" value="Post">
        </div>
    </div>
</form>