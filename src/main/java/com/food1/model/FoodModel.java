package com.food1.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food1.dao.Db;
import com.food1.dao.MainDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 메인모델
@Controller
@RequestMapping("/food")
public class FoodModel {

// 로그인
@GetMapping("/login")
public String goLogin(HttpServletRequest request,
		HttpSession session) {
	
	// 상태값(state)과 제목값(title)
	String state = "logout";
	String title = "로그인";
	session.setAttribute("state", state);
	session.setAttribute("title", title);
	
	return "login";
}	

// 로그인중
@GetMapping("/logining")
public String logining() {
	return "login";
}	

// 로그인 인증 ------------------------------------------------------------------------
@GetMapping("/loginCert")
public String loginCert(HttpServletRequest request, 
	HttpServletResponse response,
	HttpSession session) throws IOException {
	
	// 입력정보수신(상태값(state), 추출아이디(pickId), 나의 아이디(myId), 비번(myPw))
	String state = (String) session.getAttribute("state");
	String pickId = (String) session.getAttribute("pickId");
	String myId = request.getParameter("myId");
	String myPw = request.getParameter("myPw");
	
	// 전달정보저장(상태값(state), 추출아이디(pickId), 나의 아이디(myId), 비번(myPw))
	session.setAttribute("state", state);
	session.setAttribute("pickId", pickId);
	session.setAttribute("myId", myId);
	session.setAttribute("myPw", myPw);
	
	if (state==null) {
		state = "nothing";
	}
	
	if (pickId==null) {
		pickId = "nothing";
	}
	
	// MainDAO(DB) 호출
	MainDAO mainDAO = new MainDAO();
	
	// MainDAO(DB)의 login 메소드에 아이디와 패스워드를 가져온 것
	int result = mainDAO.login(myId, myPw);
	
	// html 환경설정
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
	
	// 추출아이디(pickId)와 나의 아이디(myId)가 다를 때
	if (state.equals("update") && !pickId.equals(myId)) {
	    out.println("<script>");
	    out.println("alert('아이디가 다릅니다!');");
	    out.println("location.href='/food/logining';");
	    out.println("</script>");
	} else if (state.equals("delete") && !pickId.equals(myId)) {
	    out.println("<script>");
	    out.println("alert('아이디가 다릅니다!');");
	    out.println("location.href='/food/logining';");
	    out.println("</script>");
	}
	
	// 로그인됨 + 탈퇴
	if (result==1 && state.equals("withdraw")) { 
	    out.println("<script>");
	    out.println("location.href='/food/withdrawCheck';");
	    out.println("</script>");

	// 로그인됨 + 식품선택
	} else if (result==1 && state.equals("go")) { 
		out.println("<script>");
	    out.println("alert('인증되었습니다!');");
	    out.println("location.href='/shop/basketSave';");
	    out.println("</script>"); 

	 // 로그인됨 + 식품선택(display에서)
 	} else if (result==1 && state.equals("foodPick")) { 
 		out.println("<script>");
 	    out.println("alert('인증되었습니다!');");
 	    out.println("location.href='/shop/basketSave';");
 	    out.println("</script>");     
	    
	// 로그인됨 + 수정
	} else if (result==1 && state.equals("update")) { 
		out.println("<script>");
	    out.println("alert('인증되었습니다!');");
	    out.println("location.href='/recipe/update';");
	    out.println("</script>");
	    
	 // 로그인됨 + 삭제
	 } else if (result==1 && state.equals("delete")) { 
 		out.println("<script>");
 	    out.println("alert('인증되었습니다!');");
 	    out.println("location.href='/recipe/delete';");
 	    out.println("</script>");
	    
	// 로그인됨 + 글쓰기
	} else if (result==1 && state.equals("write")) { 
		out.println("<script>");
	    out.println("alert('인증되었습니다!');");
	    out.println("location.href='/recipe/writed';");
	    out.println("</script>"); 	    	    	    
	    
	// 로그인됨
	} else if (result==1) {
		out.println("<script>");
	    out.println("alert('인증되었습니다!');");
	    out.println("location.href='/food/pass';");
	    out.println("</script>"); 
	    
	// 비밀번호가 틀릴 때   
	} else if (result==0) {
		out.println("<script>");
		out.println("alert('비밀번호가 틀립니다!');");
		out.println("history.back()");
		out.println("</script>");
		
	// 아이디가 존재하지 않을 때	
	} else if (result==-1) {
		out.println("<script>");
		out.println("alert('존재하지 않는 아이디입니다!');");
		out.println("history.back()");
		out.println("</script>");
		
	// 오류가 발생했을 때
	} else if (result==-2) {
		out.println("<script>");
		out.println("alert('데이터베이스 오류가 발생했습니다!');");
		out.println("history.back()");
		out.println("</script>");
	} 
	
	return null;
	
}

// 패스불가(로그아웃) -----------------------------------------------------------------
@GetMapping("/logout")
public String goLogout(HttpServletRequest request,
		HttpServletResponse response, 
		HttpSession session) 
		throws IOException {
	
	// 로그아웃 변수설정(상태값(state):로그아웃, 아이디와 비번 초기화)
	String state = "logout";
	String myId = null;
	String myPw = null;
	
	// 로그아웃 정보설정(상태값, 아이디, 비번)
	session.setAttribute("state", state);
	session.setAttribute("myId", myId);
	session.setAttribute("myPw", myPw);
	
	// 로그아웃 표시
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("<script>");
	out.println("alert('로그아웃 되었습니다!');");
	out.println("location.href='/food1';");
	out.println("</script>");		
	
	return null;
}	

// 가입 ---------------------------------------------------------------------------
@GetMapping("/signup") 
public String goSignup() {
	return "signup";
}	

// 가입 인증 -----------------------------------------------------------------------
@GetMapping("/signupCert")
public String goSignupCert(HttpServletRequest request,
	HttpServletResponse response) 
	throws IOException, SQLException {
	
	String joinId = request.getParameter("joinId");
	String joinPw = request.getParameter("joinPw");
	String joinPhoneNum = request.getParameter("joinPhoneNum");
	String joinAddress = request.getParameter("joinAddress");
	
	// 가입 인증 처리
	MainDAO mainDAO = new MainDAO();
	int result = mainDAO.login(joinId);
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	// 누락항목 검사
	if (joinId=="" || joinPw=="" || joinPhoneNum=="" || joinAddress=="") { 
		out.println("<script>");  
		out.println("alert('모든 항목은 필수 입력입니다!');");
		out.println("location.href='/food/signup';");
		out.println("</script>");
	 }
	
	// 아이디 중복검사
	else if (result==2) { 
		out.println("<script>");  
		out.println("alert('아이디가 중복되었습니다!');");
		out.println("location.href='/food/signup';");
		out.println("</script>");
	} else { 
	try {
	    
		// 데이터베이스 연결
	    Class.forName("oracle.jdbc.OracleDriver");
	    Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	    
	    // SQL 쿼리 준비
	    Db.sql = "INSERT INTO login (id, pw, phoneNum, address) VALUES (?, ?, ?, ?)";
	    Db.stmt = Db.conn.prepareStatement(Db.sql);
	    Db.stmt.setString(1, joinId);
	    Db.stmt.setString(2, joinPw);
	    Db.stmt.setString(3, joinPhoneNum);
	    Db.stmt.setString(4, joinAddress);
	    
	    // 쿼리 실행
	    Db.stmt.executeUpdate();
	    
	    // 성공 메시지 출력
	    out.println("데이터가 성공적으로 저장되었습니다!");
	} catch (Exception e) {
		
		// 오류 처리
	   	out.println("오류발생 : " + e.getMessage());
	} finally {
		
		// 연결 및 리소스 해제
	    if (Db.stmt != null) {
	    	Db.stmt.close();
	    }
	    if (Db.conn != null) {
	    	Db.conn.close();
	    }
	}
	
	// 회원가입 출력표시
	out.println("<script>");  
	out.println("alert('가입되었습니다!');");
	out.println("location.href='/food1';");
	out.println("</script>");
	}
	
	return null;
}	

// 패스허가(로그인) ---------------------------------------------------------------
@GetMapping("/pass")
public String pass(HttpSession session) {
	
	// 입력정보수신(상태값(state), 아이디, 비번)
	String state = "login";
	String myId = (String) session.getAttribute("myId");
	String myPw = (String) session.getAttribute("myPw");
	
	// 전달정보저장(상태값(state), 아이디, 비번)
	session.setAttribute("state", state);
	session.setAttribute("myId", myId);
	session.setAttribute("myPw", myPw);
	return "main";
}		

// 탈퇴 시작 ---------------------------------------------------------------------
@GetMapping("/withdraw")
public String withdraw(HttpServletRequest request,
	HttpServletResponse response,	
	HttpSession session) {
	
	try {
		String state = (String) session.getAttribute("state");
		if (state.equals("login")) {
			return "redirect:withdrawCheck";
		}
	} catch (Exception e) {
		String state = "withdraw";
		String title = "탈퇴";
		session.setAttribute("state", state);
		session.setAttribute("title", title);
	}
	
	return "login";
}	

// 탈퇴 점검 ---------------------------------------------------------------------
@GetMapping("/withdrawCheck")
public String withdrawCheck(HttpServletRequest request,
	HttpServletResponse response) 
	throws IOException {
	
	// html 환경설정
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	out.println("<script>");
    out.println("var check = confirm('탈퇴하시겠습니까?');");
	out.println("if (check) {");
    out.println("location.href='/food/withdrawEnd';");
	out.println("} else {");
	out.println("alert('탈퇴가 취소되었습니다!');");
	out.println("location.href='/food1';");
    out.println("}");
	out.println("</script>");
	
	return null;
}	

// 탈퇴 완료 ---------------------------------------------------------------------
@GetMapping("/withdrawEnd")
public String withdrawEnd(HttpServletRequest request,
	HttpServletResponse response)
	throws ClassNotFoundException, SQLException, IOException {
	
	// 입력값 수신
	HttpSession session = request.getSession();
	String myId = (String)session.getAttribute("myId");
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
	
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// SQL 쿼리작성
	Db.sql = "DELETE FROM login WHERE id=?";
	Db.stmt = Db.conn.prepareStatement(Db.sql);
	
	// 데이터베이스에서 삭제항목 지정
	Db.stmt.setString(1, myId);
	
	// 쿼리실행 및 결과수신
	Db.rs = Db.stmt.executeQuery();
	
	// 데이터를 request에 저장
	request.setAttribute("login", Db.rs);
	
	// html 환경설정
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("<script>");
	out.println("alert('탈퇴가 처리되었습니다!');");
	out.println("location.href='/food1';");
	out.println("</script>");	
	
	return null;
}	

}