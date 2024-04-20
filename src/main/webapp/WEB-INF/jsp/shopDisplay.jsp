<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 샵 디스플레이 뷰 -->
<title>shopDisplay</title>
</head>
<body style="background-color:#F0F8FF">

<!-- 제목 -->
<button>
<div style="text-align:left; font-size:3.3em; 
	font-weight:bold; color:#FF6633; cursor:pointer;"
	onclick="location.href='/food1'">푸드 1번지</div>
</button>
<button style="cursor:pointer;" onclick="history.back()">뒤로</button><br><br>

<!-- 선택된 자료 출력 -->
<%
session = request.getSession();
String myFoodImage = (String) session.getAttribute("myFoodImage");
String myFoodName = (String) session.getAttribute("myFoodName");
String myFoodPrice = (String) session.getAttribute("myFoodPrice");
%>
<table border="1" align="center" style="font-weight:bold;">
<tr align="center">
	<th colspan="3" style="color:blue; font-size:18pt">신 청</th>
</tr>
<tr align="center">
<td><br><img src="<%=myFoodImage%>"><br>
	<%=myFoodName%><br>￦<%=myFoodPrice%><br></td>
<td><a href="/shop/basketGo
	?myFoodImage=<%=myFoodImage%>&myFoodName=<%=myFoodName%>&myFoodPrice=<%=myFoodPrice%>">
	<img src="/pic/main/basket.png"></a><br>
	장바구니에 담기</td>
</tr>
</table>
</body>
</html>