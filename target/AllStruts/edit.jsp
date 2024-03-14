<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Edit Post</title>
</head>
<body>
<h1>Edit Post</h1>
<s:form action="EditPostAction" method="post">
    <s:hidden id = "postId" name="postId" value="%{#parameters.postId[0]}" />
    <s:textarea id = "postContent" name="postContent" label="Post Content" value="%{postContent}" />
    <s:submit value="Update" />
</s:form>
</body>
</html>