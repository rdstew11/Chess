
public class King extends Piece{
	
	public King(Team team)
	{
		super("king",team);
		this.moveList = new String[]{"a", "castle"};
		this.identifier = team.getIdentity().substring(0,1) + "K";
	}
}
