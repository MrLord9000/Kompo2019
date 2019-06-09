package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Score implements XMLable
{
	private Integer homeGoals;
	private Integer awayGoals;
	
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

	@Override
	public Element createNode(Document doc)
	{
		Element score = doc.createElement("Score");
		Element homeGoals = doc.createElement("homeGoals");
		homeGoals.appendChild(doc.createTextNode(this.homeGoals.toString()));
		score.appendChild(homeGoals);
		Element awayGoals = doc.createElement("awayGoals");
		awayGoals.appendChild(doc.createTextNode(this.awayGoals.toString()));
		score.appendChild(awayGoals);
		return score;
	}

	@Override
	public void loadFromDocument(Document doc)
	{
		// TODO Auto-generated method stub
		
	}
}
