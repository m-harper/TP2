package edu.mharper.tp2;

import java.util.ArrayList;

public abstract class Node
{
	protected int value;
	protected Move move;
	protected ArrayList<Node> children;
	protected Node parent;

	public Node(Move mv)
	{
		move = mv;
		children = new ArrayList<Node>();
	}
	
	public Move getMove()
	{
		return move;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int val)
	{
		value = val;
	}
	
	public Node getParent()
	{
		return parent;
	}
	
	public void setParent(Node newParent)
	{
		parent = newParent;
	}
	
	public ArrayList<Node> getChildren()
	{
		return children;
	}
	
	public abstract Node addChild(Move childMove);
	public abstract boolean isMin();
	public abstract boolean isMax();
	
	@Override
	public String toString()
	{
		String moveStr;
		if(move == null || move.type == null)
			moveStr = "NULL";
		
		else
		{
			switch(move.type)
			{
			case CAPTURE:
				moveStr = "Cap (" + move.startPoint.getX() + "," + move.startPoint.getY() + ") -> (" +  
							move.endPoint.getX() + "," + move.endPoint.getY() + ")";
			 	break;
			case PAIKA:
				moveStr = "Pka (" + move.startPoint.getX() + "," + move.startPoint.getY() + ") -> (" +  
							move.endPoint.getX() + "," + move.endPoint.getY() + ")";
			 	break;
			case END_CHAIN:
				moveStr = "EndChain";
			 	break;
			case SACRIFICE:
				moveStr = "Sac (" + move.startPoint.getX() + "," + move.startPoint.getY() + ")";
			 	break;
			default:
				moveStr = "";
			 	break;
			}
		}
		
		String str = "([move] " + moveStr + ", [val]" + value + ")";
		return(str);
	}
}