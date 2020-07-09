
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>24点游戏</title>
    <style>
body{
    text-align:center;
}
form>input{
     width: 90px;
     height: 60px;
     display: inline-block;
     border: 1px solid #5882FA;
     text-align: center;
     font-size: 16px;
 }
input[type=submit]{
    width: 100px;
    height: 60px;
    background-color: #58ACFA;
    color: #fff;
}
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