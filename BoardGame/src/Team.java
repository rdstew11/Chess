
public class Team {

	private String identity;
	private int wins;
	private int forward;
	
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
	
	public void setForward(int i)
	{
		if(i > 0)
		{
			forward = 1;
		}
		else if(i < 0)
		{
			forward = -1;
		}
		boolean one = i < 0;
		boolean two = i > 0;
		System.out.println("Forward for " + toString() + " set to " + forward + " by int " + i + " i < 0 = " + one + "i > 0 = " + two);
	}
	
	public int getForward()
	{
		return forward;
	}
	
	public String toString()
	{
		return identity;
	}
}
