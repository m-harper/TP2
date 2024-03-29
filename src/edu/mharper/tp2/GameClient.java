package edu.mharper.tp2;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameClient {
	
	Socket clientSocket;
	int port;
	InputStream c_sockInput;
	OutputStream c_sockOutput;
	
	//default constructor
	public GameClient(int p){
		port = p;
	}
	
	void connect(){
		try { 
			clientSocket = null;
		
			//replace Fanorona Server to 127.0.0.1
			clientSocket = new Socket("127.0.0.1",port);
			
			//data stream
			c_sockInput = clientSocket.getInputStream();
			c_sockOutput = clientSocket.getOutputStream();
			
			byte[] buf=new byte[1024];
			//c_sockOutput.write(buf, 0, buf.length);
			
			// WELCOME acknowledge
			System.out.println(readCmd(buf));
			
			// INFO statement
			String str = readCmd(buf);
			StringTokenizer st = new StringTokenizer(str);
			st.nextToken();
			
			int row = Integer.parseInt(st.nextToken());
			int column = Integer.parseInt(st.nextToken());
			st.nextToken();
			str = st.nextToken();;
			int time = Integer.parseInt(str);
			
			Main.horizontalSpaces = row;
			Main.verticalSpaces = column;
			Main.defaultTime = time;
			Main.windowHeight = Main.verticalSpaces * Main.tileSize;  
	        Main.windowWidth = Main.horizontalSpaces * Main.tileSize;
	        Main.maxTurns = 10 * column;
	        
	        
			// READ statement
			writeCmd("READY");
			
			// BEGIN statement
			System.out.println(readCmd(buf));
			
			waitForMoves();
			
			// Game started
			/*str=readCmd(buf);
			st = new StringTokenizer(str);
			int x_prev = Integer.parseInt(st.nextToken());
			int y_prev = Integer.parseInt(st.nextToken());
			int x_next = Integer.parseInt(st.nextToken());
			int y_next = Integer.parseInt(st.nextToken());*/
			
			//remove(x_prev,y_prev,x_next,y_next);
			
			
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: 127.0.0.1."); 
			System.exit(1);
		} catch(IOException e){
			System.err.println("Couldn't get I/O for the connection to 127.0.0.1."); 
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
			
			
			boolean moveValid = false;
			moveValid = View.gameView.gameManager.isValidMove(View.gameView.gameManager.getBoard().getPiece(new Point(x1, y1)), new Point(x2, y2));
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
	
	void connectionEnd(){
		try{
		clientSocket.close();
		}catch(IOException e){
			System.out.println("Unexpected Exception occured.");
		}
	}
	
	void writeCmd(String msg){
		try{
		c_sockOutput.write(msg.getBytes(), 0, msg.length());
		System.out.println("* "+msg);
		}catch(IOException e){
			System.out.println("Write Data Stream Error.");
		}
	}
	
	String readCmd(byte[] buf){
		try{
		// buffer initialize
		for(int i=0;i<1024;i++)
			buf[i]=0;
		int bytes_read = c_sockInput.read(buf, 0, buf.length);
		String str = new String(buf, "UTF-8");
		return str;
		}catch(IOException e){
			System.out.println("Data Stream Error.");
		}
		return null;
	}
	
	void movepoint(String type, int x,int y,int xTile,int yTile){
		System.out.println(type + " ("+x+","+y+") ("+xTile+","+yTile+") ");
	}
	

}
