package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import controller.FlashScoreHtmlScoreReader;
import controller.ILoadable;
import controller.ISaveable;
import controller.Match;
import controller.Score;
import controller.Team;
import controller.TestHtmlScoreReader;
import controller.User;

public class MatchRepo implements IRepository<Match, Long> {

	private LinkedList<Match> matches;
	private static MatchRepo instance = new MatchRepo();
	private ILoadable<Match> loader;
	private ISaveable saver;
	
	public static MatchRepo getInstance()
	{
		return instance;
	}
	
	public MatchRepo() {
		this.matches = new LinkedList<>();
		
		// Temp
//		GregorianCalendar cal1 = (GregorianCalendar) Calendar.getInstance();
//		cal1.add(Calendar.HOUR_OF_DAY, 1);
//		GregorianCalendar cal2 = (GregorianCalendar) Calendar.getInstance();
//		cal2.add(Calendar.HOUR_OF_DAY, -1);
//		matches.add( new Match(666, TeamRepo.getInstance().get("Ukraina U20"), TeamRepo.getInstance().get("Włochy U20"), cal1, "World Cup U20 Final Stage") );
//		matches.add( new Match(667, TeamRepo.getInstance().get("Ecuador U20"), TeamRepo.getInstance().get("Korea Po?udniowa U20"), cal2, "World Cup U20 Final Stage") );
//		matches.add( new Match(668, TeamRepo.getInstance().get("Ukraina U20"), TeamRepo.getInstance().get("Włochy U20"), new GregorianCalendar(2019, 5, 12, 14, 7), "World Cup U20 Final Stage") );
//		// Temp end
	}
	
	public void setLoader(ILoadable<Match> l)
	{
		if(l != null)
		{
			this.loader = l;
		}
	}
	
	public void setSaver(ISaveable s)
	{
		if(s != null)
		{
			this.saver = s;
		}
	}

	public void load() {
		
		matches = loader.load();
		
	}

	@Override
	public void save() {
		try
		{
			saver.save(matches);
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Match get(Long id) {
		for(Match m: matches)
		{
			if(id == m.getId()) return m;
		}
		return null;
	}

	@Override
	public List<Match> getAll() {
		return matches;
	}

	@Override
	public void add(Match item) {
		matches.add(item);
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
	}
	
	public void removeBefore(GregorianCalendar before)
	{
		for(Iterator<Match> it = matches.iterator(); it.hasNext();)
		{
			Match m = it.next();
			if(m.getStartTime().before(before))
			{
				User.getInstance().removeTrackedMatch(m);
				it.remove();
			}
		}
	}
		
	private GregorianCalendar convertSQLDateToGregorianCalendar(java.sql.Date date, java.sql.Time time)
	{
		//System.out.println(date.getHours());
		//System.out.println(date.getMinutes());
		GregorianCalendar c = new GregorianCalendar(date.getYear() + 1900, date.getMonth(), date.getDate(), time.getHours(), time.getMinutes());
		return c;
	}

	
	public void updateScore(Match m)
	{
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=FootlendarDB;integratedSecurity=true");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("UPDATE Matches SET scoreHome=" + m.getScore().getHomeGoals() + "AND SET scoreAway=" + m.getScore().getAwayGoals());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}