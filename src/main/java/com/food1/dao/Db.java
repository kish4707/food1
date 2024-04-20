package com.food1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Db {
	public static String url="jdbc:oracle:thin:@localhost:1521:xe";
	public static String id="SCOTT";
	public static String pw="TIGER";
	public static Connection conn=null;
	public static PreparedStatement stmt=null;
	public static PreparedStatement stmt1=null;
	public static PreparedStatement stmt2=null;
	public static PreparedStatement stmt3=null;
	public static ResultSet rs=null;
	public static ResultSet rs1=null;
	public static ResultSet rs2=null;
	public static ResultSet rs3=null;
	public static String sql=null;
	public static String sql1=null;
	public static String sql2=null;
	public static String sql3=null;
	
	// sql:Login sql1:basket sql2:buy sql3:board
}