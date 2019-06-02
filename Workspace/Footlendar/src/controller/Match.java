package controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Match {
	
	private long id;
	private Team home;
	private Team away;
	private Score score;
	private GregorianCalendar startTime;
	private String description;
	private static int minutesRemindBeforeStart = 1;
	private NotifyTimer nt;
	
	
	public Match(long id, Team home, Team away, GregorianCalendar startTime, String description) {
		this.id = id;
		this.home = home;
		this.away = away;
		this.startTime = startTime;
		this.score = null;
		this.description = description;
		
		if(this.startTime.after(new GregorianCalendar()))
		{
			nt = new NotifyTimer();
			nt.start(this.startTime, minutesRemindBeforeStart, this);
		}
		
		
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
	
	public static void setminutesRemindBeforeStart(int minutes)
	{
		minutesRemindBeforeStart = minutes;
	}
	
	public String info()
	{
		String s = new String(); 
		s += this.id + "  ";
		s += this.home.getName();
		if(score == null)
		{
			s += " - : - ";
		}
		else
		{
			s += score.getHomeGoals() + " : " + score.getAwayGoals();
		}
		s += " " + away.getName();
		s +=  "		" + startTime.get(Calendar.DAY_OF_MONTH) + "/" + (startTime.get(Calendar.MONTH) + 1) + 
				"/" + startTime.get(Calendar.YEAR) + " " + startTime.get(Calendar.HOUR_OF_DAY) + ":";
		if(startTime.get(Calendar.MINUTE) < 10)
		{
			s += "0";
		}
		s +=  startTime.get(Calendar.MINUTE) + ":";
		if(startTime.get(Calendar.SECOND) < 10)
		{
			s += "0";
		}
		s += startTime.get(Calendar.SECOND);
		s += "\t" + description + "\n";
		return s;
	}
	
	@Override
	public String toString()
	{
		return info();
	}
	
	public void resetTimer()
	{
		nt.resetRemindTimer(startTime, minutesRemindBeforeStart, this);
	}
	
	

}
