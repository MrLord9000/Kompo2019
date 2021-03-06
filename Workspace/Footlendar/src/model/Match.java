package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import controller.NotifyTimer;
import controller.XMLable;

/**
 * Main match class. contains all the data regarding a specific match
 * @author Adrian Zieliński
 *
 */
public class Match implements XMLable{
	
	private long id;
	private Team home;
	private Team away;
	private Score score;
	private GregorianCalendar startTime;
	private String description;
	private int minutesRemindBeforeStart = 1;
	private NotifyTimer nt;
	
	/**
	 * Class constructor.
	 * @param id			Match id to be created
	 * @param home			Home team to be added
	 * @param away			Away team to be added
	 * @param startTime		Start taime of the match
	 * @param description	Match description
	 */
	public Match(long id, Team home, Team away, GregorianCalendar startTime, String description) {
		this.id = id;
		this.home = home;
		this.away = away;
		this.startTime = startTime;
		this.score = null;
		this.description = description;
		
		nt = new NotifyTimer();
		nt.start(this.startTime, minutesRemindBeforeStart, this);
		
		
	}
	
	public Match(Team team, Team team2, GregorianCalendar gregorianCalendar, String text) {
		this(DataBaseMatchLoader.getNextId() + 1, team, team2, gregorianCalendar, text);
	}

	public void setDescription(String desc)
	{
		description = desc;
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
	
	public void setminutesRemindBeforeStart(int minutes)
	{
		minutesRemindBeforeStart = minutes;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Gives information about current match
	 * @return String with info about match: score, teams and time
	 */
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
			s += " " + score.getHomeGoals() + " : " + score.getAwayGoals();
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
	
	/**
	 * @see info()
	 */
	@Override
	public String toString()
	{
		return info();
	}
	
	public void resetTimer()
	{
		nt.resetRemindTimer(startTime, minutesRemindBeforeStart, this);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if(obj == null) return false;
		Match m = (Match) obj;
		if(m.id == this.id) return true;
		else return false;
		
	}

	/**
	 * Creates XML node from current match object
	 */
	@Override
	public Element createNode(Document doc)
	{
		Element match = doc.createElement("Match");
		Element id = doc.createElement("ID");
		id.appendChild(doc.createTextNode(this.id + ""));
		match.appendChild(id);
		Node home = this.home.createNode(doc);
		match.appendChild(home);
		Node away = this.away.createNode(doc);
		match.appendChild(away);
		if(score != null)
		{
			Node score = this.score.createNode(doc);
			match.appendChild(score);
		}
		Element year = doc.createElement("Year");
		year.appendChild(doc.createTextNode((this.startTime.get(Calendar.YEAR) + "")));
		Element month = doc.createElement("Month");
		month.appendChild(doc.createTextNode((this.startTime.get(Calendar.MONTH) + 1 + "")));
		Element day = doc.createElement("Day");
		day.appendChild(doc.createTextNode((this.startTime.get(Calendar.DAY_OF_MONTH) + "")));
		Element hour = doc.createElement("Hour");
		hour.appendChild(doc.createTextNode((this.startTime.get(Calendar.HOUR_OF_DAY) + "")));
		Element minute = doc.createElement("Minute");
		minute.appendChild(doc.createTextNode((this.startTime.get(Calendar.MINUTE) + "")));
		Element startTime = doc.createElement("StartTime");
		startTime.appendChild(year);
		startTime.appendChild(month);
		startTime.appendChild(day);
		startTime.appendChild(hour);
		startTime.appendChild(minute);
		
		match.appendChild(startTime);
		
		Element description = doc.createElement("Description");
		description.appendChild(doc.createTextNode(this.getDescription()));
		match.appendChild(description);
		return match;
	}

	@Override
	public void loadFromDocument(Document doc)
	{
		// TODO Auto-generated method stub
		
	}

}
