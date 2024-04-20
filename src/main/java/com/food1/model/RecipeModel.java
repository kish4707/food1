package com.food1.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food1.dao.BoardDAO;
import com.food1.dao.Db;
import com.food1.vo.BoardVO;
import com.food1.vo.RecipeVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/recipe")
public class RecipeModel {

// 레시피 메인
@GetMapping("/main")
public String main(Model model) {
	
	// 레시피명 추출
	RecipeVO recipeVO = new RecipeVO();
	String[] recipeName = recipeVO.recipeNames();
	
	// 레시피명 설정
	model.addAttribute("recipeName", recipeName);
	
	return "thymeleaf/recipe/recipeMain";
}	

// 레시피 검색 ------------------------------------------------------------------------
@GetMapping("/search")
public String search(HttpServletRequest request, Model model) {
	
	// 입력정보 수신(search)
	String search = request.getParameter("search");
	
	// 입력값(search)이 null이거나 공란일 때
	if (search==null || search.trim().isEmpty()) {
		return "redirect:main";
    }
	
	// 검색된 것을 저장할 임시배열
	String pickRecipeVideo = null;
	ArrayList<String> hugRecipeVideo = new ArrayList<>();
	ArrayList<String> hugRecipeName = new ArrayList<>();
	ArrayList<String> hugRecipeStuff = new ArrayList<>();
	ArrayList<String> hugRecipeCook = new ArrayList<>();
	int count = 0;
	
	// 기존 자료(레시피의 이름, 재료, 요리법)를 호출
	RecipeVO recipeVO = new RecipeVO();
	String[] pickRecipeName = recipeVO.recipeNames();
	String[] pickRecipeStuff = recipeVO.recipeStuffs();
	String[] pickRecipeCook = recipeVO.recipeCooks();
	
	// 기존 레시피(pickRecipeName)의 이름수 만큼 돌려보기
	int i = 0;
	for (i=0; i<pickRecipeName.length; i++) {
		
		// 기존의 레시피 이름들(pickRecipeName)이 검색한 이름(search)을 포함할 때
		if (pickRecipeName[i].contains(search)) {
			
			// 임시배열에 저장(레시피의 비디오, 이름, 재료, 요리법)
			pickRecipeVideo = "/pic/recipe/recipe"+i+".mp4";
			hugRecipeVideo.add(pickRecipeVideo);
			hugRecipeName.add(pickRecipeName[i]);
			hugRecipeStuff.add(pickRecipeStuff[i]);
			hugRecipeCook.add(pickRecipeCook[i]);
			count ++;
		}
		
	}
	
	// 검색(search)된 정보가 0개일 때
	if (count==0) {
		return "redirect:main";
	}
	
	// 검색(search)된 정보가 2개 이상일 때, 전달정보설정(레시피의 비디오, 이름, 재료, 요리법)
	model.addAttribute("hugRecipeVideo", hugRecipeVideo);
	model.addAttribute("hugRecipeName", hugRecipeName);
	model.addAttribute("hugRecipeStuff", hugRecipeStuff);
	model.addAttribute("hugRecipeCook", hugRecipeCook);
	
	// 검색(search)된 정보가 1개일 때
	if (hugRecipeName.size()==1) {
		
		// 전달정보설정(레시피의 비디오, 이름, 재료, 요리법)
		model.addAttribute("myRecipeVideo", hugRecipeVideo);
		model.addAttribute("myRecipeName", hugRecipeName);
		model.addAttribute("myRecipeStuff", hugRecipeStuff);
		model.addAttribute("myRecipeCook", hugRecipeCook);
		return "thymeleaf/recipe/recipeShow";
	}
	
	return "thymeleaf/recipe/recipeGlance";
}	

// 레시피 검색됨 ------------------------------------------------------------------------
@GetMapping("/searched")
public String searched(HttpServletRequest request, Model model) {
	
	// 입력정보수신(검색된 레시피의 비디오, 이름, 재료, 요리법)
	String myRecipeVideo = request.getParameter("myRecipeVideo");
	String myRecipeName = request.getParameter("myRecipeName");
	String myRecipeStuff = request.getParameter("myRecipeStuff");
	String myRecipeCook = request.getParameter("myRecipeCook");
	
	// 전달정보설정(레시피의 비디오, 이름, 재료, 요리법)
	model.addAttribute("myRecipeVideo", myRecipeVideo);
	model.addAttribute("myRecipeName", myRecipeName);
	model.addAttribute("myRecipeStuff", myRecipeStuff);
	model.addAttribute("myRecipeCook", myRecipeCook);
	
	return "thymeleaf/recipe/recipeShow";
}

// 레시피 직접선택 ------------------------------------------------------------------------
@GetMapping("/choose")
public String choose(HttpServletRequest request, Model model) {
	
	// 입력정보수신(myRecipeNum)
	String myRecipeNum = request.getParameter("myRecipeNum");
	
	// 기존자료(레시피의 이름, 재료, 요리법)호출
	RecipeVO recipeVO = new RecipeVO();
	String pickRecipeVideo = "/pic/recipe/recipe";
	String[] pickRecipeName = recipeVO.recipeNames();
	String[] pickRecipeStuff = recipeVO.recipeStuffs();
	String[] pickRecipeCook = recipeVO.recipeCooks();
	
	// 기존 레시피(pickRecipeName)의 이름수 만큼 돌려보기
	int i = 0;
	for (i=0; i<pickRecipeName.length; i++) {
		
		// 기존의 레시피 이름들(pickRecipeName[i])중 검색한 이름(pickRecipeName[intMyRecipeNum])이 일치할 때
		int intMyRecipeNum = Integer.parseInt(myRecipeNum);
		if (pickRecipeName[i]==pickRecipeName[intMyRecipeNum]) {
			
			// 임시문자열에 저장(나의 레시피의 비디오, 이름, 재료, 요리법)
			String myRecipeVideo = pickRecipeVideo+i+".mp4";
			String myRecipeName = pickRecipeName[i];
			String myRecipeStuff = pickRecipeStuff[i];
			String myRecipeCook = pickRecipeCook[i];
			
			// 전달정보설정(나의 레시피의 비디오, 이름, 재료, 요리법)
			model.addAttribute("myRecipeVideo", myRecipeVideo);
			model.addAttribute("myRecipeName", myRecipeName);
			model.addAttribute("myRecipeStuff", myRecipeStuff);
			model.addAttribute("myRecipeCook", myRecipeCook);
		}
		
	}
	
	return "thymeleaf/recipe/recipeShow";
	
}

// 레시피보드 목록 ------------------------------------------------------------------------
@GetMapping("/board")
public String board(Model model) {
	
    // 데이터 호출하여 읽기
    BoardDAO dao = new BoardDAO();
    ArrayList<BoardVO> board = dao.BoardAllData();
    
    // 보드정보설정(board)
    model.addAttribute("board", board);
    return "thymeleaf/recipe/recipeBoard";
}

// 레시피보드 상세항목 ------------------------------------------------------------------------
@GetMapping("/detail")
public String detail(HttpServletRequest request,
	Model model, HttpSession session) 
	throws ClassNotFoundException, SQLException {
	
	/*
	String myId = request.getParameter("myId");
	if (myId.equals(null)) {
		myId = "";
	}
	*/
	
	// 입력정보수신(myNum)
	String myNum = request.getParameter("myNum");
		int intMyNum = Integer.parseInt(myNum);
	//String pickId = request.getParameter("pickId");
	
	// JDBC 드라이버 로드
    Class.forName("oracle.jdbc.OracleDriver");
    
    // 데이터베이스에 연결
    Connection conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
    
    // 쿼리 준비
    String sql = "SELECT num, id, pw, title, contents, regDate, hit FROM board WHERE num = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, intMyNum);
    updateHit(intMyNum); // 조회수
    myNum = Integer.toString(intMyNum);
    
    // 쿼리 실행
    ResultSet rs = stmt.executeQuery();
    
    // 결과 가져오기
    if (rs.next()) {
        String pickId = rs.getString("id");
        String myPw = rs.getString("pw");
        String myTitle = rs.getString("title");
        String myContents = rs.getString("contents");
        Timestamp myRegDate = rs.getTimestamp("regDate");
        int myHit = rs.getInt("hit");
        
        // 결과를 모델에 저장(일련번호, 아이디, 비번, 제목, 내용, 등록일, 조회수)
        model.addAttribute("myNum", myNum);
        model.addAttribute("pickId", pickId);
        model.addAttribute("myPw", myPw);
        model.addAttribute("myTitle", myTitle);
        model.addAttribute("myContents", myContents);
        model.addAttribute("myRegDate", myRegDate);
        model.addAttribute("myHit", myHit);
        
        /*
        // 결과를 세션에 저장(일련번호, 아이디, 비번, 제목, 내용, 등록일, 조회수)
        session.setAttribute("myNum", myNum);
        session.setAttribute("pickedId", pickedId);
        session.setAttribute("myPw", myPw);
        session.setAttribute("myTitle", myTitle);
        session.setAttribute("myContents", myContents);
        session.setAttribute("myRegDate", myRegDate);
        session.setAttribute("myHit", myHit);
        */
    }
    
	return "thymeleaf/recipe/recipeDetail";
}

// 레시피보드 옵션진행(수정 및 삭제) -------------------------------------------------------------
@GetMapping("/choice")
public String choice(HttpServletRequest request,
	HttpServletResponse response, Model model,
	HttpSession session) throws IOException {
	
	// 입력정보수신(상태값, 일련번호, 추출아이디, 제목, 내용, 나의 아이디)
	String state = request.getParameter("option");
	String myNum = request.getParameter("myNum");
	String pickId = request.getParameter("pickId");
	String myTitle = request.getParameter("myTitle");
	String myContents = request.getParameter("myContents");
	String myId = (String) session.getAttribute("myId");
	
	// 전달정보저장(상태값, 일련번호, 추출아이디, 제목, 내용, 나의 아이디)
	session.setAttribute("state", state);
	session.setAttribute("myNum", myNum);
	session.setAttribute("pickId", pickId);
	session.setAttribute("myTitle", myTitle);
	session.setAttribute("myContents", myContents);
	session.setAttribute("myId", myId);
	
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	// 상태값(state)이 list일 때
	if (state.equals("list")) {
		return "redirect:board";
	}
	
	// 상태값값(state)이나 나의 아이디(myId)가 null일 때
	if (state==null || myId==null) {
        return "login";
    }
	
	// 추출한 아이디(pickId)가 나의 아이디(myId)와 다를 때
	if (pickId!=myId) {
	    out.println("<script>");
	    out.println("alert('아이디가 서로 다릅니다!');");
	    out.println("</script>");
	    return "login";
    }
	
	// 상태값(state)과 옵션값(option)에 따른 처리
	if (state.equals("update") || state.equals("delete")) {
		return "redirect:choicing";	
	}
	
	return "thymeleaf/recipe/recipeBoard";
}

// 수정 및 삭제 + 로그인 ------------------------------------------------------------------------
@GetMapping("/choicing")
public String choicing(HttpSession session) {
	
	// 입력정보수신(상태, 옵션명, 일련번호, 제목, 내용))
	String state = (String) session.getAttribute("state");
	String option = (String) session.getAttribute("option");
	String myNum = (String) session.getAttribute("myNum");
	String myTitle = (String) session.getAttribute("myTitle");
	String myContents = (String) session.getAttribute("myContents");
	
	// 전달정보를 세션에 저장(상태, 옵션명, 일련번호, 제목, 내용)
	session.setAttribute("state", state);
	session.setAttribute("option", option);
	session.setAttribute("myNum", myNum);
	session.setAttribute("myTitle", myTitle);
	session.setAttribute("myContents", myContents);
	
	// 상태값:수정(update) & 클릭값:수정(update) 
	if (state.equals("update") && option.equals("update")) {
		return "redirect:update";
		
	// 상태값:로그인(login) & 클릭값:수정(update) 
	} else if (state.equals("login") && option.equals("update")) {
		return "redirect:update";
		
	// 상태값:삭제(delete) & 클릭값:삭제(delete) 
	} else if (state.equals("delete") && option.equals("delete")) {
		return "redirect:delete";
		
	// 상태값:로그인(login) & 클릭값:삭제(delete) 
	} else if (state.equals("login") && option.equals("delete")) {
		return "redirect:delete";	
	}
	
	return"thymeleaf/recipe/recipeBoard";
}

// 레시피보드 수정 ------------------------------------------------------------------------
@GetMapping("/update")
public String update(HttpSession session, Model model) {

	// 입력정보수신(아이디, 제목, 내용)
	String myId = (String) session.getAttribute("myId");
	String myTitle = (String) session.getAttribute("myTitle");
	String myContents = (String) session.getAttribute("myContents");

	// 전달정보를 세션에 저장(아이디, 제목, 내용)
	model.addAttribute("myId", myId);
	model.addAttribute("myTitle", myTitle);
	model.addAttribute("myContents", myContents);
	return "thymeleaf/recipe/recipeUpdate";
}

// 레시피보드 수정됨 ------------------------------------------------------------------------
@GetMapping("/updated")
public String updated(HttpServletRequest request,
	HttpSession session, Model model) 
	throws ClassNotFoundException, SQLException {
	
	// 수정에서 목록으로 갈 때
	String option = request.getParameter("option");
	if (option.equals("list")) {
		return "redirect:board";
	}
	
	// 입력정보수신(일련번호, 제목, 내용)
	String myNum = (String) session.getAttribute("myNum");
		int intMyNum = Integer.parseInt(myNum);
	String myTitle = request.getParameter("myTitle");
	String myContents = request.getParameter("myContents");
	
	// 현재 날짜와 시간 추출
	LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
	Timestamp myRregDate = Timestamp.valueOf(localDateTime);
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
		
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// 레시피보드에 저장
	Db.sql3 = "UPDATE board SET title=?, contents=?, regDate=? WHERE num=?";
	Db.stmt3 = Db.conn.prepareStatement(Db.sql3);
	Db.stmt3.setString(1, myTitle);
	Db.stmt3.setString(2, myContents);
	Db.stmt3.setTimestamp(3, myRregDate);
	Db.stmt3.setInt(4, intMyNum);
	
	//쿼리 실행
	Db.stmt3.executeUpdate();
	
	return "redirect:board";
}

// 레시피보드 삭제시작 ------------------------------------------------------------------------
@GetMapping("/delete")
public String delete(HttpSession session, Model model) {
	
	// 입력정보수신(일련번호, 비번, 제목, 내용)
	String myNum = (String) session.getAttribute("myNum");
	String myId = (String) session.getAttribute("myId");
	String myTitle = (String) session.getAttribute("myTitle");
	String myContents = (String) session.getAttribute("myContents");
	
	// 전달정보를 모델에 저장(아이디, 제목, 내용)
	model.addAttribute("myNum", myNum);
	model.addAttribute("myId", myId);
	model.addAttribute("myTitle", myTitle);
	model.addAttribute("myContents", myContents);
	
	return "thymeleaf/recipe/recipeDelete";
}

// 레시피보드에서 삭제하기 ------------------------------------------------------------------------
@GetMapping("/deleted")
public String deleted(HttpServletRequest request, HttpSession session) 
	throws ClassNotFoundException, SQLException {
	
	String option = request.getParameter("option");
	if (option.equals("list")) {
		return "redirect:board";
	}
	
	// 입력정보수신(일련번호)
	String myNum = (String) session.getAttribute("myNum");
	int intMyNum = Integer.parseInt(myNum);
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
	
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// SQL 쿼리작성
	Db.sql = "DELETE FROM board WHERE num=?";
	Db.stmt = Db.conn.prepareStatement(Db.sql);
	
	// 데이터베이스에서 삭제항목 지정
	Db.stmt.setInt(1, intMyNum);
	
	// 쿼리실행 및 결과수신
	Db.rs = Db.stmt.executeQuery();
	
	// 데이터를 request에 저장
	request.setAttribute("board", Db.rs);
	
	return "redirect:board";
}	

// 레시피보드 힛업 ------------------------------------------------------------------------
@GetMapping("/hitup")
public String hitup(HttpServletRequest request) 
	throws ClassNotFoundException, SQLException {
	
	// 입력정보수신(일련번호)
	String myNum = request.getParameter("myNum");

	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
		
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// 지정된 num에 대해 hitCnt를 1씩 업데이트
	updateHit(Integer.parseInt(myNum));
	
	return "redirect:board";
}	

// 레시피보드 글쓰기 시작 ------------------------------------------------------------------------
@GetMapping("/write")
public String write(HttpServletResponse response, 
	HttpSession session, Model model) throws IOException {
	
	// 글쓰기에서 로그인일 때
	try {
		String myId = (String) session.getAttribute("myId");
		if (myId!=null) {
			return "redirect:writed";
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	String state = "write";
	session.setAttribute("state", state);
	return "login";
}	

// 레시피보드 로그인 글쓰기 하기 ------------------------------------------------------------------------
@GetMapping("/writed")
public String writed(HttpSession session, Model model) {

	// 입력정보수신(아이디, 비번)
	String myId = (String) session.getAttribute("myId");
	String myPw = (String) session.getAttribute("myPw");
	
	// 전달정보를 모델에 저장(아이디, 비번)
	model.addAttribute("myId", myId);
	model.addAttribute("myPw", myPw);	
	
	return "thymeleaf/recipe/recipeWrite";
}

// 레시피보드 로그인일 때 글쓰기 저장 ------------------------------------------------------------------------
@GetMapping("/insert")
public String insert(HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session) 
		throws ClassNotFoundException, SQLException, IOException {
	
	// 글쓰기에서 목록으로 갈 때
	String list = request.getParameter("list");
	if (list!=null) {
		return "redirect:board";
	}
	
	//int num1 = 1;
	
	// 전달정보수신(아이디, 비번, 제목, 내용)
	String myId = (String) session.getAttribute("myId");
	String myPw = (String) session.getAttribute("myPw");
	String myTitle = request.getParameter("myTitle");
	String myContents = request.getParameter("myContents");
	
	// 현재 날짜와 시간 추출
	LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
	Timestamp myRegDate = Timestamp.valueOf(localDateTime);
	
	// 가장 큰 num 값 가져오기
	int nextNum = getNextNumFromDatabase();
	
	// JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
		
	// 데이터베이스에 연결
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// 레시피보드에 저장
	Db.sql3 = "INSERT INTO board (num, id, pw, title, contents, regDate, hit) VALUES (?, ?, ?, ?, ?, ?, ?)";
	Db.stmt3 = Db.conn.prepareStatement(Db.sql3);
	
	// 레시피보드에 항목별(일련번호, 아이디, 비번, 제목, 내용, 등록일, 조회수) 저장
	Db.stmt3.setInt(1, nextNum);
	Db.stmt3.setString(2, myId);
	Db.stmt3.setString(3, myPw);
	Db.stmt3.setString(4, myTitle);
	Db.stmt3.setString(5, myContents);
	Db.stmt3.setTimestamp(6, myRegDate);
	Db.stmt3.setInt(7, 1);
	
	//쿼리 실행
	Db.stmt3.executeUpdate();
	
	return "redirect:board";
}

// DB의 board의 myNum에 번호 추가 ------------------------------------------------------------------------
private int getNextNumFromDatabase() throws SQLException {
	
	Db.sql = "SELECT MAX(num) AS maxNum FROM board";
	Db.stmt = Db.conn.prepareStatement(Db.sql);
	ResultSet rs = Db.stmt.executeQuery();
	
	int nextNum = 1;
	if (rs.next()) {
		
		// 가장 높은 수의 다음수를 저장
		int maxNum = rs.getInt("maxNum");
		nextNum = maxNum + 1;
	}
	rs.close();
	return nextNum;
}

// DB의 board의 hitCnt에 번호 추가 ------------------------------------------------------------------------
private void updateHit(int myNum) throws SQLException {
	
	String sqlUpdateHit = "UPDATE board SET hit = hit + 1 where num = ?";
	PreparedStatement stmtUpdateHit = Db.conn.prepareStatement(sqlUpdateHit);
	stmtUpdateHit.setInt(1, myNum);
	stmtUpdateHit.executeUpdate();
	stmtUpdateHit.close();
}

}