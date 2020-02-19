
public class Piece {
	protected String type = "";
	protected String[] moveList;
	protected Team team;
	protected String identifier;
	protected boolean hasMoved;
	
	/**
	 * Creates a basic piece object
	 * @param type what kind of piece it is (like a rook in chess).
	 * @param color the team the piece belongs to 
	 */
	public Piece(String type, Team team)
	{
		this.type = type;
		this.team = team;
		this.identifier = team.getIdentity().substring(0,1) + type.substring(0,1);
		hasMoved = false;
	}
	
	public Piece()
	{
		hasMoved = false;
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
	
	public String toString()
	{
		return type;
	}
	
	public void setIdentifier(String identity)
	{
		identifier = identity;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public boolean getHasMoved()
	{
		return hasMoved;
	}
	
	public boolean checkHasMoved()
	{
		return hasMoved;
	}
	
	public void setHasMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
	}
	
}
