
public class King extends Piece {
	public King()
	{
		this("white");
	}
	public King(String color)
	{
		super(color,0);
	}
	public boolean moveIsValid(Square oldPos, Square newPos, Board board)
	{
		boolean valid = false;
		if((newPos.getRow() == -1 && newPos.getCol() == -1) || (oldPos.getRow() == -1 && oldPos.getCol() == -1))
			return false;
		Square above = board.getAbove(oldPos);
		Square below = board.getBelow(oldPos);
		Square left = board.getLeft(oldPos);
		Square right = board.getRight(oldPos);
		Square upLeft = board.getUpperLeft(oldPos);
		Square upRight = board.getUpperRight(oldPos);
		Square lowLeft = board.getLowerLeft(oldPos);
		Square lowRight = board.getLowerRight(oldPos);
		Square[] arr = new Square[] {above,below,left,right,upLeft,upRight,lowLeft,lowRight};
		for(Square i: arr)
		{
			//System.out.println("    " + i.printPos() + " " + (i.getPiece() instanceof None) + " " + !(i.getPiece().getColor().equals(getColor())));
			if(newPos.getRow() == i.getRow() &&
					newPos.getCol() == i.getCol() &&
					(i.getPiece() instanceof None || !(i.getPiece().getColor().equals(getColor())))
					)
			{
				//System.out.println("returned true");
				return true;
			}
		}
		return false;
	}
	public String getIcon()
	{
		if(getColor().equals("white"))
			return "K";
		else
			return "k";
	}
	public String getName()
	{
		return "king";
	}
}
