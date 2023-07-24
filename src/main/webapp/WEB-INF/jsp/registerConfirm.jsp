<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
<%
Account registerUser = (Account) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<p>下記のユーザーを登録します</p>
<p>
ユーザーID:<%=registerUser.getUserId() %><br>
パスワード:<%= registerUser.getPass() %><br>
メールアドレス:<%= registerUser.getMail() %><br>
姓名:<%= registerUser.getName() %><br>
年齢:<%= registerUser.getAge() %>
</p>
<a href="RegisterUser">戻る</a>
<a href="RegisterUser?action=done">登録</a>
</body>
</html>