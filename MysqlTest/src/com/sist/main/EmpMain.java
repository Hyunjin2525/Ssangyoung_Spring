package com.sist.main;
import java.util.*;


import java.sql.*;

public class EmpMain {
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
		String username="root";
		String password="happy";
		String driver="com.mysql.cj.jdbc.Driver"; 
		try
		{
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,username,password);
			String sql="SELECT empno,ename,job,hiredate,sal FROM emp";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
			}
					
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}