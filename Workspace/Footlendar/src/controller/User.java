package controller;

import java.util.LinkedList;

public class User {

	private static User instance = new User(); 
	private LinkedList<Team> favTeams;
	private LinkedList<Match> trackedMatches;
	private INotifier notifier;
	
	public User()
	{
		this.favTeams = new LinkedList<>();
		this.trackedMatches = new LinkedList<>();
	}
	
	public static User getInstance()
	{
		return instance;
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
	
	public void setNotifier(INotifier notifier)
	{
		if(notifier != null)
		{
			this.notifier = notifier;
		}
	}
	
	private boolean containsFavClub(Match match)
	{
		if(trackedMatches.contains(match)) return true;
		else if(favTeams.contains(match.getHome()) || favTeams.contains(match.getAway())) return true;
		else return false;
	}
	
	public void notifyOnStart(Match match)
	{
		if(containsFavClub(match))
		{
			notifier.notify("Match starts\n " + match.info());
		}
	}
	
	
	public void notifyBefore(Match match, int minutesBefore)
	{
		if(containsFavClub(match))
		{
			notifier.notify("Match starts in " +minutesBefore + " minutes\n " + match.info());
		}
	}
}
