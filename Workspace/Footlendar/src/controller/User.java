package controller;

import java.util.LinkedList;

public class User {

	private LinkedList<Team> favTeams;
	private LinkedList<Match> trackedMatches;
	
	public User()
	{
		this.favTeams = new LinkedList<>();
		this.trackedMatches = new LinkedList<>();
	}
	
	public void addFavouriteTeam(Team team)
	{
		if(team != null)
		{
			favTeams.add(team);
		}
	}
	
	
	public void addTrackedMatch(Match match)
	{
		if(match != null)
		{
			trackedMatches.add(match);
		}
	}
}
