package controller;

import java.util.Calendar;
import java.util.LinkedList;

import model.MatchRepo;
import view.INotifier;

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
	
	public LinkedList<Team> getFavTeams()
	{
		return favTeams;
	}
	
	public LinkedList<Match> getTrackedMatches()
	{
		return trackedMatches;
	}
	
	public LinkedList<Match> getMonthMatches(int month, int year)
	{
		LinkedList<Match> output = new LinkedList<Match>();
		LinkedList<Match> allMatches = (LinkedList<Match>)MatchRepo.getInstance().getAll(); 
		for(Match item : allMatches)
		{
			System.out.println(item.getStartTime().get(Calendar.MONTH));
			System.out.println(month);
			if(item.getStartTime().get(Calendar.MONTH) == month - 1 && item.getStartTime().get(Calendar.YEAR) == year)
			{
				output.add(item);
			}
		}
		return output;
	}
	
	public LinkedList<Match> getTrackedMonthMatches(int month, int year)
	{
		LinkedList<Match> output = new LinkedList<Match>();
		for(Match item : trackedMatches)
		{
			if(item.getStartTime().get(Calendar.MONTH) == month && item.getStartTime().get(Calendar.YEAR) == year)
			{
				output.add(item);
			}
		}
		return output;
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
