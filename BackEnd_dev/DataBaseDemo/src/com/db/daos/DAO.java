package com.db.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.db.users.*;

public class DAO {
	
	private static Connection getConn() {
		Connection conn = null;
		try {
		       Class.forName("com.mysql.jdbc.Driver"); //classLoader,加载对应驱动      
		   } catch (ClassNotFoundException e) {
		       e.printStackTrace();
		   }
		   String url = "jdbc:mysql://localhost:3306/t_student";
		   //String url = "jdbc:mysql://192.168.65.244:3306/zyj/t_student";
		   String username = "admin";//数据库账户，一般为root
		   String password = "123";//数据库密码
		   
		   try{
		       conn = DriverManager.getConnection(url, username, password);
		   } catch (SQLException e) {
		   e.printStackTrace();
		   }
		   return conn;
		}

		public static int insert(Student student) {
		   Connection conn = getConn();
		   int i = 0;
		   String sql = "insert into T_student (Name,Sex,Age) values(?,?,?)";
		   PreparedStatement pstmt;
		   try {
		       pstmt = (PreparedStatement) conn.prepareStatement(sql);
		       pstmt.setString(1, student.getName());
		       pstmt.setString(2, student.getSex());
		       pstmt.setString(3, student.getAge());
		       i = pstmt.executeUpdate();
		       pstmt.close();
		       conn.close();
		   } catch (SQLException e) {
		       e.printStackTrace();
		   }
		   return i;
		}

		public static int update(Student student) {
		   Connection conn = getConn();
		   int i = 0;
		   String sql = "update T_student set Age='" + student.getAge() + "' where Name='" + student.getName() + "'";
		   PreparedStatement pstmt;
		   try {
		       pstmt = (PreparedStatement) conn.prepareStatement(sql);
		       i = pstmt.executeUpdate();
		       System.out.println("resutl: " + i);
		       pstmt.close();
		       conn.close();
		   } catch (SQLException e) {
		       e.printStackTrace();
		   }
		   return i;
		}

		public static Integer getAll() {
		   Connection conn = getConn();
		   String sql = "select * from T_student";
		   PreparedStatement pstmt;
		   try {
		       pstmt = (PreparedStatement)conn.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       int col = rs.getMetaData().getColumnCount();
		       System.out.println("============================");
		       while (rs.next()) {
		           for (int i = 1; i <= col; i++) {
		               System.out.print(rs.getString(i) + "\t");
		               if ((i == 2) && (rs.getString(i).length() < 8)) {
		                   System.out.print("\t");
		               }
		            }
		           System.out.println("");
		       }
		           System.out.println("============================");
		   } catch (SQLException e) {
		       e.printStackTrace();
		   }
		   return null;
		}

		public static int delete(String name) {
		   Connection conn = getConn();
		   int i = 0;
		   String sql = "delete from T_student where Name='" + name + "'";
		   PreparedStatement pstmt;
		   try {
		       pstmt = (PreparedStatement) conn.prepareStatement(sql);
		       i = pstmt.executeUpdate();
		       System.out.println("resutl: " + i);
		       pstmt.close();
		       conn.close();
		   } catch (SQLException e) {
		       e.printStackTrace();
		   }
		   return i;
		} 
}
