package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import controller.ILoadable;
import controller.ISaveable;
import controller.Team;

public class TeamRepo implements IRepository<Team, String> {

	private LinkedList<Team> teams;
	private static TeamRepo instance = new TeamRepo();
	private ILoadable<Team> loader;
	private ISaveable saver;
	
	
	private TeamRepo()
	{
		teams = new LinkedList<>();
	}
	
	public static TeamRepo getInstance()
	{
		return instance;
	}
	
	public void setLoader(ILoadable<Team> l)
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
	
	@Override
	public void load() {
		if(loader != null)
		this.teams = loader.load();
		
	}

	@Override
	public void save() {
		
	;
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
		teams.add(item);
		//System.out.println("Added team " + item.getName());
	}

	public void remove(Team item) {
		teams.remove(item);
		
	}

	@Override
	public void remove(String id)
	{
		// TODO Auto-generated method stub
		
	}

}
