package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * Database loader class. Used in Team Repo for loading data from database
 * @author Adrian Zieliñski
 */
public class DataBaseTeamLoader implements ILoadable<Team>
{
private String path;
	
	/**
	 * Class constructor.
	 * @param path 	Sets the path to the database.
	 */
	public DataBaseTeamLoader(String path) {
		this.path = path;
	}

	/**
	 * Method responsible for loading data from database specified in constructor
	 */
	@Override
	public LinkedList<Team> load()
	{
		LinkedList<Team> teams = new LinkedList<>();
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection(path);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM Teams");
			while(rs.next())
			{
				teams.add(new Team(rs.getString("name")));
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
		return teams;
	}

}
