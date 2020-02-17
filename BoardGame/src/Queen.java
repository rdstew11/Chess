
public class Queen extends Piece{

	public Queen(String team)
	{
		super("queen", team);
		this.moveList = new String[] {"r","c","d"};
	}
}
