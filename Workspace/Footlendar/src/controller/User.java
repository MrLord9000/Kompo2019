package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

import model.MatchRepo;
import model.TeamRepo;
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
	
	private void loadTeams()
	{
		ObjectFileLoader<LinkedList<String>> ofl = new ObjectFileLoader<>(".\\src\\favTeams.bin");
		LinkedList<String> names = null;
		try
		{
			names = ofl.load();
			for (Team t : TeamRepo.getInstance().getAll())
			{
				if (names.contains(t.getName()))
				{
					favTeams.add(t);
				}
			}
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void loadMatches()
	{
		// WCZYTYWANIE MECZY
		ObjectFileLoader<LinkedList<Long>> ofl2 = new ObjectFileLoader<>(".\\src\\trackedMatches.bin");
		LinkedList<Long> ids = null;

		try
		{
			ids = ofl2.load();
			for (Long l : ids)
			{
				trackedMatches.add(MatchRepo.getInstance().get(l));
			}
		} catch (ClassNotFoundException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		
		loadTeams();
		loadMatches();
		
	}
	
	private void saveTeams()
	{
		ObjectFileWriter ofw = new ObjectFileWriter(".\\src\\favTeams.bin");
		LinkedList<String> s = new LinkedList<>();
		for(Team t: favTeams)
		{
			s.add(t.getName());
		}
		try
		{
			ofw.save(s);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void saveMatches()
	{
		ObjectFileWriter ofw = new ObjectFileWriter(".\\src\\trackedMatches.bin");
		
		LinkedList<Long> l = new LinkedList<>();
		
		for(Match t: trackedMatches)
		{
			l.add(t.getId());
		}
		
		try
		{
			ofw.save(l);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save()
	{
		saveTeams();
		saveMatches();
	}
	
	public void addFavouriteTeam(Team team)
	{
		if(team != null && favTeams.contains(team) == false)
		{
			favTeams.add(team);
			saveTeams();
		}
		else
		{
			System.out.println("Wyjatek");
		}
		
	}
	
	public LinkedList<Match> getMonthMatches(int month, int year)
	{
		LinkedList<Match> output = new LinkedList<Match>();
		LinkedList<Match> allMatches = (LinkedList<Match>)MatchRepo.getInstance().getAll(); 
		for(Match item : allMatches)
		{
			if(item.getStartTime().get(Calendar.MONTH) == month - 1 && item.getStartTime().get(Calendar.YEAR) == year)
			{
				output.add(item);
			}
		}
		return output;
	}

	public LinkedList<Match> getDayMatches(int day, int month, int year)
	{
		LinkedList<Match> output = new LinkedList<Match>();
		LinkedList<Match> allMatches = (LinkedList<Match>)MatchRepo.getInstance().getAll(); 
		for(Match item : allMatches)
		{
			if(item.getStartTime().get(Calendar.DAY_OF_MONTH) == day && item.getStartTime().get(Calendar.MONTH) == month - 1 && item.getStartTime().get(Calendar.YEAR) == year)
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
	
	
	public void addTrackedMatch(Match match) throws MatchAlreadyInCollectionException
	{
		System.out.println(trackedMatches.contains(match));
		if(match != null && trackedMatches.contains(match) == false)
		{
			trackedMatches.add(match);
			saveMatches();
		}
		else
		{
			throw new MatchAlreadyInCollectionException("Specified match: " + match.toString() + " is already in tracked matches repository");
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
	
	public void notifyOnUpdate(Match match)
	{
		if(containsFavClub(match))
		{
			notifier.notify("Goal!!!\n " + match.info());
		}
	}
	
	
	public void notifyBefore(Match match, int minutesBefore)
	{
		if(containsFavClub(match))
		{
			notifier.notify("Match starts in " +minutesBefore + " minutes\n " + match.info());
		}
	}
	
	public LinkedList<Team> getFavTeams()
	{
		return favTeams;
	}
	
	public LinkedList<Match> getTrackedMatches()
	{
		return trackedMatches;
	}
}
