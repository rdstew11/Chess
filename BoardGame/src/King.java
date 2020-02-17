
public class King extends Piece{
	
	public King(String team)
	{
		super("king",team);
		this.moveList = new String[]{"a", "castle"};
	}
}
