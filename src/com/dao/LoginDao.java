package com.dao;

import java.sql.*;

public class LoginDao {
	String sql = "select * from credentials where Username = ? and Password = ?";
	String url = "jdbc:mysql://localhost:3306/company";
	String sqluser = "root";
	String sqlpass = "root";
	
	public boolean check(String uname, String pass) {
		
		// Connecting The database through JDBC, and checking the credentials
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, sqluser, sqlpass);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,uname);
			st.setString(2,pass);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
}
