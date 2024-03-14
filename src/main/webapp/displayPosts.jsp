<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Posts</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    h1 {
        color: #333;
        text-align: center;
        padding: 20px 0;
        background-color: #007bff;
        color: #fff;
        margin: 0;
    }
    .post-card {
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin: 20px auto;
        padding: 20px;
        width: 50%;
    }
    .post-title {
        color: #007bff;
        margin: 0 0 10px 0;
    }
    .post-content {
        margin: 10px 0;
    }
    a {
        color: #007bff;
        text-decoration: none;
    }
    a:hover {
        color: #0056b3;
    }
</style>
</head>
<body>
    <h1>Posts</h1>
    <s:action name="DisplayPostsAction" executeResult="false" />
    <s:iterator value="posts">
        <div class="post-card">
            <p class="post-title"><s:property value="title" /></p>
            <p class="post-content"><s:property value="post" /></p>
            <a href="<s:url action='EditPostPageAction'><s:param name='postId' value='%{postId}' /></s:url>">Edit</a>
        </div>
    </s:iterator>
</body>