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
	
	public void testAIVersusAI()
	{
		View view = new View();
		while(true)
		{
			Move firstMove = GameEval.getOptimalFirstMove(view.gameView.gameManager, Main.maxAITurns);
			GameEval.makeMove(view.gameView.gameManager, firstMove);
			view.gameView.endTurnAndUpdate();
			
			Move secondMove = GameEval.getOptimalSecondMove(view.gameView.gameManager, Main.maxAITurns);
			GameEval.makeMove(view.gameView.gameManager, secondMove);
			view.gameView.endTurnAndUpdate();
		}
	}
}