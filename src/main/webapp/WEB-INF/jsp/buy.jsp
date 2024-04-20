<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>buy</title>
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

<h2>결제수단</h2>
<form action="/shop/pay">
<input type="radio" name="pay" value="credit" checked="checked"> 체크/신용카드<br><br>
<input type="radio" name="pay" value="virtual"> 무통장(가상계좌)<br><br>
<input type="submit" value="결제">
</form>

</body>
</html>