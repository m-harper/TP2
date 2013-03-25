package edu.mharper.tp2;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameView extends Canvas implements MouseListener {
	
	public Point pieceSelection;
	public Point tileSelection;
	private GameManager gameManager;
	
	public GameView() {
		setPreferredSize(new Dimension(Main.windowWidth, Main.windowHeight));
		
		pieceSelection = null;
		gameManager = new GameManager();
		
		addMouseListener(this);
		gameManager.startGame();
	}
	
	public void newGame() {
		gameManager.startGame();
	}
	
	public void saveGame() {
		gameManager.saveGame();
	}
	
	public void loadGame(String fileName) {
		gameManager.loadGame(fileName);
	}
	
	public void paint(Graphics g) {	
		drawBackground(g);
		//drawSpaces(g);
		drawLines(g);
		drawTileSelection(g);
		drawPieces(g);
		drawSelection(g);
		drawValidMoves(g);
	}
	
	private void drawValidMoves(Graphics g) {
		if (pieceSelection != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.green);
			g2.setStroke(new BasicStroke(5));
			
			int x = pieceSelection.getX() * Main.tileSize + Main.tileSize / 2;
			int y = pieceSelection.getY() * Main.tileSize + Main.tileSize / 2;
			ArrayList<Point> validMoves = gameManager.getBoard().getValidMoves(pieceSelection);
			for (int i = 0; i < validMoves.size(); i++) {
				// Draw point from pieceSelection to its valid moves
				g2.drawLine(x, y, validMoves.get(i).getX() * Main.tileSize + Main.tileSize / 2, validMoves.get(i).getY() * Main.tileSize + Main.tileSize / 2);
			}
		}
	}
	
	private void drawBackground(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, Main.windowWidth, Main.windowHeight);
	}
	
	private void drawSpaces(Graphics g) {
		Dimension panelSize = getParent().getSize();
		//System.out.println(panelSize);
		double horizontalSpacing = panelSize.getWidth() / Main.horizontalSpaces;
		double verticalSpacing = panelSize.getHeight() / Main.verticalSpaces;
		
		
		g.setColor(Color.white);
		for (int i = 0; i < Main.horizontalSpaces; i++) {
			for (int j = 0; j < Main.verticalSpaces; j++) {
				g.fillOval((int) (i * horizontalSpacing), (int) (j * verticalSpacing), (int) horizontalSpacing, (int) verticalSpacing);
			}
		}
	}
	
	private void drawLines(Graphics g) {
		int spacing = Main.tileSize / 2;
		
		// Set up drawing properties
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.blue);
		g2.setStroke(new BasicStroke(5));
		
		// Draw vertical lines
		for (int i = 0; i < Main.horizontalSpaces; i++) {
			//g2.drawLine(i * Main.tileSize + spacing, spacing, i * Main.tileSize + spacing, getParent().getHeight() - spacing);
			g2.drawLine(i * Main.tileSize + spacing, spacing, i * Main.tileSize + spacing, Main.tileSize * Main.verticalSpaces - spacing);
		}
		
		// Draw horizontal lines
		for (int i = 0; i < Main.verticalSpaces; i++) {
			g2.drawLine(spacing, i * Main.tileSize + spacing, getParent().getWidth() - spacing, i * Main.tileSize + spacing);
		}
		
		// Draw diagonals
		for (int i = 0; i < Main.horizontalSpaces; i++) {
			for (int j = 0; j < Main.verticalSpaces; j++) {
				if (i % 2 == 0 && j % 2 == 0) {
					// Draw down right
					if (j != Main.verticalSpaces - 1 && i != Main.horizontalSpaces - 1) {
						g2.drawLine(i * Main.tileSize + Main.tileSize / 2, j * Main.tileSize + Main.tileSize / 2, 
							(i + 1) * Main.tileSize + Main.tileSize / 2, (j + 1) * Main.tileSize + Main.tileSize / 2);
					}
					// Draw down left
					if (i != 0 && j != Main.verticalSpaces - 1)
						g2.drawLine(i * Main.tileSize + Main.tileSize / 2, j * Main.tileSize + Main.tileSize / 2, 
							(i - 1) * Main.tileSize + Main.tileSize / 2, (j + 1) * Main.tileSize + Main.tileSize / 2);
				}
				else if (i % 2 != 0 && j % 2 != 0) {
					g2.drawLine(i * Main.tileSize + Main.tileSize / 2, j * Main.tileSize + Main.tileSize / 2, 
						(i - 1) * Main.tileSize + Main.tileSize / 2, (j + 1) * Main.tileSize + Main.tileSize / 2);
					g2.drawLine(i * Main.tileSize + Main.tileSize / 2, j * Main.tileSize + Main.tileSize / 2, 
						(i + 1) * Main.tileSize + Main.tileSize / 2, (j + 1) * Main.tileSize + Main.tileSize / 2);
					
				}
			}
		}
		/*
		// Draw right diagonals
		for (int i = 0; i < Main.horizontalSpaces; i++) {
			// Draw every other space diagonal
			if (i % 2 == 0 && i + 4 < Main.horizontalSpaces) {
				int xStart = i * Main.tileSize + spacing;
				int xEnd = (i + 4) * Main.tileSize + spacing;
				int yStart = spacing;
				int yEnd = getParent().getHeight() - spacing;
				g2.drawLine(xStart, yStart, xEnd, yEnd);
			}
		}
		// Draw shorter right diagonal
		g2.drawLine(getParent().getWidth() - (2 * Main.tileSize + spacing) , spacing, getParent().getWidth() - spacing, Main.tileSize * 2 + spacing);
		g2.drawLine(spacing, 2 * Main.tileSize + spacing, 2 * Main.tileSize + spacing, getParent().getHeight() - spacing);
		
		// Draw left diagonals
		for (int i = Main.horizontalSpaces; i >= 0; i--) {
			// Draw every other space diagonal
			if (i % 2 == 0 && i - 4 >= 0) {
				int xStart = i * Main.tileSize + spacing;
				int xEnd = (i - 4) * Main.tileSize + spacing;
				int yStart = spacing;
				int yEnd = getParent().getHeight() - spacing;
				g2.drawLine(xStart, yStart, xEnd, yEnd);
			}
		}
		// Draw shorter left diagonal
		g2.drawLine(2 * Main.tileSize + spacing, spacing, spacing, 2 * Main.tileSize + spacing);
		g2.drawLine(getParent().getWidth() - spacing, 2 * Main.tileSize + spacing, getParent().getWidth() - 2 * Main.tileSize - spacing, getParent().getHeight() - spacing);
		*/
	}
	
	//Draw game pieces over board	
	public void drawPieces(Graphics g) {
		ArrayList<GamePiece> pieces = gameManager.getBoard().getPiecesList();
		for (GamePiece piece : pieces) {
			if (piece != null) {
				int x = piece.getColumn();
				int y = piece.getRow();
				Color color = piece.getColor();
				
				int xCoord = Main.tileSize * x + Main.pieceSize / 2;
				int yCoord = Main.tileSize * y + Main.pieceSize / 2;
				g.setColor(color);
				
				g.fillOval(xCoord, yCoord, Main.pieceSize, Main.pieceSize);
			}
		}
	}
	
	private void drawSelection(Graphics g) {
		g.setColor(Color.green);
		if (pieceSelection != null) {
			g.fillOval(pieceSelection.getX(), pieceSelection.getY(), Main.pieceSize, Main.pieceSize);
		}
		pieceSelection = null;
	}
	
	private void drawTileSelection(Graphics g) {
		if (tileSelection != null) {
			g.setColor(Color.green);
			g.fillOval(tileSelection.getX(), tileSelection.getY(), Main.tileSize, Main.tileSize);
		}
		tileSelection = null;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		int xTile = coordToTile(x);
		int yTile = coordToTile(y);
		
		// Highlight the piece if there is one
		if (isPiecePresent(xTile, yTile)) {
			System.out.println("Piece present at clicked location");
			int xCoord = xTile * Main.tileSize + Main.pieceSize / 2;
			int yCoord = yTile * Main.tileSize + Main.pieceSize / 2;
			pieceSelection = new Point(xCoord, yCoord);
			repaint();
		}
		else {
			// Highlight the tile
			int xCoord = xTile * Main.tileSize;
			int yCoord = yTile * Main.tileSize;
			tileSelection = new Point(xCoord, yCoord);
			repaint();
		}
		
	}
	
	boolean isPiecePresent(int x, int y) {
		ArrayList<GamePiece> pieces = gameManager.getBoard().getPiecesList();
		for (GamePiece piece : pieces) {
			if (piece != null && piece.getColumn() == x && piece.getRow() == y)
				return true;
		}
		return false;
	}
	
	int coordToTile(int offset) {
		return offset / Main.tileSize;
	}
}
