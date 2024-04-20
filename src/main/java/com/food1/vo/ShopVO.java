package com.food1.vo;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShopVO {

// 국문식품명 자료
public String[] korFoodNames() {
	String[] korFoodName = new String[11];
	korFoodName[0] = "축산";
	korFoodName[1] = "수산";
	korFoodName[2] = "쌀";
	korFoodName[3] = "채소";
	korFoodName[4] = "과일";
	korFoodName[5] = "유제품";
	korFoodName[6] = "조미료";
	korFoodName[7] = "음료";
	korFoodName[8] = "라면";
	korFoodName[9] = "과자";
	korFoodName[10] = "가공식품";
	return korFoodName;
}
	
// 영문식품명 자료
public String[] engFoodNames() {
	String[] engFoodName = new String[11];
	engFoodName[0] = "meat";
	engFoodName[1] = "fish";
	engFoodName[2] = "rice";
	engFoodName[3] = "veg";
	engFoodName[4] = "fruit";
	engFoodName[5] = "milk";
	engFoodName[6] = "sauce";
	engFoodName[7] = "drink";
	engFoodName[8] = "ramen";
	engFoodName[9] = "snack";
	engFoodName[10] = "pf";
	return engFoodName;
}

// 축산류 데이터
public Map<String, String> mapMeats() {
Map<String, String> mapMeat = new LinkedHashMap<String, String>();
mapMeat.put("프라임 척아이롤 목심+등심 100g", "3300");
mapMeat.put("한우 우둔산적 1+등급 (100g)(팩)", "4980");
mapMeat.put("초이스 부채살 100g", "3500");
mapMeat.put("프리미엄 소고기 초이스등급 구이용 우삼겹 200g", "3700");
mapMeat.put("국내산 1+한우 수제 떡갈비 1팩 소분포장", "3900");
mapMeat.put("(한우유통1번가) 미경산한우 사골 1kg", "4000");
mapMeat.put("비프월드 대패 삼겹살 300g 구이 숯불구이", "4140");
mapMeat.put("우삼겹 미국산 고급육 250g 샤브/구이용/진공소포장", "4200");
mapMeat.put("프리미엄 브랜드 수입육 목살", "4500");
mapMeat.put("부채살 미국산 스테이크용 250kg 이지팩 1~2인용 소포장", "5900");
mapMeat.put("일품포크 돼지뒷다리 100g", "960");
mapMeat.put("선진포크 돼지뒷사태살 100g", "1320");
mapMeat.put("일품포크 돼지갈비 100g", "1550");
mapMeat.put("(맛나) 돈육돼지/갈비 100g/국산", "1710");
mapMeat.put("촌놈쉐프 구이용 목살 돼지고기 100g (수입/냉동)", "1760");
mapMeat.put("촌놈쉐프 구이용 삼겹살 돼지고기 100g (수입/냉동)", "1830");
mapMeat.put("백두한돈 돼지안심 100g", "2390");
mapMeat.put("일품포크 돼지목심 100g", "2520");
mapMeat.put("목전지 제육용 250g 이지팩 1~2인용 소포장", "2800");
mapMeat.put("미트플러스/목전지 찌개용 300g", "2800");
mapMeat.put("국내산 생닭가슴살 200g 냉동한입 큐브 소포장 혼밥혼술", "1900");
mapMeat.put("사조안심 닭가슴살 마일드 100g", "1900");
mapMeat.put("꼬꼬빌 심쿵 닭가슴살 어묵바 매콤한맛 70g x 1팩", "1980");
mapMeat.put("수제 닭가슴살 패티 100g", "2000");
mapMeat.put("푸드원 닭가슴살 스테이크 유자맛 100g", "2020");
mapMeat.put("햇살닭 현미 닭가슴살 스테이크 100g", "2300");
mapMeat.put("맛있닭 닭가슴살볼 치즈맛 100gx1팩", "2460");
mapMeat.put("갤러리아 아침몰 실온보관 바로먹는 촉촉한 아침란 2구 PET (1팩)", "1490");
mapMeat.put("다사다 훈제계란 6입", "2990");
mapMeat.put("텃골팜 1등급 신선란 특/10구", "3200");
return mapMeat;
}
	
// 수산류 데이터
public Map<String, String> mapFishs() {
Map<String, String> mapFish = new LinkedHashMap<String, String>();
mapFish.put("바다드림 쌈다시마 250g", "940");
mapFish.put("냉동 홍합살 200g", "1050");
mapFish.put("냉동 바지락살 200g", "1100"); 
mapFish.put("잔치국수육수 다시팩", "2010");
mapFish.put("고동 소라", "2200");
mapFish.put("냉동주꾸미/내장제거(통 200gr 4미/절단 500gr)", "2300");
mapFish.put("민물새우 110g(냉동수산물)", "2800");
mapFish.put("PLAZA청정원 명란바사삭김 3.8gx6개입", "2390");
mapFish.put("(국내산) 완도특산품 염장다시마 350g", "3200");
mapFish.put("냉동 국내산 자숙홍합 500g", "3500");
mapFish.put("MY-M 양반한식김자반50g(멸치야채)", "3550");
mapFish.put("지케이푸드 냉동자숙문어다리 100g 초밥슬라이스", "4340");
mapFish.put("탈피가오리채 가오리무침 300g", "4500");
mapFish.put("손질 논고동살 논우렁살 500g", "5300");
mapFish.put("냉동가오리날개 450g", "5700");
mapFish.put("순살 안동간고등어 120g(반마리)", "1500");
mapFish.put("국산 해동참조기", "1200");
mapFish.put("구운조기(중) 1팩 120g", "4640");
mapFish.put("제주은갈치", "4620");
mapFish.put("푸른자연바다 반건조 참돔 24~30cm/250~320g", "7000");
mapFish.put("한바다몰 명태포슬라이스 400g/명태전/동태전", "2390");
mapFish.put("구운통가자미 1팩(160~180g) (전자레인지 1분 간편식)", "4900");
mapFish.put("생연어 500g 냉동연어구이용 수제간식용", "6240");
mapFish.put("참치회 냉동참치 슬라이스 흑새치 속살 100g", "5100");
mapFish.put("국내산 삼치순살 손질삼치 구이용 500g", "3850");
mapFish.put("구운꽁치 1팩(1마리) 120g (전자레인지 1분 간편식)", "3800");
mapFish.put("그램원 홍어채 300g 홍어무침용", "5400");
mapFish.put("냉동국산 참굴비", "1590");
mapFish.put("손질가오리날개 무침용", "5600");
mapFish.put("냉동 아귀순살 500g", "6900");
return mapFish;
}

// 쌀 데이터
public Map<String, String> mapRices() {
Map<String, String> mapRice = new LinkedHashMap<String, String>();
mapRice.put("2023년산 이맛쌀 10kg", "22900");
mapRice.put("2023년 햅쌀 농부의아침 쌀10kg 백미 상등급", "24750");
mapRice.put("농협프리미엄 새청무 10KG 포", "24160"); 
mapRice.put("경기미 찰진쌀 10kg/상등급", "31200");
mapRice.put("국산 한입만 현미 10kg", "24600");
mapRice.put("특등급 연천농협 경기미 10kg", "29610");
mapRice.put("농사꾼 양심쌀 신동진쌀 10kg (특등급 2023년산 햅쌀)", "26140");
mapRice.put("(1+1) 가바성분이 풍부한 갈색가바현미 1.5KG", "10580");
mapRice.put("봉황뜰 햅쌀 일반미 백미 10kg(2023년산)", "28000");
return mapRice;
}

// 채소 데이터
public Map<String, String>mapVegs() {
Map<String, String> mapVeg = new LinkedHashMap<String, String>();
mapVeg.put("애호박(국산)", "2900");
mapVeg.put("제주 무(국산)", "1930");
mapVeg.put("새벽수확 양상추(국산)", "2990"); 
mapVeg.put("청양고추 150g", "3870");
mapVeg.put("제주 브로콜리(국산 송이)", "3,290");
mapVeg.put("다다기 오이 3입봉", "4360");
mapVeg.put("청상추", "2990");
mapVeg.put("깻잎", "2790");
mapVeg.put("한끼신선 깐마늘", "990");
mapVeg.put("영해시금치(국산)", "3990");
mapVeg.put("부추 300g 봉", "4990");
mapVeg.put("푸릇 친환경 양파 1kg", "4410");
mapVeg.put("제주 세척 당근 봉", "6490");
mapVeg.put("풀무원 유기농콩나물 270g", "1190");
mapVeg.put("하우스 감자 900g 봉", "5330");
return mapVeg;
}

// 과일류 데이터
public Map<String, String> mapFruits() {
Map<String, String> mapFruit = new LinkedHashMap<String, String>();
mapFruit.put("썬키스트 점보레몬", "1290");
mapFruit.put("항공직송 싱싱한 아보카도", "1590");
mapFruit.put("온도씨가락시장 키위 1개", "1680"); 
mapFruit.put("블랙라벨 고당도오렌지 특대", "1690");
mapFruit.put("온도씨가락시장 생라임 1개", "1860");
mapFruit.put("온도씨가락시장 오렌지 1개", "1950");
mapFruit.put("코코넛", "1950");
mapFruit.put("소용량 매일매일 바나나 4입", "2490");
mapFruit.put("냉동망고 과육 500g", "2900");
mapFruit.put("온도씨가락시장 자몽 1개", "2920");
mapFruit.put("사과 330g 이상 맛있는사과", "4500");
mapFruit.put("프리미엄 제철과일 꿀자두", "5000");
mapFruit.put("냉동적포도 1kg 스무디 요거트 디저트", "3700");
mapFruit.put("피쉬앤 냉동딸기 1kg", "5700");
mapFruit.put("참외 소과 1개(개당 280g 내외)", "7590");
return mapFruit;
}

// 유제품 데이터
public Map<String, String> mapMilks() {
Map<String, String> mapMilk = new LinkedHashMap<String, String>();
mapMilk.put("요플레 딸기 85gX4입 아이스박스 포장 요거트", "2210");
mapMilk.put("동일 치즈 쏘옥 달콤 우유 스틱 15T/커피/밀크/핫초코", "3700");
mapMilk.put("종근당건강 아이클리어 루테인 지아잔틴 500mgx30캡슐 1박스", "6330"); 
mapMilk.put("로이타이 코코넛밀크 500ml (동남아요리)", "2450");
mapMilk.put("유기농 오트(귀리) 드링크 1L", "6500");
mapMilk.put("제티 바나나 20스틱 340g", "3630");
mapMilk.put("빙그레 메로나맛 우유 240ml", "1780");
mapMilk.put("유얼거트 국산 원유 저당 플레인 그릭 요거트 130g", "3000");
mapMilk.put("티오 아이스티(레몬) 234g", "3870");
mapMilk.put("프리차드 버터 200g", "5500");
mapMilk.put("오뚜기 파운드 마아가린 450g", "1550");
mapMilk.put("서울우유 체다슬라이스치즈 100g 5매입", "2580");
return mapMilk;
}

// 조미료 데이터
public Map<String, String> mapSauces() {
Map<String, String> mapSauce = new LinkedHashMap<String, String>();
mapSauce.put("삼광 생와사비 4g", "200");
mapSauce.put("간편한 어묵탕 국물용 분말스프 6g", "300");
mapSauce.put("보리보리/사조대림꽃소금 500g", "810"); 
mapSauce.put("식자재왕 동치미맛 냉면육수 310g", "810");
mapSauce.put("샘표 합성향없는 발효 양조식초 900ml", "1170");
mapSauce.put("청정원 순창 100% 현미 태양초 찰고추장 500g", "5260");
mapSauce.put("푸른들마켓 해가원 후추가루 50g", "1300");
mapSauce.put("CJ 제일제당 백설 하얀설탕 400g", "1750");
mapSauce.put("몽고간장 진간장 500ml", "1800");
return mapSauce;
}

// 음료 데이터
public Map<String, String> mapDrinks() {
Map<String, String> mapDrink = new LinkedHashMap<String, String>();
mapDrink.put("롯데 펩시콜라 캔콜라 탄산음료 190ml", "720");
mapDrink.put("롯데 칠성사이다 캔음료 190ml", "720");
mapDrink.put("비락식혜 238ml", "720"); 
mapDrink.put("동서 옥수수차 500ml", "730");
mapDrink.put("굿 농심 카프리썬 오렌지 200ml", "730");
mapDrink.put("포도 봉봉 340ml", "730");
mapDrink.put("해태음료 갈아만든배 340ML", "900");
mapDrink.put("(현대hmall) 롯데칠성 실론티 240ml", "900");
mapDrink.put("(현대hmall) 조지아 캔커피 오리지널 240ml)", "900");
mapDrink.put("빙그레 쥬시쿨 복숭아 930ml 냉장배송", "930");
mapDrink.put("가야농장 정성담은 알로에 1L", "1000");
mapDrink.put("웅진 결명자차 1.35L", "1000");
return mapDrink;
}

//기타 데이터
public Map<String, String> mapRamens() {
Map<String, String> mapRamen = new LinkedHashMap<String, String>();
mapRamen.put("봉지라면 농심 너구리 얼큰한맛 120g", "980");
mapRamen.put("오뚜기 스낵면 90g 큰컵라면", "990");
mapRamen.put("봉지라면 농심 사리곰탕면 110g", "990"); 
mapRamen.put("오뚜기 라면 사리 110g 5개 멀티팩 전골 찌개", "990");
mapRamen.put("참깨라면 큰컵 110g", "1010");
mapRamen.put("팔도 일품 해물라면 왕컵 110g", "1030");
mapRamen.put("농심 멸치칼국수 98g", "1040");
mapRamen.put("비빔라면 팔도 비빔면컵 115g", "1050");
mapRamen.put("오뚜기 튀김우동 컵라면 110g", "1050");
mapRamen.put("봉지라면 농심 짜파게티 140g", "1070");
mapRamen.put("봉지라면 농심 오징어짬뽕 124g", "1070");
mapRamen.put("봉지라면 농심 무파마탕면 122g", "1130");
mapRamen.put("삼양 맛있는라면 115g", "1130");
mapRamen.put("쇠고기미역국라면 소컵 60g", "1250");
mapRamen.put("수타면 120g", "1360");
return mapRamen;
}

// 과자 데이터
public Map<String, String> mapSnacks() {
Map<String, String> mapSnack = new LinkedHashMap<String, String>();
mapSnack.put("미쯔 60g 과자 1개", "680");
mapSnack.put("스낵 오뚜기 뿌셔뿌셔 바베큐맛 90g/간식", "680");
mapSnack.put("군대 밀건빵 1봉", "690"); 
mapSnack.put("오리온 고래밥 볶음양념맛 46g 1개", "690");
mapSnack.put("참좋은식품 쌀강정 80g", "690");
mapSnack.put("굿 오리온 미니스낵 스윙칩 볶음고추장맛 30g", "820");
mapSnack.put("해태 크림웨하스 50g 1개입/아이간식/사무실간식", "820");
mapSnack.put("푸름 맛나쫀디기 130g 쫀드기 추억의간식 옛날과자", "820");
mapSnack.put("굿 오리온 미니스낵 오감자 감자그라탕맛 30g", "820");
mapSnack.put("굿 에이원식품 약과처럼 도넛처럼 60g", "820");
mapSnack.put("사무 생활 리빙 꼬미볼 포도맛 40g 문구 탕비", "820");
mapSnack.put("다이아몬드푸드 치즈향 크림 크래커 100g", "820");
mapSnack.put("오리온 쿠쉬쿠쉬 크루아상맛 (65.6g)1EA/발효크래커", "820");
mapSnack.put("초콜렛 트윅스 초콜릿 트윅스미니 미니 밀크초콜릿땅콩 10g 초코과자", "820");
mapSnack.put("허쉬 다크 앤 화이트 쿠키 50g/초코칩/초코과자/K", "820");
return mapSnack;
}

// 가공식품 데이터
public Map<String, String> mapPfs() {
Map<String, String> mapPf = new LinkedHashMap<String, String>();
mapPf.put("푸른들마켓사조 숯불구이맛김밥햄(100g)", "1500");
mapPf.put("갤러리아 푸른들마켓사조 한입롤피자(콤비네이션)(80g)", "1560");
mapPf.put("빵가루(오뚜기 200g)", "1560"); 
mapPf.put("안심 닭가슴살 리얼 90g (사조) 1개", "1650");
mapPf.put("보리보리/사조대림 딥치즈 버거 155g X 1개 /간식/햄버거/SB10", "1650");
mapPf.put("머거본 쇠고기육포 25g", "1680");
mapPf.put("예다원 삼계탕 100g", "1800");
mapPf.put("한성 맛있는 해물바 60g", "1800");
mapPf.put("고추참치 85g", "1150");
return mapPf;
}

}
