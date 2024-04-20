<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>

<!-- 배경 이미지 -->
<style>
body {
background-image:url('/pic/main/bgMain.png');
	background-size:auto;
	}
</style>
</head>
<body>

<%
session = request.getSession();
String title = (String) session.getAttribute("title");
%>

<!-- 제목 -->
<button><div style="text-align:left; font-size:3.3em; 
	font-weight:bold; color:#FF6633; cursor:pointer;"
	onclick="location.href='/food1'">푸드 1번지</div></button>

<!-- 소제목 -->
<div style="text-align:center;
 	font-size:1.5em; font-weight:bold; color:blue;">${title}</div>

<!-- 로그인 표 -->
<form action="/food/loginCert">
<table border="1" bgcolor="66CCCC"
style="margin-left:auto; margin-right:auto; font-weight:bold;">
<tr>
	<td align="center">아이디</td>
	<td><input type="text" name="myId"/></td>
</tr>
<tr>
	<td align="center">비밀번호</td>
	<td><input type="password" name="myPw"/></td>
</tr>
<tr>
	<td colspan="2" align="center">
	<input type="submit" value="로그인">
</tr>
</table>
</form>
</body>
</html>