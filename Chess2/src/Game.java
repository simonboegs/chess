//controls the main game logic
import java.util.Scanner;
public class Game {
	private static Board board;
	public static void main(String [] args) //main method
	{
		board = new Board();
		board.printBoard();
		Visual v = new Visual(board);
		v.drawBoard();
		Bot randomBot = new RandomBot("white");
		Bot mmBot = new minimaxBot("black");
		Bot pruneBot = new PruneBot("black");
		//Bot whiteBot = new PruneBot("white");
		while(!board.checkMate("white") && !board.checkMate("black"))
		{
			Square[] controlled = board.getAllControlledSquares("white");
			for(Square i: controlled)
				System.out.println(i.printPos());
			if(!board.checkMate("white"))
			{
				if(board.inCheck("white"))
					System.out.println("White is in check.");
				turn("white");
				//botTurn(whiteBot);
			}
			else
				System.out.println("Game over. " + "Black" + " wins.");
			board.printBoard();
			v.redrawBoard(board);
			if(!board.checkMate("black"))
			{
				if(board.inCheck("black"))
					System.out.println("Black is in check.");
				//turn("black");
				long startTime = System.currentTimeMillis();
				botTurn(pruneBot);
				long endTime = System.currentTimeMillis();
				System.out.println("bot move took " + (endTime - startTime)/1000 + " seconds");
			}
			else
				System.out.println("Game over. " + "White" + " wins.");
			board.printBoard();
			v.redrawBoard(board);
		}
	}
	
	public static boolean gameOver(Board board, String color)
	{
		if(board.checkMate(color))
			return true;
		return false;
	}
	public static void turn(String color)
	{
		System.out.println(color + "'s turn.");
		//int[][] move = reqMove(color);
		int[][] move = ClickListener.getInput();
		int[] oldCord = move[0];
		int[] newCord = move[1];
//		int[] oldCord = {6,0};
//		int[] newCord = {5,0};
		boolean success = board.move(oldCord,newCord,color);
		if(!success)
		{
			System.out.println("Invalid move.");
			turn(color);
		}
	}
	public static void botTurn(Bot bot)
	{
		System.out.println(bot.getColor() + "'s turn. [bot]");
		int[][] move = bot.getMove(board);
		int[] oldCord = move[0];
		int[] newCord = move[1];
		boolean success = board.move(oldCord,newCord,bot.getColor());
		if(!success)
		{
			System.out.println("Invalid move.");
			botTurn(bot);
		}
	}
	public static int[][] reqMoveGUI(String color)
	{
		text t = new text(color);
		t.main();
		while(!t.getBoolean())
		{
			System.out.println(t.getBoolean());
		}
		String[] s = t.getInputs();
		int[][] inputs = {{Integer.parseInt(s[0]),Integer.parseInt(s[1])},{Integer.parseInt(s[2]),Integer.parseInt(s[3])}};
		return inputs;
	}
	
	public static int[][] reqMove(String color)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter oldPos: ");
		String oldPos = in.nextLine();
		System.out.println("Enter newPos: ");
		String newPos = in.nextLine();
		String[] oldStr = oldPos.split(" ");
		String[] newStr = newPos.split(" ");
		int[] oldInt = {Integer.parseInt(oldStr[0]),Integer.parseInt(oldStr[1])};
		int[] newInt = {Integer.parseInt(newStr[0]),Integer.parseInt(newStr[1])};
		int[][] move = {{2,2},{1,1}};
		int[][] input = {oldInt,newInt};
		return input;
	}
}
