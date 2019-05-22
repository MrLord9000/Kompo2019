package controller;

import java.util.GregorianCalendar;

public class Match {
	
	private long id;
	private Team home;
	private Team away;
	private Score score;
	private GregorianCalendar startTime;
	
	
	public Match(long id, Team home, Team away, GregorianCalendar startTime) {
		this.id = id;
		this.home = home;
		this.away = away;
		this.startTime = startTime;
		this.score = null;
	}

	public Score getScore() 
	{
		return score;
	}
	
	public void setScore(Score score) 
	{
		this.score = score;
	}
	
	public long getId() 
	{
		return id;
	}
	
	public Team getHome() 
	{
		return home;
	}
	
	public Team getAway() 
	{
		return away;
	}
	
	public GregorianCalendar getStartTime() 
	{
		return startTime;
	}
	
	

}
