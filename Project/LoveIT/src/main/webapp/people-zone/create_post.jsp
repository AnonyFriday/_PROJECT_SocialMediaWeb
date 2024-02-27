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

<div class="create_post">
    <input type="text" placeholder="Enter text here...">
    <div class="sub-component">
        <label>Upload an image:</label>
        <input type="text" placeholder="Image URL...">
        <button>Browse...</button>
        <button>Post</button>
    </div>
</div>