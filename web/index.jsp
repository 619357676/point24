<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>24点游戏</title>
</head>
<body>
<h2>point 24</h2>
<form action="index.jsp">
    <input type="text" name="A">
    <input type="text" name="B">
    <input type="text" name="C">
    <input type="text" name="D">
    <input type="submit">
    <input type="reset">
</form>

<%out.println("你的输入：");%><br/>
A:<%=request.getParameter("A")%><br/>
B:<%=request.getParameter("B")%><br/>
C:<%=request.getParameter("C")%><br/>
D:<%=request.getParameter("D")%><br/>
<%
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    try {
        a = Integer.parseInt(request.getParameter("A"));
        b = Integer.parseInt(request.getParameter("B"));
        c = Integer.parseInt(request.getParameter("C"));
        d = Integer.parseInt(request.getParameter("D"));
    } catch (Exception e) {
        //out.println("空");
    }

    String result = test.Suanf.solve(a, b, c, d);
    out.println("答案:");

    out.println(result);
%>
<%=session.getAttribute("result")%>
</body>
</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>24点游戏</title>
    <style>

    </style>
</head>
<body>
<h2>POINT24</h2>
<form  action="/solve" method="post">
    <input type="text" name="A" />
    <input type="text" name="B" />
    <input type="text" name="C" />
    <input type="text" name="D" />
    <input type="submit" value="GO" />
</form>
<%=session.getAttribute("result")%>
</body>
</html>