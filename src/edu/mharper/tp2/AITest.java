package edu.mharper.tp2;

public class AITest
{
	public AITest()
	{
	}
	
	public void testMinMaxTree()
	{
		MinMaxTree tree = new MinMaxTree();
		GameManager manager = new GameManager();
		manager.genBoard();
		tree.generate(manager, tree.getRoot(), 3);
		tree.printAll();
	}
}