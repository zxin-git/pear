package com.zxin.jdk.test.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketTest {

	public static void client(int port){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try
        {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost",port));
 
            if(socketChannel.finishConnect())
            {
                int i=0;
                while(true)
                {
                    String info = "I'm "+i+++"-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                    TimeUnit.SECONDS.sleep(10);
                }
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
	
	
	public static void server(){
	       ServerSocket serverSocket = null;
	       InputStream in = null;
	       try
	       {
	           serverSocket = new ServerSocket(8080);
	           int recvMsgSize = 0;
	           byte[] recvBuf = new byte[1024];
	           while(true){
	               Socket clntSocket = serverSocket.accept();
	               SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
	               System.out.println("Handling client at "+clientAddress);
	               in = clntSocket.getInputStream();
	               while((recvMsgSize=in.read(recvBuf))!=-1){
	                   byte[] temp = new byte[recvMsgSize];
	                   System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
	                   System.out.println(new String(temp));
	               }
	           }
	       }
	       catch (IOException e)
	       {
	           e.printStackTrace();
	       }
	       finally{
	           try{
	               if(serverSocket!=null){
	                   serverSocket.close();
	               }
	               if(in!=null){
	                   in.close();
	               }
	           }catch(IOException e){
	               e.printStackTrace();
	           }
	       }
	   }
	
	public static void main(String[] args) {
		server();
	}
	
}
