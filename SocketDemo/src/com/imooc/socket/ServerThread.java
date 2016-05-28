package com.imooc.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.imooc.entity.File;
import com.imooc.entity.User;
import com.imooc.service.FileService;
import com.imooc.service.UserService;

public class ServerThread extends Thread{
   Socket socket;
   UserService userService=new UserService();
   FileService fileService=new FileService();
   public ServerThread( Socket socket)
   {
	  this.socket=socket;
   }
   
   public void run() {
	   try 
	   {
		   System.out.println("进入服务端，启动的一个run方法");
		   InputStream is=socket.getInputStream();
		  ObjectInputStream ois=new ObjectInputStream(is);
//          boolean flag=true;
		  Object obj;
		  while((obj=ois.readObject())!=null)
		  {
//		   Object obj=ois.readObject();
//		   if(obj!=null)
//		   {
//			 String type=(String)obj;
//			 if(type.equals("关闭"))
//			 {
//			        flag = false;
//			        continue;
//			  }
		     Object o=ois.readObject();
		     String type=(String)obj;
		     service(type,o);
//		   }
		  }
	   } catch (IOException e) {
		e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   
   public void service(String type,Object o)
   {
	   System.out.println("service 方法，type="+type);
	   if(type.equals("login"))
	   {
		   loginService((User)o);
	   }
	   else if(type.equals("register"))
	   {
		   registerService((User)o);
	   }
	   else if(type.equals("upload"))
	   {
		   uploadService((File)o);
	   }
   }
   
   public void loginService(User u)
   {
	   System.out.println("loginService");
	   boolean res=userService.isUserExist(u.getUsername(), u.getPassword());
	   try
		{
		    OutputStream os=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(os);
		    pw.println(res);
		    pw.flush();
//		    socket.shutdownOutput();
		}
	    catch (IOException e) {
		  e.printStackTrace();
	   }
   }
   
   public void registerService(User u)
   {
	   boolean res=userService.addUser(u);
	   try {
		   OutputStream  os =socket.getOutputStream();
		   PrintWriter pw=new PrintWriter(os);
		   pw.println(res);
		   pw.flush();
	    } catch (IOException e) {
		e.printStackTrace();
	}
   }
   
   public void uploadService(File f)
   {
	   boolean res=fileService.uploadFile(f);
	   try {
		   OutputStream  os =socket.getOutputStream();
		   PrintWriter pw=new PrintWriter(os);
		   pw.println(res);
		   pw.flush();
	    } catch (IOException e) {
		e.printStackTrace();
	}
   }
}
