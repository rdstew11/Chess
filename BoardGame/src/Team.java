
public class Team {

	private String identity;
	private int wins;
	
	public Team(String identifier)
	{
		identity = identifier;
		wins = 0;
	}
	
	public void setIdentity(String identifier)
	{
		identity = identifier;
	}
	
	public String getIdentity()
	{
		return identity;
	}
	
	public void setWins(int n)
	{
		wins = n;
	}
	
	public int getWins()
	{
		return wins;
	}
	
	public void addWin() 
	{
		wins++;
	}
}
