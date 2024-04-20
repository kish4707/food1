package com.food1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food1.dao.Db;
import com.food1.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// 메인 모델
@Controller
@RequestMapping("/suggest")
public class SuggestModel {

// 샵메인 시작
@GetMapping("/main")
public String main() {
	return "thymeleaf/suggest/suggestMain";
}	

// 조회수 순으로 출력
@GetMapping("/board")
public String order(HttpServletRequest request,
	HttpSession session, Model model) 
	throws ClassNotFoundException, SQLException {

	String option = request.getParameter("option");
	
	// board를 담기 위한 boardList 준비
	List<BoardVO> boardList = new ArrayList<>();
	
	//JDBC 드라이버 로드
	Class.forName("oracle.jdbc.OracleDriver");
	
	// 데이터베이스에 연결
	Connection conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	
	// 쿼리 준비
	String sql = null;
	if (option.equals("orderHit")) {
		// 조회수별
		sql = "SELECT num, id, pw, title, contents, regDate, hit FROM board ORDER BY hit DESC";
	} else if (option.equals("orderAbc")) {
		// 제목순
		sql = "SELECT num, id, pw, title, contents, regDate, hit FROM board ORDER BY title ASC";
	} else if (option.equals("orderId")) {
		// 아이디순
		sql = "SELECT num, id, pw, title, contents, regDate, hit FROM board ORDER BY id ASC";
	} else {
		// 등록일순
		sql = "SELECT num, id, pw, title, contents, regDate, hit FROM board ORDER BY regDate ASC";
	}
	PreparedStatement stmt = conn.prepareStatement(sql);
	
	// 쿼리 실행
	ResultSet rs = stmt.executeQuery();
	
	// 결과 수신
	while (rs.next()) {
		BoardVO board = new BoardVO();
		board.setNum(rs.getInt("num"));
		board.setId(rs.getString("id"));
		board.setPw(rs.getString("pw"));
		board.setTitle(rs.getString("title"));
		board.setContents(rs.getString("contents"));
		board.setRegDate(rs.getTimestamp("regDate"));
		board.setHit(rs.getInt("hit"));
		
		// 가져온 데이터를 리스트에 추가
		boardList.add(board);
		model.addAttribute("boardList", boardList);
	}

	String optionName = null;
	switch (option) {
		case "orderHit" : optionName = "조회수별";
			break;
		case "orderAbc" : optionName = "제목순";
			break;
		case "orderId" : optionName = "아이디순";
			break;
		case "orderRegDate" : optionName = "등록일순";
		break;
	}
	model.addAttribute("optionName", optionName);
	
//return boardList;
return "thymeleaf/suggest/suggestBoard";

}

}
