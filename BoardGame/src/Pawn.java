
public class Pawn extends Piece {

	public Pawn(Team team)
	{
		super("pawn", team);
		this.moveList = new String[] {"f","f2","d1"};
	}
	
	public void setHasMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
		this.moveList = new String[] {"f","d1",};
	}
}
