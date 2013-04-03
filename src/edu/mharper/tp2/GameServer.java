package edu.mharper.tp2;

import java.io.*;
import java.net.*;

public class GameServer{
	
	ServerSocket serverSocket;
	Socket clientSocket = null;
	InputStream sockInput = null;
	OutputStream sockOutput = null;
	
	//Default Constructor
	public GameServer(int port){
		try {
			//connect to the given port
			serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				System.out.println("Could not listen on port:" + port); 
				System.exit(-1); 
		}
	}
	
	void connectClient(){
		try { 
		clientSocket = serverSocket.accept();
		
		sockInput = clientSocket.getInputStream();
		sockOutput = clientSocket.getOutputStream();
		
		// data stream buf
		byte[] buf=new byte[1024];
		
		// WELCOME acknowledge
		writeCmd(sockOutput,"WELCOME");
		
		// INFO statement
		String msg;
		msg="INFO "+Main.horizontalSpaces+" "+Main.verticalSpaces+" B "+Main.defaultTime+" ";
		writeCmd(sockOutput,msg);
		
		// READY statement
		readCmd(sockInput,buf);
		
		// BEGIN statement
		writeCmd(sockOutput,"BEGIN");
		
		// Game start
		
		
		
		}catch(IOException e) {
			System.err.println("Acception failed."); 
			System.exit(1); 
		}
		
	}
	
	void writeCmd(OutputStream sockOutput,String msg){
		try{
		sockOutput.write(msg.getBytes(), 0, msg.length());
		}catch(IOException e){
			System.out.println("Write Data Stream Error.");
		}
	}
	
	void readCmd(InputStream sockInput,byte[] buf){
		try{
		int bytes_read = sockInput.read(buf, 0, buf.length);
		String str = new String(buf, "UTF-8");
		System.out.println(str);
		
		// buffer initialize 
		for(int i=0;i<1024;i++)
			buf[i]=0; 
		
		}catch(IOException e){
			System.out.println("Data Stream Error.");
		}
	}
	
	
	void connectionEnd(){
		try{
		serverSocket.close();
		clientSocket.close();
		}catch(IOException e){
			System.out.println("Unexpected Exception occured.");
		}
	}
	
	void movepoint(String type, int x,int y,int xTile,int yTile){
		//System.out.println("B ("+x+","+y+") ("+xTile+","+yTile+") ");
		String str=type + " " + x +" "+y+" "+xTile+" "+yTile+" ";
		writeCmd(sockOutput,str);
	}

}
