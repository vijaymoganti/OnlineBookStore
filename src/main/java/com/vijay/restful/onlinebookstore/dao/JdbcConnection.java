package com.vijay.restful.onlinebookstore.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	
	static String userName = "vijaymoganti";
	static String password = "25dec1987";
	static String url = "jdbc:mysql://localhost:3306/jdbc";
	static String driver = "com.mysql.jdbc.Driver";
	
	private  Connection conn;
	 
    public  Connection getConnection() throws IOException {     
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection( url, userName, password );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return conn;
    }
 
   

}
