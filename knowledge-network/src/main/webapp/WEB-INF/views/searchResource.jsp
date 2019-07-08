<%--
  Created by IntelliJ IDEA.
  User: wshish000
  Date: 17-12-9
  Time: 下午3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>搜索资源</title>
    <base href="<%=basePath%>">
</head>
<body>
<form action="/resource/searchResource" method="post">
    搜索：<input type="text" name="key"><br/>
    <input type="submit" value="提交">
</form>

<h5>搜索结果：</h5>
<c:forEach items="${resourceList}" var="resource">
    ------------------------------------------------------------------------------<br/>
    <%--<img alt="暂无图片" src="${quize.url}" /> <br/>--%>
    资源名字: ${resource.name}<br/>
    资源描述: ${resource.description}<br/>
    资源评分: ${resource.score}<br/>
    资源排名: ${resource.rank}<br/>
    上传的人: ${resource.uploader.userName}<br/>
    知识点们：<br/>
    <c:forEach items="${resource.knowledgePoints}" var="knowledgePoint">
        ${knowledgePoint.name} <br/>
    </c:forEach>
    相关课程：<br/>
    <c:forEach items="${resource.courses}" var="course">
        ${course.name} <br/>
    </c:forEach>
    下载链接: <br/>
    <c:url value="/resource/download" var="downurl">
        <c:param name="filename" value="${resource.url}"></c:param>
    </c:url>
    ${resource.name}<a href="${downurl}">下载</a>
    <br/>
</c:forEach>
</body>
</html>
