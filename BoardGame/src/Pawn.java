
public class Pawn extends Piece {

	public Pawn(String team)
	{
		super("pawn", team);
		this.moveList = new String[] {"f","f1","d1"};
	}
}
