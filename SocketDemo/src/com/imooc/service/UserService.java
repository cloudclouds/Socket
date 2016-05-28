package com.imooc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.imooc.entity.User;
import com.imooc.util.DBUtil;

public class UserService {
	Connection conn;
	{
		conn=DBUtil.getConnection();
	}
   public boolean isUserExist(String userName,String password)
   {
	   boolean success=false;
	   String sql="select * from tb_user where username=? and password=?";
	   try 
	   {
		  PreparedStatement pst=conn.prepareStatement(sql);
		  pst.setString(1, userName);
		  pst.setString(2, password);
		  ResultSet rs=pst.executeQuery();
		  if(rs.next()) success=true;
	   } catch (SQLException e) {
		e.printStackTrace();
	   }
	   return success;
   }
   
   public boolean addUser(User u)
   {
	   boolean success=false;
	   String sql="insert into tb_user(username,password) values(?,?)";
	   PreparedStatement pst;
	   try 
	   {
			 pst = conn.prepareStatement(sql);
			 pst.setString(1, u.getUsername());
			 pst.setString(2, u.getPassword());
			 int x=pst.executeUpdate();
			 if(x>0) success=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return success;
   }
}
