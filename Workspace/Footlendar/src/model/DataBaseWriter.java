package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import controller.ISaveable;

/**
 * Class responsible for implementing database saving.
 * Contains methods for both updating and saving to database when needed.
 * @author Adrian Zieliński
 *
 */
public class DataBaseWriter implements ISaveable<Match>
{
	private String path;

	/**
	 * Class constructor.
	 * @param path 	Sets the path to the database.
	 */
	public DataBaseWriter(String path) {
		this.path = path;
	}
	
	/**
	 * Method responsible for saving data from database specified in constructor
	 * @param m 	Match to be saved to the database
	 */
	@Override
	public void save(Match m)
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection(path);
			PreparedStatement stat = con.prepareStatement("INSERT INTO Matches(home, away, startTime, descr) VALUES(?, ?, ?, ?)");
			//stat.setLong(1, m.getId());
			stat.setString(1, m.getHome().getName());
			stat.setString(2, m.getAway().getName());
			stat.setDate(3, new java.sql.Date(m.getStartTime().getTimeInMillis()));
			stat.setString(4, m.getDescription());
			stat.execute();
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
	
	/**
	 * Method responsible for saving data from database specified in constructor
	 * @param col 	Collection to be saved to the database
	 */
	@Override
	public void saveCollection(LinkedList<Match> col)
	{
		for(Match m: col)
		{
			save(m);
		}
		
	}
	
	public void update(Match m)
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection(path);
			PreparedStatement stat = con.prepareStatement("UPDATE Matches SET scoreHome = ?, scoreAway = ? WHERE id = ?");
			stat.setInt(1, m.getScore().getHomeGoals());
			stat.setInt(2, m.getScore().getAwayGoals());
			stat.setLong(3, m.getId());
			stat.execute();
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
	
	public void deleteBefore(GregorianCalendar cal)
	{
		java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection(path);
			PreparedStatement del = con.prepareStatement("DELETE FROM Matches WHERE DATADIFF(?, startTime) > 0");
			del.setDate(1, date);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
