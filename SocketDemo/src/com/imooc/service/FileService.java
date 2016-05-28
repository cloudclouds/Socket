package com.imooc.service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.imooc.entity.File;
import com.imooc.util.DBUtil;

public class FileService {
  Connection conn;
  public FileService()
  {
	  conn=DBUtil.getConnection();
  }
  
  public boolean uploadFile(File f)
  {
	  boolean success=false;
	  try 
	  {
		String sql="insert into tb_file(fname,fcontent) values(?,?)";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, f.getFname());
		pst.setBytes(2, f.getFcontent());
		int x=pst.executeUpdate();
		if(x>0) success=true;
	  } catch (SQLException e) {
		e.printStackTrace();
	  }
	  return success;
  }
}
