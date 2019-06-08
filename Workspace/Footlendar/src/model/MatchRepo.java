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

import controller.FlashScoreHtmlScoreReader;
import controller.Match;
import controller.Score;
import controller.Team;
import controller.TestHtmlScoreReader;
import controller.User;

public class MatchRepo implements IRepository<Match, Long> {

	private LinkedList<Match> matches;
	private static MatchRepo instance = new MatchRepo();
	
	public static MatchRepo getInstance()
	{
		return instance;
	}
	
	public MatchRepo() {
		this.matches = new LinkedList<>();
		
		// Temp
		//matches.add(new Match(0, new Team("Francja U20"), new Team("USA U20"), (GregorianCalendar) GregorianCalendar.getInstance(), "to 2323est opis"));
		//matches.add(new Match(1, new Team("Włochy U20"), new Team("Polska U20"), (GregorianCalendar) GregorianCalendar.getInstance(), "to j11est opis"));
		//matches.add(new Match(2, new Team("Argentyna U20"), new Team("Mali U20"), new GregorianCalendar(2019, Calendar.JUNE, 22), "to jest o434pis"));
		// Temp end
	}

	public void load() {
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=FootlendarDB;integratedSecurity=true");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM Matches");
			
			while(rs.next())
			{
				
				GregorianCalendar cal = convertSQLDateToGregorianCalendar(rs.getDate("startTime"), rs.getTime("startTime"));
				Match m = new Match(rs.getInt("id"), TeamRepo.getInstance().get(rs.getString("home")), TeamRepo.getInstance().get(rs.getString("away")), cal, rs.getString("descr"));
				Integer sh = rs.getInt("scoreHome");
				Integer sa = rs.getInt("scoreAway");
				
				//System.out.println(sa);
				if(sh >= 0 && sa >= 0)
				{
					Score score = new Score(sh, sa);
					m.setScore(score);
				}
				else if(cal.before(new GregorianCalendar()))
				{
					m.setScore(new FlashScoreHtmlScoreReader().getScore(m));
					GregorianCalendar endTime = (GregorianCalendar) cal.clone();
					endTime.add(Calendar.HOUR, 3);
					if(endTime.before(new GregorianCalendar()) && sh == -1 && sa == -1)
					{
						System.out.println(m);
						PreparedStatement stat2 = con.prepareStatement("UPDATE Matches SET scoreHome=" + m.getScore().getHomeGoals() + " WHERE id=" + m.getId());
						PreparedStatement stat3 = con.prepareStatement("UPDATE Matches SET scoreAway=" + m.getScore().getAwayGoals() + " WHERE id=" + m.getId());
						stat2.executeUpdate();
						stat3.executeUpdate();
						con.commit();
					}
				}
				matches.add(m);
			}
			
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
				User.getInstance().remove(m);
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
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-41IQBFQ\\WINCCPLUSMIG2014;databaseName=FootlendarDB;integratedSecurity=true");
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
