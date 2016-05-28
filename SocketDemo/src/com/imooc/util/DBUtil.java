package com.imooc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://115.28.29.244/imooc";
	private static String password="jjjddd";
	private static String user="root";
   public static Connection getConnection()
   {
	   Connection conn=null;
	   try 
	   {
		 Class.forName(driver);
		 conn=DriverManager.getConnection(url,user,password);
	    } catch (ClassNotFoundException e) {
		  e.printStackTrace();
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	   return conn;
   }
   
   public void closeAll(Connection conn)
   {
	   
   }
}
