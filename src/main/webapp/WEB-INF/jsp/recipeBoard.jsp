<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RECIPE BOARD</title>
</head>
<body>

<!-- 제목 -->
<button>
<div style="text-align:left; font-size:3.3em; 
	font-weight:bold; color:#FF6633; cursor:pointer;"
	onclick="location.href='/food1'">푸드 1번지</div></button>
	<br><br><br>

<!-- 레시피보드 -->
<table bgcolor="CCFFCC" border="1" align="center">
<tr bgcolor="CCFF99">
	<th colspan="7">자유게시판</th>
</tr>
<tr bgcolor="CCFF99">
	<th>번호</th>
	<th>작성자</th>
	<th>제목</th>
	<th>내용</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>

<%
session = request.getSession();
ResultSet board = (ResultSet) request.getAttribute("board");
//String myId1 = (String) session.getAttribute("myId1");
try {
	while (board.next()) {
	String num = board.getString("num");
//if (myId.equals(myId1)) {
	String myId = board.getString("myId");
	String myPw = board.getString("myPw");
	String title = board.getString("title");
	String contents = board.getString("contents");
	String hitCnt = board.getString("hitCnt");
%>
<tr>
	<td><%=num%></td>
	<td><%=myId%></td> 
	<td><%=myPw%></td> 
	<td><%=title%></td> 
	<td><%=contents%></td> 
	<td><%=hitCnt%></td> 
<%
//}
}
} catch(Exception e) {
	e.printStackTrace();
}
%>

</table>

</body>
</html>