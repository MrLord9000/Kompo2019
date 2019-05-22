package controller;


public class Score 
{
	private int homeGoals;
	private int awayGoals;
	
	public Score()
	{
		homeGoals = 0;
		awayGoals = 0;
	}
	
	public Score(int homeGoals, int awayGoals)
	{
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
	}
	
	public int getHomeGoals()
	{
		return homeGoals;
	}
	
	public int getAwayGoals()
	{
		return awayGoals;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}

	public void setAwayGoals(int awayGoals) {
		this.awayGoals = awayGoals;
	}

	@Override
	public String toString() {
		return homeGoals + ":" + awayGoals;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(this == other) return true;
		if(other == null) return false;
		if(getClass() != other.getClass()) return false;
		Score s = (Score) other;
		
		if(this.homeGoals == s.homeGoals && this.awayGoals == s.awayGoals) return true;
		else return false;
	}
}
