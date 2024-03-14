<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Posts</title>
</head>
<body>
<h1>Posts</h1>
<s:action name="DisplayPostsAction" executeResult="false" />
<s:iterator value="posts">
    <h2><s:property value="hash" /></h2>
    <p><s:property value="post" /></p>
    <a href="<s:url action='EditPostPageAction'><s:param name='postId' value='%{postId}' /></s:url>">Edit</a>
</s:iterator>
</body>
</html>