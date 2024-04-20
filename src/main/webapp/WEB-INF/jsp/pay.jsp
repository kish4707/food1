<%@page import="java.sql.ResultSet"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>pay</title>
	</head>
<body style="background-color:#F0F8FF">

<!-- 제목 -->
<br>
<button>
<div style="text-align:left; font-size:3.3em; 
	font-weight:bold; color:#FF6633; cursor:pointer;"
	onclick="location.href='/food1'">푸드 1번지</div>
</button>
<button style="cursor:pointer;" onclick="history.back()">뒤로</button><br><br><br>

<!-- 결제정보 -------------------------------------->
<table bgcolor="CCFFCC" border="1" align="center">
<tr bgcolor="CCFF99">
	<th colspan="4">결제정보</th>
</tr>
<tr bgcolor="CCFF99">
	<th>식품이미지</th>
	<th>식품명</th>
	<th>식품가격</th>
	<th>식품수량</th>
</tr>
<%
session=request.getSession();
ResultSet buy = (ResultSet) request.getAttribute("buy");
String myId = (String) session.getAttribute("myId");
String viewText = (String) session.getAttribute("viewText");

int foodNum = 0;
int foodPriceWithSubtotal = 0;
int shipPrice = 0;
int discountPrice = 0;
int totalPrice = 0;
String wonPickFoodPrice = null;
String wonFoodPriceWithSubtotal = null;

try {
	while(buy.next()) {
		String pickId = buy.getString("id");
	if (pickId.equals(myId)) {
		String pickPw = buy.getString("pw");
		String pickFoodImage = buy.getString("foodImage");
		String pickFoodName = buy.getString("foodName");
		String pickFoodPrice = buy.getString("foodPrice");
			int intPickFoodPrice = Integer.parseInt(pickFoodPrice); // 정수로 변환
			wonPickFoodPrice = String.format("%, d", intPickFoodPrice); // 원화표시
		String pickFoodQuantity = buy.getString("foodQuantity");
			int intFoodQuantity = Integer.parseInt(pickFoodQuantity); // 정수로 변환
		
		foodNum = foodNum + intFoodQuantity;
		int foodPriceWithQuantity = intPickFoodPrice * intFoodQuantity;
		foodPriceWithSubtotal = foodPriceWithSubtotal + foodPriceWithQuantity;
		wonFoodPriceWithSubtotal = String.format("%, d", foodPriceWithSubtotal); // 원화표시
		
%>
<tr>
	<td><img src="<%=pickFoodImage%>"></td>
	<td><%=pickFoodName%></td>
	<td align="right">￦<%=wonPickFoodPrice%></td>
	<td align="right"><%=pickFoodQuantity%></td>
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
wonFoodPriceWithSubtotal = String.format("%, d", foodPriceWithSubtotal);
String wonShipPrice = String.format("%, d", shipPrice);
String wonDiscountPrice = String.format("%, d", discountPrice);
String wonTotalPrice = String.format("%, d", totalPrice);


%>
<tr>
	<td colspan="4" bgcolor="CCFF99"></td>
</tr>
<tr>
	<th colspan="1">식품수</th>
	<td colspan="3" align="right"><%=foodNum%>개</td>
</tr>
<tr>
	<th colspan="1">식품금액</th>
	<td colspan="3" align="right">￦<%=wonFoodPriceWithSubtotal%></td>
</tr>
<tr>
	<th>배송비</th>
	<td colspan="3" align="right">￦<%=wonShipPrice%></td>
</tr>
<tr>
	<th>할인금액</th>
	<td colspan="3" align="right">-￦<%=wonDiscountPrice%></td>
</tr>
<tr>
	<th>총구매금액</th>
	<td colspan="3" align="right">￦<%=wonTotalPrice%></td>
</tr>
<tr>
	<td colspan="4" align="center"><%=viewText%></td>
</tr>
</table>

</body>
</html>