package com.imooc.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	Socket socket;
	OutputStream os;
	InputStream is;
    ObjectOutputStream oop;
   public SocketClient(String host,int port)
   {
	   try 
	   {
		  socket=new Socket(host,port);
		  os=socket.getOutputStream();
		  is=socket.getInputStream();
		  oop=new ObjectOutputStream(os);
	   } catch (UnknownHostException e) {
		e.printStackTrace();
	   } catch (IOException e) {
		e.printStackTrace();
	   }
   } 
   
   public boolean out(String type,Object o)
   {
	   System.out.println("out 方法");
	   try 
	   {
		  oop.writeObject(type);
		  oop.writeObject(o);
		  System.out.println("成功向服务端发送了消息");
		  InputStreamReader isr=new InputStreamReader(is);
		  BufferedReader br=new BufferedReader(isr);
		  String reply=br.readLine();
		  if(reply.equals("true")) return true;
	   } catch (IOException e) {
		e.printStackTrace();
	   }
	   return false;
   }
   public void close()
   {
	   try {
		socket.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
   }
}
