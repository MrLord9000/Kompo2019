package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import controller.Team;

public class TeamRepo implements IRepository<Team, String> {

	private LinkedList<Team> teams;
	private static TeamRepo instance = new TeamRepo();
	
	
	private TeamRepo()
	{
		teams = new LinkedList<>();
	}
	
	public static TeamRepo getInstance()
	{
		return instance;
	}
	
	@Override
	public void load() {
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-41IQBFQ\\WINCCPLUSMIG2014;databaseName=FootlendarDB;integratedSecurity=true");
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
		
	}

	@Override
	public void save() {
		
		
	}

	@Override
	public Team get(String name) {
		for(Team t: teams)
		{
			if(t.getName().equals(name))
				return t;
		}
		return null;
	}

	@Override
	public List<Team> getAll() {
		return teams;
	}

	@Override
	public void add(Team item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

}
