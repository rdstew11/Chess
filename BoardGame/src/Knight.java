
public class Knight extends Piece{

	public Knight(Team team)
	{
		super("knight",team);
		this.identifier = team.getIdentity().substring(0,1) + "n";
		this.moveList = new String[] {"L"};
	}
}
