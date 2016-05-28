package com.imooc.socket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.imooc.entity.File;
import com.imooc.entity.User;
import com.imooc.service.FileService;

public class StartClient {
	SocketClient socketClient;
	private String menu="*****欢迎使用imooc文件上传器*****\n"
			+"1.登录\n"
			+"2.注册\n"
			+"3.退出\n"
			+"******************************************\n"
			+"请选择：";
	
	public StartClient()
	{
		socketClient=new SocketClient("www.budong.bid",8888);
	}
    public void showMainMenu()
    {
    	Scanner sc=new Scanner(System.in);
    	int choice;
    	while(true)
    	{
    	  System.out.println(menu);
    	  choice=sc.nextInt();
    	  switch(choice)
    	  {
    	     case 1:login();break;
    	     case 2:register();break;
    	     case 3:break;
    	  }
    	  if(choice==3) break;
    	}
    }
    public void login()
    {
    	String username,password;
    	Scanner sc=new Scanner(System.in);
        System.out.print("请输入用户名：");
  	    username=sc.next();
  	    System.out.print("请输入密码：");
        password=sc.next();
        //进行登录操作
        boolean success=false;
        User u=new User(username,password);
        success=socketClient.out("login",u);
        if(success)
        {
        	uploadFile();
        }
    }
    public void register()
    {
    	String username,password,repassword;
    	Scanner sc=new Scanner(System.in);
    	while(true)
    	{
    	  System.out.print("请输入用户名：");
    	  username=sc.next();
    	  System.out.print("请输入密码：");
          password=sc.next();
          System.out.print("请再次输入密码：");
    	  repassword=sc.next();
    	  if(!password.equals(repassword)) 
    		 System.out.println("两次输入的密码不一致！");
    	  else 
    	  {
    		  //进行注册操作
    		  boolean success=false;
    		  User u=new User(username,password);
    		  success=socketClient.out("register",u);
    		  if(success)  
    		  {
    			  System.out.println("注册成功，请登录！");
    			  login();break;
    		  }
    		  else System.out.println("数据库操作出现问题");
    	   }
    	}
    	
    }
    
    public void uploadFile()
    {
    	String path;
    	Scanner sc=new Scanner(System.in);
    	while(true)
    	{
    	 System.out.println("请输入上传文件的绝对路径（如e:/imooc/dog.jpg）:");
    	 path=sc.next();
     	 boolean success=true;
     	 //进行上传文件
     	java.io.File file=new java.io.File(path);
		long len=file.length();
		byte[] b=new byte[(int)len];
		FileInputStream is;
		try {
			is = new FileInputStream(file);
			is.read(b);
			File f=new File();
			f.setFname(file.getName());
			f.setFcontent(b);
			success=socketClient.out("upload", f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 if(success) 
    	 {
    		 System.out.println("文件上传成功，再见！");
    		 socketClient.close();
    	     System.exit(0);
    	 }
    	}
    }
    public static void main(String[] args)
    {
    	new StartClient().showMainMenu();
    }
}
