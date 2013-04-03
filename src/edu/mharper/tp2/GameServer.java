package edu.mharper.tp2;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
		readCmd(buf);
		
		// BEGIN statement
		writeCmd(sockOutput,"BEGIN");
		
		// Game start
		waitForMoves();
		
		
		
		}catch(IOException e) {
			System.err.println("Acception failed."); 
			System.exit(1); 
		}
		
	}
	
	void waitForMoves() {
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				for ( ; ; ) {
					byte buf[] = new byte[1024];
					String command = readCmd(buf);
					parse(command);
				}
				
			}
		};
		Thread thread = new Thread(run);
		thread.start();
	}
	
	void parse(String command) {
		ArrayList<String> tokens = Util.getTokens(command);
		
		if (tokens.get(0).equals("A") || tokens.get(0).equals("W")) {
			// Single move only at the moment
			int x1, x2, y1, y2;
			x1 = Integer.parseInt(tokens.get(1));
			y1 = Integer.parseInt(tokens.get(2));
			x2 = Integer.parseInt(tokens.get(3));
			y2 = Integer.parseInt(tokens.get(4));
			
			View.gameView.gameManager.movePiece(View.gameView.gameManager.getBoard().getPiece(new Point(x1, y1)), new Point(x2, y2));
			View.gameView.endTurnAndUpdate();
		}
		
		if (tokens.get(0).equals("P")) {
			int x1, x2, y1, y2;
			x1 = Integer.parseInt(tokens.get(1));
			y1 = Integer.parseInt(tokens.get(2));
			x2 = Integer.parseInt(tokens.get(3));
			y2 = Integer.parseInt(tokens.get(4));
			View.gameView.gameManager.movePiece(View.gameView.gameManager.getBoard().getPiece(new Point(x1, y1)), new Point(x2, y2));
		}
		
		if (tokens.get(0).equals("S")) {
			int x1, y1;
			x1 = Integer.parseInt(tokens.get(1));
			y1 = Integer.parseInt(tokens.get(2));
			// Sacrifice the piece
			
		}
		
		
		
	}
	
	void writeCmd(OutputStream sockOutput,String msg){
		try{
		sockOutput.write(msg.getBytes(), 0, msg.length());
		}catch(IOException e){
			System.out.println("Write Data Stream Error.");
		}
	}
	
	String readCmd(byte[] buf){
		try{
		// buffer initialize
		for(int i=0;i<1024;i++)
			buf[i]=0;
		int bytes_read = sockInput.read(buf, 0, buf.length);
		String str = new String(buf, "UTF-8");
		return str;
		}catch(IOException e){
			System.out.println("Data Stream Error.");
		}
		return null;
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
		System.out.println("B ("+x+","+y+") ("+xTile+","+yTile+") ");
		String str=type + " " + x+" "+y+" "+xTile+" "+yTile+" ";
		writeCmd(sockOutput,str);
	}

}
