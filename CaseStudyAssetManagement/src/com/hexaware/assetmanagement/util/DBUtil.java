package com.hexaware.assetmanagement.util;
/*
 * @Author: Pritesh Rai 
 * Description: Created DBUtil class for getting static connection
 * Date: 12th Oct 24
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assetmanagement", "root", "Raji#1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
