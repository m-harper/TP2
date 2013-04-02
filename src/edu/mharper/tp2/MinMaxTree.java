package edu.mharper.tp2;

import java.util.ArrayList;

public class MinMaxTree {
	
	private MaxNode root;
	private int height;
	private final int MAX_VALUE = 100;
	private final int MIN_VALUE = 0;
	private final int MEAN_VALUE = (MAX_VALUE + MIN_VALUE) / 2;
	
	
	public MinMaxTree() 
	{
		root = new MaxNode(null, MEAN_VALUE, 0);
	}
	
	public Node getRoot()
	{
		return root;
	}
	
	//Generates moves based on a starting game state
	public void generate(GameManager manager, Node node, int numMoves)
	{
		if(numMoves == 0)
			return;
		
		ArrayList<Move> allMoves = GameEval.getAllValidMoves(manager);
		for(Move move : allMoves)
		{
			GameManager nextManager = new GameManager(manager);
			GameEval.makeMove(nextManager, move);
			nextManager.endTurn();
			Node child = node.addChild(move, getUtility(nextManager.getBoard()));
			System.out.println("Added child: " + child);
			generate(nextManager, child, numMoves - 1);
		}
	}
	
	//Generates utility value of board state
	public int getUtility(GameBoard board)
	{
		double util = GameEval.UtilityFunction(board);
		int scaledUtil = (int)(util * (MAX_VALUE - MIN_VALUE) + MIN_VALUE);
		return scaledUtil;
	}
	
	//Prints tree one level at a time
	public void printAll()
	{
		ArrayList<Node> toPrint = new ArrayList<Node>();
		toPrint.add(root);
		
		while(!toPrint.isEmpty())
		{
			Node nextNode = toPrint.remove(0);
			System.out.println(nextNode);
			toPrint.addAll(nextNode.getChildren());
		}
	}
	
	/*
	minimax(player,board)
    if(game over in current board position)
        return winner
    children = all legal moves for player from this board
    if(max's turn)
        return maximal score of calling minimax on all the children
    else (min's turn)
        return minimal score of calling minimax on all the children
	*/
		
}