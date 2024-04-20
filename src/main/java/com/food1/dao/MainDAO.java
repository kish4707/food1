package com.food1.dao;

import java.sql.DriverManager;
import org.springframework.stereotype.Repository;

@Repository
public class MainDAO {
	
// DB에 접속
public MainDAO() {
	try {
		
	// 1단계. 드라이버 불러오기
	Class.forName("oracle.jdbc.OracleDriver");

	// mySql에 접속 할 수 있게 해주는 부분
	Db.conn = DriverManager.getConnection(Db.url, Db.id, Db.pw);
	} catch (Exception e) {
		e.printStackTrace();
	}
}	
	
// 로그인 인증
public int login(String id, String pw) {
	Db.sql = "SELECT pw FROM LOGIN WHERE id = ?";
	try {
		Db.stmt = Db.conn.prepareStatement(Db.sql);
		Db.stmt.setString(1, id);
		Db.rs = Db.stmt.executeQuery();
		if(Db.rs.next()) {
			if(Db.rs.getString(1).equals(pw)) {
				System.out.println("rs.getString(1)" + Db.rs.getString(1));
				return 1; // 로그인 성공
			} else {
				return 0; // 비밀번호 불일치
			}
		}
		return -1; // 아이디가 없음
	} catch (Exception e) {
		e.printStackTrace();
	}
	return -2; // 데이터베이스 오류
}

// 가입 인증
public int login(String id) {
	Db.sql = "SELECT id FROM LOGIN WHERE id = ?";
	try {
		Db.stmt = Db.conn.prepareStatement(Db.sql);
		Db.stmt.setString(1, id);
		Db.rs = Db.stmt.executeQuery();
		if(Db.rs.next()) {
			if(Db.rs.getString(1).equals(id)) {
				System.out.println("rs.getString(1)" + Db.rs.getString(1));
				return 2; // 아이디 중복
			} 
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 1; // 가입 성공
}	
	
}
