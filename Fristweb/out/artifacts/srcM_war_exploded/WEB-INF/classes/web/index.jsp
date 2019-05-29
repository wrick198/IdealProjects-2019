<%--
  Created by IntelliJ IDEA.
  User: steven
  Date: 18-7-23
  Time: 下午7:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<p>JSP循环字体变化13 <br/>
        <% for(int i=0;i<3;i++){
      out.println("<font size='"+(i+3)+"'>");
  %>
    第一个javaweb项目</font> <br/>
        <%
      }
  %>
    <br>
        <%!
      public int count;
      public String info(){
          return "hello";
      }
  %>
        <%
      out.println(count);
  %>
    <br>
        <%
      out.println(info());
  %>
        <%=++count%>
        <%=info()%>
    <br>
<table bgcolor="#123456" border="2" widt="400px">
    <tr>
        <td>标题</td>
        <td>值</td>
    </tr>
    <%
        for (int i = 0; i < 3; i++) {
    %>
    <tr>
        <td><%=i%>
        </td>
        <td><%=i + 3%>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%!
    public int a=4;
%>

<% application.setAttribute("counter",String.valueOf(++a)); %>

</body>
</html>
