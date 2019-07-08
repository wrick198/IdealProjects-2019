<%--
  Created by IntelliJ IDEA.
  User: wshish000
  Date: 17-12-8
  Time: 上午10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>资源上传</title>
    <base href="<%=basePath%>">
</head>
<body>
    <form action="/resource/upload.do"enctype="multipart/form-data" method="post">
        上传用户：<input type="text" name="username"><br/>
        资源名字：<input type="text" name="name"><br/>
        资源描述：<input type="text" name="description"><br/>
        资源评分：<input type="text" name="score"><br/>
        资源排名：<input type="text" name="rank"><br/>
        资源类型：<input type="text" name="type"><br/>
        相关课程：<br/>
        <input type="checkbox" name="course" value="yuwen">语文<br/>
        <input type="checkbox" name="course" value="shuxue">数学<br/>
        <input type="checkbox" name="course" value="yingyu">英语<br/>
        <input type="checkbox" name="course" value="wuli">物理<br/>
        <input type="checkbox" name="course" value="huaxue">化学<br/>
        相关知识点：<br/>
        <input type="checkbox" name="knowledgePoint" value="zhishidian1">小知识点<br/>
        <input type="checkbox" name="knowledgePoint" value="zhishidian2">中知识点<br/>
        <input type="checkbox" name="knowledgePoint" value="zhishidian3">大知识点<br/>
        上传文件：<input type="file" name="file1"><br/>
        上传文件：<input type="file" name="file2"><br/>
        上传文件：<input type="file" name="file3"><br/>
        <input type="submit" value="提交">
    </form>

    <h5>上传结果：</h5>
    ${message}<br/>
    <c:forEach items="${fileList}" var="imagename">
        <img alt="暂无图片" src="upload/${imagename}" /> <br/>
    </c:forEach>
</body>
</html>
