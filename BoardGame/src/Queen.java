
public class Queen extends Piece{

	public Queen(Team team)
	{
		super("queen", team);
		this.moveList = new String[] {"r","c","d"};
	}
}
