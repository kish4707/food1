<%@page import="javax.swing.text.Document"%>
<%@page import="com.food1.controller.MainController"%>
<%@ page language="java" contentType="text/html;
	charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>main</title>

<!-- 배경이미지 -->
<style>
body { 
background-image:url('/pic/main/bgMain.png');
background-size:auto;
background-repeat:no-repeat;
}
</style>
</head>

<body>

<!-- 제목(푸드1번지) -->
<button><div style="text-align:left; font-size:3.3em; 
	font-weight:bold; color:#FF6633; cursor:pointer;"
	onclick="location.href='/food1'">푸드 1번지
	</div>
</button>
<br><br>

<!-- 상단 버튼들(장바구니, 로그인, 로그아웃, 가입, 탈퇴) -->
<div style="text-align:right;">
	<button style="cursor:pointer;" onclick="location.href='/food1/go?option=basketOpen'">장바구니</button>
	<button style="cursor:pointer;" onclick="location.href='/food1/go?option=login'">로그인</button>
	<button style="cursor:pointer;" onclick="location.href='/food1/go?option=logout'">로그아웃</button>
	<button style="cursor:pointer;" onclick="location.href='/food1/go?option=signup'">가입</button>
	<button style="cursor:pointer;" onclick="location.href='/food1/go?option=withdraw'">탈퇴</button>
</div>

<!-- 중간 버튼들 -->
<style>
	.shopIcon{text-align:left;margin-top:10px;}
	.suggestIcon{text-align:center;margin-top:-105px;}
	.recipeIcon{text-align:right;margin-top:-102px;}
</style>
<br>

<!-- 샵아이콘(쇼핑몰) -->
<div class="shopIcon">
	<button style="cursor:pointer;"><img src="/pic/main/btCake.png"
		onclick="location.href='/food1/go?option=shopButton'">
	</button>
</div>
<!-- 추천아이콘(오늘의 메뉴추천) -->
<div class="suggestIcon">
	<button style="cursor:pointer;"><img src="/pic/main/btEgg.png"
		onclick="location.href='/food1/go?option=suggestButton'">
	</button>
</div>

<!-- 레시피아이콘(오늘의 레시피) -->
<div class="recipeIcon">
	<button style="cursor:pointer;"><img src="/pic/main/btSand.png"
		onclick="location.href='/food1/go?option=recipeButton'">
	</button>
</div>

</body>
</html>