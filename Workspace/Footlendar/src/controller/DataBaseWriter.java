package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

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

}
