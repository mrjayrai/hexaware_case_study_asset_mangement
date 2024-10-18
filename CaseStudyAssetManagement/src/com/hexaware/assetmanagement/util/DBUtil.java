package com.hexaware.assetmanagement.util;
import java.io.FileReader;
import java.io.IOException;
/*
 * @Author: Pritesh Rai 
 * Description: Created DBUtil class for getting static connection
 * Date: 12th Oct 24
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;

		try {
		FileReader fileReader = new FileReader("resources/DBConfig.properties");
		Properties prop = new Properties();
		prop.load(fileReader);
		
		String driver = prop.getProperty("driver.classname");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		Class.forName(driver);

		
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;

	}
}
