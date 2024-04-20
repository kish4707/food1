package com.food1.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food1.dao.Db;
import com.food1.dao.MainDAO;
import com.food1.vo.ShopVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.model.StorageType;
import net.nurigo.sdk.message.service.DefaultMessageService;

// 메인모델
@Controller
@RequestMapping("/shop")
public class ShopModel {

// 식품목록
@GetMapping("/main")
public String main (Model model) {
	
	// 식품목록 호출
	ShopVO shopVO = new ShopVO();
	String[] korFoodName = shopVO.korFoodNames();
	String[] engFoodName = shopVO.engFoodNames();
	model.addAttribute("korFoodName", korFoodName);
	model.addAttribute("engFoodName", engFoodName);
	return "thymeleaf/shop/shopMain";
}	

// 검색한 식품목록 처리 ------------------------------------------------------------------------
@GetMapping("/search")
public String search(HttpServletRequest request,
	HttpSession session, Model model) {
	
	// 검색어 수신
	String search = request.getParameter("search");
	if (search==null || search.trim().isEmpty()) {
		return "redirect:main";
    }
	
	// 검색된 것을 저장할 임시배열
	int count = 0;
	String pickFoodImage = null;
	String pickedFoodImage = null;
	ArrayList<String> hugFoodImage = new ArrayList<>();
	ArrayList<String> hugFoodName = new ArrayList<>();
	ArrayList<String> hugFoodPrice = new ArrayList<>();
	
	// 식품호출
	ShopVO shopVO = new ShopVO();
	String[] headFoodName = shopVO.engFoodNames();
	Map<String, String> pickFood = null;
	ArrayList<String> pickFoodName = null;
	ArrayList<String> pickFoodPrice = null;
	int hugCount = 0;
	
	for (int i=0; i<headFoodName.length; i++) {
		
	// 각 식품에 따른 맵호출
	switch (i) {
		case 0 :
			pickFoodImage = "/pic/shop/sub/meat/meat";
			pickFood = shopVO.mapMeats();
			break;
		case 1 : 
			pickFoodImage = "/pic/shop/sub/fish/fish";
			pickFood = shopVO.mapFishs(); 
			break;
		case 2 : 
			pickFoodImage = "/pic/shop/sub/rice/rice";
			pickFood = shopVO.mapRices(); 
			break;
		case 3 : 
			pickFoodImage = "/pic/shop/sub/veg/veg";
			pickFood = shopVO.mapVegs(); 
			break;
		case 4 : 
			pickFoodImage = "/pic/shop/sub/fruit/fruit";
			pickFood = shopVO.mapFruits(); 
			break;
		case 5 : 
			pickFoodImage = "/pic/shop/sub/milk/milk";
			pickFood = shopVO.mapMilks(); 
			break;
		case 6 : 
			pickFoodImage = "/pic/shop/sub/sauce/sauce";
			pickFood = shopVO.mapSauces(); 
			break;
		case 7 : 
			pickFoodImage = "/pic/shop/sub/drink/drink";
			pickFood = shopVO.mapDrinks(); 
			break;
		case 8 : 
			pickFoodImage = "/pic/shop/sub/ramen/ramen";
			pickFood = shopVO.mapRamens(); 
			break;
		case 9 : 
			pickFoodImage = "/pic/shop/sub/snack/snack";
			pickFood = shopVO.mapSnacks(); 
			break;
		case 10 : 
			pickFoodImage = "/pic/shop/sub/pf/pf";
			pickFood = shopVO.mapPfs(); 
			break;
	}
		
		// 각 식품의 검색어 포함여부 체크
		pickFoodName = new ArrayList<>(pickFood.keySet());
		pickFoodPrice = new ArrayList<>(pickFood.values());
		
		for (int j=0; j<pickFoodName.size(); j++) {

			if (pickFoodName.get(j).contains(search)) {
				
				// 배열에 저장
				count ++;
				pickedFoodImage = pickFoodImage+j+".png";
				hugFoodImage.add(pickedFoodImage);
				hugFoodName.add(pickFoodName.get(j));
				hugFoodPrice.add(pickFoodPrice.get(j));
				hugCount ++;
			}
			
		}
					
	}
	
	// 검색된 식품명이 0개일 때
	if (count==0) {
		return "redirect:main";
	}
	
	// 검색된 식품명이 2개 이상일 때
	if (hugFoodName.size()>1) {
		model.addAttribute("hugFoodImage", hugFoodImage);
		model.addAttribute("hugFoodName", hugFoodName);
		model.addAttribute("hugFoodPrice", hugFoodPrice);
		return "thymeleaf/shop/shopGlance";
	}
	
		// 검색된 식품명이 1개일 때(검색된 식품이미지, 식품명, 식품가격)
		String myFoodImage = hugFoodImage.get(0);
		String myFoodName = hugFoodName.get(0);
		String myFoodPrice = hugFoodPrice.get(0);
		
		// 전달정보설정(검색된 식품이미지, 식품명, 식품가격)
		session.setAttribute("myFoodImage", myFoodImage);
		session.setAttribute("myFoodName", myFoodName);
		session.setAttribute("myFoodPrice", myFoodPrice);

		return "shopDisplay";
	
}
	
// 검색된 식품목록들 중 1개 선택일 때 처리 ------------------------------------------------------------------------
@GetMapping("/searched")
public String searched(HttpServletRequest request,
	HttpSession session) {
	
	// 입력정보저장(식품이미지, 식품명, 식품가격)
	String myFoodImage = request.getParameter("myFoodImage");
	String myFoodName = request.getParameter("myFoodName");
	String myFoodPrice = request.getParameter("myFoodPrice");
	
	// 입력정보설정(식품이미지, 식품명, 식품가격)
	session.setAttribute("myFoodImage", myFoodImage);
	session.setAttribute("myFoodName", myFoodName);
	session.setAttribute("myFoodPrice", myFoodPrice);
	return "shopDisplay";
}

// 해당 식품목록 ------------------------------------------------------------------------
@GetMapping("/list")
public String list(HttpServletRequest request, Model model) {
	
	// 입력정보수신(국문식품이름, 영문식품이름)
	String korFoodName = request.getParameter("korFoodName");
	String engFoodName = request.getParameter("engFoodName");
	ShopVO shopVO = new ShopVO();
	
	// 식품별 처리(축산, 수산, 쌀, 채소, 과일, 유제품, 조미료, 음료, 라면, 과자, 가공식품)
	Map<String, String> mapFood = null;
	switch (engFoodName) {	
		case "meat" : 
			mapFood = shopVO.mapMeats(); 
			break;
		case "fish" : 
			mapFood = shopVO.mapFishs(); 
			break;
		case "rice" : 
			mapFood = shopVO.mapRices(); 
			break;
		case "veg" : 
			mapFood = shopVO.mapVegs(); 
			break;	
		case "fruit" : 
			mapFood = shopVO.mapFruits(); 
			break;
		case "milk" : 
			mapFood = shopVO.mapMilks(); 
			break;
		case "sauce" : 
			mapFood = shopVO.mapSauces(); 
			break;
		case "drink" : 
			mapFood = shopVO.mapDrinks(); 
			break;
		case "ramen" : 
			mapFood = shopVO.mapRamens(); 
			break;
		case "snack" : 
			mapFood = shopVO.mapSnacks(); 
			break;
		case "pf" : 
			mapFood = shopVO.mapPfs(); 
			break;
	}
	
	// 해당 식품내용(식품명(foodKey), 식품가격(foodValue))을 목록에 담기
	ArrayList<String> foodKey = new ArrayList<>(mapFood.keySet());
	ArrayList<String> foodValue = new ArrayList<>(mapFood.values());
	
	// 전달정보설정(국문식품이름, 영문식품이름)
	model.addAttribute("korFoodName", korFoodName);
	model.addAttribute("engFoodName", engFoodName);
	
	// 전달정보설정(해당 식품명(foodKey)과 식품가격(foodValue))
	request.setAttribute("foodKey", foodKey);
	request.setAttribute("foodValue", foodValue);
	return "thymeleaf/shop/shopList";
}

// 식품 한개 표시 ------------------------------------------------------------------------
@GetMapping("/display")
public String display(HttpServletRequest request,
	HttpSession session) {
	
	// 입력정보수신(식품이미지, 식품명, 식품가격)
	String myFoodImage = request.getParameter("foodImage");
	String myFoodName = request.getParameter("foodName");
	String myFoodPrice = request.getParameter("foodPrice");
	
	// 전달정보설정(식품이미지, 식품명, 식품가격)
	session.setAttribute("myFoodImage", myFoodImage);
	session.setAttribute("myFoodName", myFoodName);
	session.setAttribute("myFoodPrice", myFoodPrice);
	
	return "shopDisplay";
}

// 식품 한개 진행 ------------------------------------------------------------------------
@GetMapping("/basketGo")
public String basketGo(HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session)
		throws ServletException, IOException {
	
	// 입력정보수신
	String state = (String) session.getAttribute("state");
	String myId = (String) session.getAttribute("myId");
	
	// 나의 아이디(myId)가 null일 때
	if (myId==null) {
		state = "foodPick";
		session.setAttribute("state", state);
		return "login";
	}
	
	// 상태(state)가 로그아웃일 때
	if (state==null || state=="logout") {
		String title = "로그인";
		session.setAttribute("title", title);
		return "login";
	}
	
	// 전달정보설정(식품이미지, 식품명, 식품가격)
	String myFoodImage = request.getParameter("myFoodImage");
	String myFoodName = request.getParameter("myFoodName");
	String myFoodPrice = request.getParameter("myFoodPrice");
	
	// 전달정보설정(상태, 식품이미지, 식품명, 식품가격)
	session.setAttribute("state", state);
	session.setAttribute("myFoodImage", myFoodImage);
	session.setAttribute("myFoodName", myFoodName);
	session.setAttribute("myFoodPrice", myFoodPrice);
	
	return "redirect:basketSave";
}	

// 장바구니 인증 ------------------------------------------------------------------------
@GetMapping("/basketCert")
public String basketCert(HttpServletRequest request,
	HttpServletResponse response,
	HttpSession session) throws IOException {
	
	// 입력정보수신(아이디, 비번)
	String myId = request.getParameter("myId");
	String myPw = request.getParameter("myPw");
	
	// 로그인 처리
	MainDAO mainDAO = new MainDAO();
	
	// MainDAO의 login 메소드에 아이디와 패스워드 수신
	int result = mainDAO.login(myId, myPw);
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	// MainDAO에서 만든 로그인이 성공했을 때
	if (result==1) {
		out.println("<script>");
		out.println("alert('인증되었습니다!')");
		out.println("location.href='/shops/basketSave';");
		out.println("</script>");
	
	// MainDAO에서 만든 비밀번호가 틀릴 때
	} else if (result==0) {
		out.println("<script>");
		out.println("alert('비밀번호가 틀립니다!')");
		out.println("history.back()");
		out.println("</script>");
	
	// MainDAO에서 만든 아이디가 존재하지 않을 때
	} else if (result==-1) {
		out.println("<script>");
		out.println("alert('존재하지 않는 아이디입니다!')");
		out.println("history.back()");
		out.println("</script>");
	
	// MainDAO에서 오류가 발생했을 때	
	} else if (result==-2) {
		out.println("<script>");
		out.println("alert('데이터베이스 오류가 발생했습니다!')");
		out.println("history.back()");
		out.println("</script>");
	}
	
	// 전달정보설정(아이디, 비번)
	session.setAttribute("myId", myId);
	session.setAttribute("myPw", myPw);
	return null;
}

// 장바구니에 저장 ------------------------------------------------------------------------
@GetMapping("/basketSave")
public String basketSave(HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session)
		throws ClassNotFoundException, SQLException, IOException {
	
	// 입력정보수신(아이디, 비번, 식품이미지, 식품명, 식품가격)
	String myId = (String) session.getAttribute("myId");
	String myPw = (String) session.getAttribute("myPw");
	String myFoodImage = (String) session.getAttribute("myFoodImage");
	String myFoodName = (String) session.getAttribute("myFoodName");
	String myFoodPrice = (String) session.getAttribute("myFoodPrice");
	
	// 전달정보설정(상태, 식품명, 식품명, 식품가격)
	session.setAttribute("myId", myId);
	session.setAttribute("myPw", myPw);
	session.setAttribute("myFoodImage", myFoodImage);
	session.setAttribute("myFoodName", myFoodName);
	session.setAttribute("myFoodPrice", myFoodPrice);
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
	
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// 장바구니에 저장
	Db.sql = "INSERT INTO basket (id, pw, foodImage, foodName, foodPrice) VALUES (?, ?, ?, ?, ?)";
	Db.stmt = Db.conn.prepareStatement(Db.sql);
	
	Db.stmt.setString(1, myId);
	Db.stmt.setString(2, myPw);
	Db.stmt.setString(3, myFoodImage);
	Db.stmt.setString(4, myFoodName);
	Db.stmt.setString(5, myFoodPrice);
	
	//쿼리 실행
	Db.stmt.executeUpdate();
	
	return "redirect:basketOpen";
}

// 장바구니 열기 ------------------------------------------------------------------------
@GetMapping("/basketOpen")
public String basketOpen(HttpServletRequest request,
	HttpSession session) {
	
	try {	
		
		// JDBC 드라이버 로드
		Class.forName("oracle.jdbc.OracleDriver");
		
		// 데이터베이스에 연결
		Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
		
		// SQL 쿼리작성
		Db.sql1 = "SELECT * FROM basket";
		Db.sql2 = "SELECT * FROM buy";
		Db.stmt1 = Db.conn.prepareStatement(Db.sql1);
		Db.stmt2 = Db.conn.prepareStatement(Db.sql2);
		
		// 쿼리실행 및 결과수신
		Db.rs1 = Db.stmt1.executeQuery();
		Db.rs2 = Db.stmt2.executeQuery();
		
		// 데이터를 request에 저장
		request.setAttribute("basket", Db.rs1);
		request.setAttribute("buy", Db.rs2);
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	// 상태값(state)이 'pay'일 때
	String state = (String) session.getAttribute("state");
	
	try { 
		if (state.equals("pay")) {
			return "pay"; 
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "basketOpen";
}		

// 장바구니에서 선택 ------------------------------------------------------------------------
@GetMapping("/basketSelect")
public String basketSelect(HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session)
		throws ClassNotFoundException, SQLException, IOException {
	
	// 입력정보수신
	String myId = (String) session.getAttribute("myId");
	String myPw = (String) session.getAttribute("myPw");
	String pickFoodImage = request.getParameter("pickFoodImage");
	String pickFoodName = request.getParameter("pickFoodName");
	String pickFoodPrice = request.getParameter("pickFoodPrice");
	String pickFoodQuantity = "1";
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
	
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// 장바구니에 저장
	Db.sql = "INSERT INTO buy (id, pw, foodImage, foodName, foodPrice, foodQuantity) VALUES (?, ?, ?, ?, ?, ?)";
	Db.stmt = Db.conn.prepareStatement(Db.sql);
	Db.stmt.setString(1, myId);
	Db.stmt.setString(2, myPw);
	Db.stmt.setString(3, pickFoodImage);
	Db.stmt.setString(4, pickFoodName);
	Db.stmt.setString(5, pickFoodPrice);
	Db.stmt.setString(6, pickFoodQuantity);
	
	//쿼리 실행
	Db.stmt.executeUpdate();
	return "redirect:basketOpen";
}		

// 장바구니에서 삭제 ------------------------------------------------------------------------
@GetMapping("/basketDelete")
public String basketDelete(HttpServletRequest request,
	HttpServletResponse response)
	throws ClassNotFoundException, SQLException, IOException {
	
	// 입력값 수신
	String pickFoodImage = request.getParameter("pickFoodImage");
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
	
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// SQL 쿼리작성
	Db.sql1 = "DELETE FROM basket WHERE foodImage=?";
	Db.stmt1 = Db.conn.prepareStatement(Db.sql1);
	
	// 데이터베이스에서 삭제항목 지정
	Db.stmt1.setString(1, pickFoodImage);
	
	// 쿼리실행 및 결과수신
	Db.rs1 = Db.stmt1.executeQuery();
	
	// 데이터를 request에 저장
	request.setAttribute("basket", Db.rs1);
	return "redirect:basketOpen";
}	

// 구매에서 수량증감 ------------------------------------------------------------------------
@GetMapping("/updown")
public String updown(HttpServletRequest request,
	HttpSession session) 
	throws ClassNotFoundException, SQLException {
	
	// 입력정보수신
	String state = "updown";
	String updown = request.getParameter("updown");
	String pickedFoodImage = request.getParameter("pickedFoodImage");
	String pickedFoodName = request.getParameter("pickedFoodName");
	String pickedFoodPrice = request.getParameter("pickedFoodPrice");
	String pickedFoodQuantity = request.getParameter("pickedFoodQuantity");
	
	// 정보 연산
	int sign = 1;
	int intPickedFoodQuantity = Integer.parseInt(pickedFoodQuantity);
	int intedPickedFoodQuantity = 0;
	
	if (updown.equals("up")) {
		intedPickedFoodQuantity = intPickedFoodQuantity + sign;
		pickedFoodQuantity = Integer.toString(intedPickedFoodQuantity);
		
	} else if (updown.equals("down")) {
		intedPickedFoodQuantity = intPickedFoodQuantity - sign;
		pickedFoodQuantity = Integer.toString(intedPickedFoodQuantity);
	}
	
	if (updown.equals("down") && intedPickedFoodQuantity==0) {
		pickedFoodQuantity="1";
	}
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
		
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// DB의 buy에 저장
	Db.sql2 = "UPDATE buy SET foodQuantity=? WHERE foodImage=?";
	Db.stmt2 = Db.conn.prepareStatement(Db.sql2);
	Db.stmt2.setString(1, pickedFoodQuantity);
	Db.stmt2.setString(2, pickedFoodImage);
	
	//쿼리 실행
	Db.stmt2.executeUpdate();
	
	// 전달정보설정(상태, 식품이미지, 식품명, 식품가격, 식품수량)
	session.setAttribute("state", state);
	session.setAttribute("buyFoodImage", pickedFoodImage);
	session.setAttribute("buyFoodName", pickedFoodName);
	session.setAttribute("buyFoodPrice", pickedFoodPrice);
	session.setAttribute("buyFoodQuantity", pickedFoodQuantity);
	
	return "redirect:basketOpen";	
}	

// 구매에서 삭제 ------------------------------------------------------------------------
@GetMapping("/buyDelete")
public String buyDelete(HttpServletRequest request,
	HttpServletResponse response,
	HttpSession session)
	throws ClassNotFoundException, SQLException, IOException {
	
	// 입력정보수신(식품이미지)
	String pickedFoodImage = request.getParameter("pickedFoodImage");
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
	
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// SQL 쿼리작성
	Db.sql = "DELETE FROM buy WHERE foodImage=?";
	Db.stmt = Db.conn.prepareStatement(Db.sql);
	
	// 데이터베이스에서 삭제항목 지정
	Db.stmt.setString(1, pickedFoodImage);
	
	// 쿼리실행 및 결과수신
	Db.rs = Db.stmt.executeQuery();
	
	// 데이터를 request에 저장
	request.setAttribute("buy", Db.rs);
	
	return "redirect:basketOpen";
}	

// 구매하기 ------------------------------------------------------------------------
@GetMapping("/buy")
public String buy(HttpServletRequest request) {
	return "buy";
}

// 결제하기 ------------------------------------------------------------------------
@GetMapping("/pay")
public String pay(HttpServletRequest request,
	HttpSession session) {
	
	// DB 연결
	try {	
		
		// JDBC 드라이버 로드
		Class.forName("oracle.jdbc.OracleDriver");
		
		// 데이터베이스에 연결
		Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
		
		// SQL 쿼리작성
		Db.sql = "SELECT * FROM login";
		Db.sql2 = "SELECT * FROM buy";
		Db.stmt = Db.conn.prepareStatement(Db.sql);
		Db.stmt2 = Db.conn.prepareStatement(Db.sql2);
		
		// 쿼리실행 및 결과수신
		Db.rs = Db.stmt.executeQuery();
		Db.rs2 = Db.stmt2.executeQuery();
		
		// 데이터를 request에 저장
		request.setAttribute("login", Db.rs);
		request.setAttribute("buy", Db.rs2);
	
	} catch (Exception e) {
		e.printStackTrace();
	}

	// DB 표시
	ResultSet login = (ResultSet) request.getAttribute("login");
	ResultSet buy = (ResultSet) request.getAttribute("buy");
	String myId = (String) session.getAttribute("myId");
	String address = null;
	int foodNumber = 0;
	int foodPriceWithSubtitle = 0;
	int shipPrice = 0;
	int discountPrice = 0;
	int totalPrice = 0;
	ArrayList<String> phoneTexts = new ArrayList<>();

	// DB 중 buy 실행
	try {
		while(buy.next()) {
		String pickId = buy.getString("id");
		
		// 아이디가 일치할 때, 식품정보저장(비번, 식품이미지, 식품명, 식품가격)
		if (pickId.equals(myId)) {
			// String pickPw = buy.getString("pw");
			// String pickFoodImage = buy.getString("foodImage");
			String pickFoodName = buy.getString("foodName");
			String pickFoodPrice = buy.getString("foodPrice");
				int intPickFoodPrice = Integer.parseInt(pickFoodPrice); // 정수로 변환
			String pickFoodQuantity = buy.getString("foodQuantity");
				int intPickFoodQuantity = Integer.parseInt(pickFoodQuantity); // 정수로 변환
			
			// 아이디가 null이 아닐 때, 식품수량처리
			if (myId != null) {
				foodNumber = foodNumber + intPickFoodQuantity;
			}
			
			// 식품가격처리
			int intFoodPriceWithQuantity = intPickFoodPrice * intPickFoodQuantity;
			String wonFoodPriceWithQuantity = String.format("%, d", intFoodPriceWithQuantity); // 원화표시
			
			// 폰내용에 식품정보 추가하기
			phoneTexts.add(pickFoodName+": ￦"+wonFoodPriceWithQuantity+"원("+pickFoodQuantity+"개)\n");
			
			// 식품가격 소계
			foodPriceWithSubtitle = foodPriceWithSubtitle + intFoodPriceWithQuantity;
			
		}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	// 배송비와 할인금액 적용
	if (myId != null) {
		shipPrice = 3000;
		totalPrice = foodPriceWithSubtitle + shipPrice - discountPrice ;
	}
	
	// 원화자리표시
	String wonFoodPriceWithSubtitle = String.format("%, d", foodPriceWithSubtitle);
	String wonShipPrice = String.format("%, d", shipPrice);
	String wonDiscountPrice = String.format("%, d", discountPrice);
	String wonTotalPrice = String.format("%, d", totalPrice);
	
	// 폰내용에 추가하기
	phoneTexts.add("식품수: "+foodNumber+"개\n식품금액: ￦"+wonFoodPriceWithSubtitle
			+"원\n배송비: ￦"+wonShipPrice+"원\n할인금액: ￦"+wonDiscountPrice
			+"원\n총구매금액: ￦"+wonTotalPrice+"원\n");
	String phoneNum = null;

	// DB 중 login 실행
	try {
		while(login.next()) {
			String pickedId = login.getString("id");
		if (pickedId.equals(myId)){
			// String pickedPw = login.getString("pw");
			phoneNum = login.getString("phoneNum");
			address = login.getString("address");
		}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	// 결제처리
	String state = "pay";
	String pay = request.getParameter("pay");
	String viewText = null;
	
	// pay가 '체크/신용카드'일 때
	if (pay.equals("credit")) {
		phoneTexts.add("주거래 카드에서 결제합니다.\n"
		 		+ wonTotalPrice + "원 결제가 완료되었습니다.\n"
				+ myId + "님의 배송지, " + address + "(으)로 배송됩니다.");
		viewText="<br>주거래 카드에서 결제합니다.<br>￦"
				+ wonTotalPrice + "원 결제가 완료되었습니다.<br><br>"
				+ myId + "님의 배송지,<br> " + address + "(으)로 배송됩니다.<br><br>";
		
	// pay가 '무통장(가상계좌)'일 때	
	} else {
		phoneTexts.add("주문완료를 위해 ￦"
				+ wonTotalPrice + "원을 입금해 주세요.\n"
				+ "입금은행: 신한은행 110-497-040061");
		viewText="<br>주문완료를 위해 <br>￦"
				+ wonTotalPrice + "원을 입금해 주세요. <br><br>"
				+ "입금은행: 신한은행 110-497-040061<br><br>";
	}

	String phoneText = "";
	for (int i=0; i<phoneTexts.size(); i++) {
		phoneText = phoneText + "\n" + phoneTexts.get(i);
	}
	
	// 전달정보설정(상태, 폰번호, 주소, 폰내용, 뷰내용)
	session.setAttribute("state", state);
	session.setAttribute("phoneNum", phoneNum);
	session.setAttribute("address", address);
	session.setAttribute("phoneText", phoneText);
	session.setAttribute("viewText", viewText);

	return "redirect:mobile";
}			

// 모바일로 정보전달 ------------------------------------------------------------------------
@GetMapping("/mobile")
public String mobile(HttpServletRequest request) throws IOException {

	// 연결설정(API_Key, API_Secret, Host)
	DefaultMessageService messageService 
		= NurigoApp.INSTANCE.initialize("NCSR19ZQVJCMRDC6", "4NJMAT9ONNSA8KAHZXL1UEQ4JBMJEATR",	"https://api.coolsms.co.kr");
	HttpSession session = request.getSession();
	
	// 발신번호와 수신번호
	String fromPhoneNum="01072651303";
	String toPhoneNum = (String) session.getAttribute("phoneNum");
	
	// 문자설정
	String phoneText = (String) session.getAttribute("phoneText");
	
	// 이미지설정
	String image = "chrHuman"; 
	//ClassPathResource resource = new ClassPathResource("/static/"+image+".jpg");
	ClassPathResource resource = new ClassPathResource("/static/pic/main/"+image+".jpg");
	File file = resource.getFile();
	String imageId = messageService.uploadFile(file, StorageType.MMS, null);
	
	// 정보발송(발신처, 수신처, 문자, 이미지)
	Message message = new Message();
	message.setFrom(fromPhoneNum);
	message.setTo(toPhoneNum);
	message.setText(phoneText);
	message.setImageId(imageId);
	
	// 예외처리
	try {
		messageService.send(message);
	} catch (NurigoMessageNotReceivedException exception) {
		System.out.println(exception.getFailedMessageList());
		System.out.println(exception.getMessage());
	} catch (Exception exception) {
		System.out.println(exception.getMessage());
	}		
	
	return "redirect:basketOpen";
}

}