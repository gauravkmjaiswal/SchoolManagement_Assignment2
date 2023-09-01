package com.osttra.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
public static Connection makeConnection() {
		
		Connection connectionObject = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connectionObject = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment2", "root", "Microsoft@123");
			
		} catch (Exception e) {
			
			System.out.println("Connection error with Database : " + e);
			e.printStackTrace();
			
		}
		
		return connectionObject;
		
	}
}
