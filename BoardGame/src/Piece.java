
public class Piece {
	protected String type = "";
	protected String[] moveList;
	protected Team team;
	
	/**
	 * Creates a basic piece object
	 * @param type what kind of piece it is (like a rook in chess).
	 * @param color the team the piece belongs to 
	 */
	public Piece(String type, Team team)
	{
		this.type = type;
		this.team = team;
	}
	
	public Piece()
	{
		
	}
	
	public Team getTeam()
	{
		return team;
	}
	
	public void setTeam(Team team)
	{
		this.team = team;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public String[] getMoveList()
	{
		return moveList;
	}
	
	public void setMoveList(String[] moveList)
	{
		this.moveList = moveList;
	}
	
	
}
