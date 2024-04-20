package com.food1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.food1.vo.BoardVO;

public class BoardDAO {
	
    private Connection conn;
    private PreparedStatement ps;
    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; {
        try {
        Class.forName("oracle.jdbc.OracleDriver");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void getConnection() {
        try {
            conn = DriverManager.getConnection(URL, "SCOTT","TIGER");
        } catch(Exception ex) {}
    }
    public void disConnection() {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch(Exception ex) {}
    }
    
    // 보드리스트
    public ArrayList<BoardVO> BoardAllData() {
        ArrayList<BoardVO> list = new ArrayList<BoardVO>();
        try {
            
        	// 오라클 연결
            getConnection();
            
            // SQL 문장 전송
            String sql = "SELECT DISTINCT num, id, pw, title, contents, regDate, hit"
                    + " FROM board ORDER BY num ASC";
            
            //executeQuery()
            ps = conn.prepareStatement(sql);
            
            // 결과값 받기
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setNum(rs.getInt(1));
                vo.setId(rs.getString(2));
                vo.setPw(rs.getString(3));
                vo.setTitle(rs.getString(4));
                vo.setContents(rs.getString(5));
                vo.setRegDate(rs.getTimestamp(6));
                vo.setHit(rs.getInt(7));
                
                // 자료를 모아서 => 브라우저로 전송
                list.add(vo);
            }
            rs.close();
            
            // ArrayList에 값 채우기
        } catch (Exception ex) {
            
        	// Error시에 에러 종류 확인
            System.out.println(ex.getMessage());
        } finally {
            
        	// 서버 종료
            disConnection();
        }
        return list;
    }
    
}