package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import controller.FlashScoreHtmlScoreReader;
import controller.ILoadable;

/**
 * Database loader class. Used in Match Repo for loading data from database
 * @author Adrian Zieli�ski
 */
public class DataBaseMatchLoader implements ILoadable<Match>
{
	private static String path;
	
	/**
	 * Class constructor.
	 * @param path 	Sets the path to the database.
	 */
	public DataBaseMatchLoader(String path) {
		DataBaseMatchLoader.path = path;
	}
	
	public static long getNextId()
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection(path);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT IDENT_CURRENT('Matches')");
			//System.out.println("Next ID: " + rs.getLong(1));
			rs.next();
			return rs.getLong(1);
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
		
	}
	
	/**
	 * Method responsible for loading data from database specified in constructor
	 */
	public LinkedList<Match> load() {
		LinkedList<Match> matches = new LinkedList<>();
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection(path);
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
		return matches;
				
	}
	
	private GregorianCalendar convertSQLDateToGregorianCalendar(java.sql.Date date, java.sql.Time time)
	{
		//System.out.println(date.getHours());
		//System.out.println(date.getMinutes());
		GregorianCalendar c = new GregorianCalendar(date.getYear() + 1900, date.getMonth(), date.getDate(), time.getHours(), time.getMinutes());
		return c;
	}

}