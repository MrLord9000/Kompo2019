package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DataBaseTeamLoader implements ILoadable<Team>
{
private String path;
	
	public DataBaseTeamLoader(String path) {
		this.path = path;
	}

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
