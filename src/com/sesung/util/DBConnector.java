package com.sesung.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {
	
	public static Connection connector() throws Exception {
		String user = "USER02";
		String password = "USER02";
		String url = "jdbc:oracle:thin:@211.238.142.23:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}
	
	public static void disconnector(Connection con, PreparedStatement st) throws Exception{
		st.close();
		con.close();
	}

	public static void disconnector(Connection con, PreparedStatement st, ResultSet rs) throws Exception{
		rs.close();
		st.close();
		con.close();
	}
}
