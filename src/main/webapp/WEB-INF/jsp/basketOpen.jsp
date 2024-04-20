<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page import="org.yaml.snakeyaml.events.Event.ID"%>
<%@page import="com.fasterxml.jackson.annotation.JsonTypeInfo.Id"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>basketOpen</title>
</head>

<body style="background-color:#F0F8FF">

<!-- 제목 -->
<button>
<div style="text-align:left; font-size:3.3em; 
	font-weight:bold; color:#FF6633; cursor:pointer;"
	onclick="location.href='/food1'">푸드 1번지</div></button>
	<br><br><br>

<!-- 장바구니 -------------------------------------->
<table bgcolor="CCFFCC" border="1" align="center">
<tr bgcolor="CCFF99">
	<th colspan="7" style="color:blue;">장바구니</th>
</tr>
<tr bgcolor="CCFF99">
	<th>ID</th>
	<th>PW</th>
	<th>식품이미지</th>
	<th>식품명</th>
	<th>식품가격</th>
	<th>선택</th>
	<th>삭제</th>
</tr>

<%
session = request.getSession();
ResultSet basket = (ResultSet) request.getAttribute("basket");
String myId = (String) session.getAttribute("myId");
String blueButton = "/pic/main/blueButton.png";
String redButton = "/pic/main/redButton.png";

// 장바구니 자료추출
try {
	while(basket.next()) {
	String pickId = basket.getString("id");
if (pickId.equals(myId)) {
	String pickPw = basket.getString("pw");
	String pickFoodImage = basket.getString("foodImage");
	String pickFoodName = basket.getString("foodName");
	String pickFoodPrice = basket.getString("foodPrice");
		int intPickFoodPrice = Integer.parseInt(pickFoodPrice);
		String wonPickFoodPrice = String.format("%, d", intPickFoodPrice);
%>
<tr>
	<!-- 장바구니 ID, PW, 식품이미지, 식품명, 식품가격 -->
	<td><%=myId%></td>
	<td>****</td>
	<td><img src="<%=pickFoodImage%>"></td>
	<td><%=pickFoodName%></td>
	<td align="right">￦<%=wonPickFoodPrice%></td>
	
	<!-- 장바구니에서 선택 -->
	<td><a href="/shop/basketSelect
		?pickFoodImage=<%=pickFoodImage%>
		&pickFoodName=<%=pickFoodName%>
		&pickFoodPrice=<%=pickFoodPrice%>">
		<img src="<%=blueButton%>"></a></td>
	
	<!-- 장바구니에서 삭제 -->
	<td><a href="/shop/basketDelete
		?pickFoodImage=<%=pickFoodImage%>">
		<img src="<%=redButton%>"></a></td>
</tr>
<%
}
}
} catch(Exception e) {
	e.printStackTrace();
}
%>

</table>
<br><br>

<!-- 구매정보 -------------------------------------->
<table bgcolor="CCFFCC" border="1" align="center">
<tr bgcolor="CCFF99">
	<th colspan="7" style="color:blue;">구매정보</th>
</tr>
<tr bgcolor="CCFF99">
	<th>식품이미지</th>
	<th>식품명</th>
	<th>식품가격</th>
	<th>식품수량</th><th>⊕</th><th>⊖</th>
	<th>삭제</th>
</tr>

<%
session = request.getSession();
ResultSet buy = (ResultSet) request.getAttribute("buy");

// 구매정보변수 초기화(식품개수, 식품가격, 배송비, 할인비용, 총비용)
int foodNumber = 0;
int foodPriceWithSubtotal = 0;
int shipPrice = 0;
int discountPrice = 0;
int totalPrice = 0;

// 구매정보 자료추출
try {
	
	while(buy.next()) {
		String pickedId = buy.getString("id");
	
		if (pickedId.equals(myId)) {
		String pickedPw = buy.getString("pw");
		String pickedFoodImage = buy.getString("foodImage");
		String pickedFoodName = buy.getString("foodName");
		String pickedFoodPrice = buy.getString("foodPrice");
			int intPickedFoodPrice = Integer.parseInt(pickedFoodPrice);
			String wonPickedFoodPrice = String.format("%, d", intPickedFoodPrice);
		String pickedFoodQuantity = buy.getString("foodQuantity");
			int intPickedFoodQuantity = Integer.parseInt(pickedFoodQuantity);
			int pickedFoodPriceWithQuantity = intPickedFoodPrice * intPickedFoodQuantity;
			foodPriceWithSubtotal = foodPriceWithSubtotal + pickedFoodPriceWithQuantity;
		
		if (myId != null) {
			foodNumber = foodNumber + intPickedFoodQuantity; 
		}
		
%>
<tr>
	<!-- 구매정보의 식품이미지, 식품명, 식품가격, 식품수량 -->
	<td><img src="<%=pickedFoodImage%>"></td>
	<td><%=pickedFoodName%></td>
	<td align="right">￦<%=pickedFoodPrice%></td>
	<td align="right"><%=pickedFoodQuantity%></td>
	
	<!-- 구매 수량증가 -->
	<td><a href="/shop/updown
		?updown=up
		&pickedFoodImage=<%=pickedFoodImage%>
		&pickedFoodName=<%=pickedFoodName%>
		&pickedFoodPrice=<%=pickedFoodPrice%>
		&pickedFoodQuantity=<%=pickedFoodQuantity%>">⊕</a></td>
	
	<!-- 구매 수량감소 -->
	<td><a href="/shop/updown
		?updown=down
		&pickedFoodImage=<%=pickedFoodImage%>
		&pickedFoodName=<%=pickedFoodName%>
		&pickedFoodPrice=<%=pickedFoodPrice%>
		&pickedFoodQuantity=<%=pickedFoodQuantity%>">⊖</a></td>
	
	<!-- 삭제 -->
	<td><a href="/shop/buyDelete?
		pickedFoodImage=<%=pickedFoodImage%>">
	<img src="<%=redButton%>"></a></td>
</tr>
<%
}
}
} catch(Exception e) {
	e.printStackTrace();
}

if (myId != null) {
	shipPrice = 3000;
	totalPrice = foodPriceWithSubtotal + shipPrice - discountPrice ;
	}

// 원화자리표시
String wonFoodPriceWithSubtotal = String.format("%, d", foodPriceWithSubtotal);
String wonShipPrice = String.format("%, d", shipPrice);
String wonDiscountPrice = String.format("%, d", discountPrice);
String wonTotalPrice = String.format("%, d", totalPrice);
%>

<tr>
	<td colspan="7" bgcolor="CCFF99"></td>
</tr>
<tr>
	<th colspan="1">식품수</th>
	<td colspan="6" align="right"><%=foodNumber%>개</td>
</tr>
<tr>
	<th colspan="1">식품금액</th>
	<td colspan="6" align="right">￦<%=wonFoodPriceWithSubtotal%></td>
</tr>
<tr>
	<th>배송비</th>
	<td colspan="6" align="right">￦<%=wonShipPrice%></td>
</tr>
<tr>
	<th>할인금액</th>
	<td colspan="6" align="right">-￦<%=wonDiscountPrice%></td>
</tr>
<tr>
	<th>총구매금액</th>
	<td colspan="6" align="right">￦<%=wonTotalPrice%></td>
</tr>
<tr>
	<th colspan="7" bgcolor="CCFF99">
		<a href="/shop/buy">구매하기</a></th>
</tr>
</table>

</body>
</html>
