import java.util.Random;

public class RandomBot extends Bot {
	
	public RandomBot(String color)
	{
		super(color);
	}
	
	public int[][] getMove(Board board)
	{
		Square[][] validMoves = null;
		validMoves = board.getAllLegalMoves(getColor());
		int choice = random(0, validMoves.length-1);
		int[][] move = {validMoves[choice][0].convert(),validMoves[choice][1].convert()};
		return move;
	}

}
