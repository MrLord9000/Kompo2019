package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import controller.ISaveable;
import controller.Match;

/**
 * Class responsible for implementing database saving
 * @author Adrian Zieliñski
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
			PreparedStatement stat = con.prepareStatement("INSERT INTO Matches(id, home, away, startTime, descr) VALUES(?, ?, ?, ?, ?)");
			stat.setLong(1, m.getId());
			stat.setString(2, m.getHome().getName());
			stat.setString(3, m.getAway().getName());
			stat.setDate(4, new java.sql.Date(m.getStartTime().getTimeInMillis()));
			stat.setString(5, m.getDescription());
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

}
