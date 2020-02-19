
public class Rook extends Piece{

	public Rook(Team team)
	{
		super("rook",team);
		this.moveList = new String[] {"r","c","castle"};
	}
	
	public void hasMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
		this.moveList = new String[] {"r","c"};
	}
}
