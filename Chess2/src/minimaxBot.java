
public class minimaxBot extends Bot {
	private int initDepth = 2;
	private int calls = 0;
	public minimaxBot(String color)
	{
		super(color);
	}
	
	public int[][] getMove(Board board)
	{
		calls = 0;
		Square[][] possibleMoves = null;
		possibleMoves = board.getAllLegalMoves(getColor());
		Board[] possibleBoards = new Board[possibleMoves.length];
		for(int i = 0; i < possibleMoves.length; i++)
		{
			possibleBoards[i] = new Board(board.getArr());
			possibleBoards[i].move(possibleMoves[i][0].convert(),possibleMoves[i][1].convert(), getColor());
		}
		int maxEval = -100;
		int index = -1;
		for(int i = 0; i < possibleBoards.length; i++)
		{
			int eval = minimax(possibleBoards[i], false, initDepth);
			if(eval > maxEval)
			{
				maxEval = eval;
				index = i;
			}
		}
		int[][] decision = {possibleMoves[index][0].convert(),possibleMoves[index][1].convert()};
		return decision;
	}
	
	private int minimax(Board board, boolean botsTurn, int depth)
	{
		calls++;
		System.out.println(calls);
		//System.out.println("minimaxing this board:");
		//board.printBoard();
		if(depth == 0 || board.checkMate("white") || board.checkMate("black"))
		{
			return scoreBoard(board);
		}
		
		String botColor = "";
		String playerColor = "";
		if(getColor().equals("black"))
		{
			botColor = "black";
			playerColor = "white";
		}
		else if(getColor().equals("white"))
		{
			botColor = "white";
			playerColor = "black";
		}
		
		Square[][] possibleMoves = null;
		possibleMoves = board.getAllLegalMoves(botColor);
		Board[] possibleBoards = new Board[possibleMoves.length];
		for(int i = 0; i < possibleMoves.length; i++)
		{
			possibleBoards[i] = new Board(board.getArr());
			possibleBoards[i].move(possibleMoves[i][0].convert(),possibleMoves[i][1].convert(), botColor);
		}
		
		Square[][] playerPossibleMoves = null;
		playerPossibleMoves = board.getAllLegalMoves(playerColor);
		Board[] playerPossibleBoards = new Board[playerPossibleMoves.length];
		for(int i = 0; i < playerPossibleMoves.length; i++)
		{
			playerPossibleBoards[i] = new Board(board.getArr());
			playerPossibleBoards[i].move(playerPossibleMoves[i][0].convert(),playerPossibleMoves[i][1].convert(), playerColor);
		}
		
		
		if(botsTurn)
		{
			int maxEval = -10000;
			for(Board i: possibleBoards)
			{
				int eval = minimax(i, false, depth-1);
				if(eval > maxEval)
					maxEval = eval;
			}
			return maxEval;
		}
		else
		{
			int minEval = 10000;
			for(Board i: playerPossibleBoards)
			{
				int eval =  minimax(i, true, depth-1);
				if(eval < minEval)
					minEval = eval;
			}
			return minEval;
		}
	}
}
