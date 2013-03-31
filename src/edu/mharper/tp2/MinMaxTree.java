package edu.mharper.tp2;

public class MinMaxTree {
	
	public MinMaxTree() {
		
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
	private class Node {
		
		private int value;
		private int depth;
		private boolean isMin;
		
		public Node() {
			
		}
		
		
	}
}