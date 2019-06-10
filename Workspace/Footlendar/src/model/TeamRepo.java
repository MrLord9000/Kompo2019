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

/**
 * Team repository class, holds all teams and loader/saver
 * @author Adrian Zieliñski
 *
 */
public class TeamRepo implements IRepository<Team, String> {

	private LinkedList<Team> teams;
	private static TeamRepo instance = new TeamRepo();
	private ILoadable<Team> loader;
	private ISaveable saver;
	
	/*
	 * Class constructor. Initializes collections with empty LinkedLists
	 */
	private TeamRepo()
	{
		teams = new LinkedList<>();
	}
	
	/**
	 * Static method for getting singleton User instance
	 * @return singleton User instance
	 */
	public static TeamRepo getInstance()
	{
		return instance;
	}
	
	/**
	 * Sets default match repo loader
	 * @param l ILoadable to be set
	 */
	public void setLoader(ILoadable<Team> l)
	{
		if(l != null)
		{
			this.loader = l;
		}
	}
	
	/**
	 * Sets default match repo saver
	 * @param s ISaveable to be set
	 */
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