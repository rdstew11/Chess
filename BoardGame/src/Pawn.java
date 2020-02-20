
public class Pawn extends Piece {

	public Pawn(Team team)
	{
		super("pawn", team);
		this.moveList = new String[] {"pf","pf2","pa"};
	}
	
	public void setHasMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
		this.moveList = new String[] {"pf","pa",};
	}
}
