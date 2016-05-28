package com.imooc.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
   public static void main(String[] args) throws IOException
   {
		 ServerSocket serverSocket=new ServerSocket(8888);
		 System.out.println("*****服务器即将启动，等待客户端连接*****");
		 while(true)
		 {
		    Socket socket=serverSocket.accept();
		    System.out.println("接收到客户端连接请求");
		    new ServerThread(socket).start();
		 }
   }
}
