package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

import model.MatchRepo;
import model.TeamRepo;
import view.INotifier;

/**
 * User class holds information about favourite teams and matches, as well as about tracked matches.
 * It is also used to set appropriate notifier.
 * @author Adrian Zieliñski
 *
 */
public class User {

	private static User instance = new User(); 
	private LinkedList<Team> favTeams;
	private LinkedList<Match> trackedMatches;
	private LinkedList<Match> favMatches;
	private INotifier notifier;
	
	/*
	 * Class constructor. Initializes collections with empty LinkedLists
	 */
	public User()
	{
		this.favTeams = new LinkedList<>();
		this.favMatches = new LinkedList<>();
		this.trackedMatches = new LinkedList<>();
	}
	
	/**
	 * Static method for getting singleton User instance
	 * @return singleton User instance
	 */
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
		} 
		catch (ClassNotFoundException | IOException e)
		{
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
	
	private void saveMatches(LinkedList<Match> collection, String filepath)
	{
		ObjectFileWriter ofw = new ObjectFileWriter(filepath);
		
		LinkedList<Long> l = new LinkedList<>();
		
		for(Match t: collection)
		{
			l.add(t.getId());
		}
		
		try
		{
			ofw.save(l);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void saveTrackedMatches()
	{
		saveMatches(trackedMatches, ".\\src\\trackedMatches.bin");
	}
	
	private void saveFavMatches()
	{
		saveMatches(favMatches, ".\\src\\favMatches.bin");
	}
	
	public void save()
	{
		saveTeams();
		saveTrackedMatches();
		saveFavMatches();
	}
	
	public void addFavouriteTeam(Team team) throws ElementAlreadyInCollectionException
	{
		if(team != null && favTeams.contains(team) == false)
		{
			favTeams.add(team);
			saveTeams();
		}
		else
		{
			throw new ElementAlreadyInCollectionException("Team " + team.getName() + " already in collection favTeams!");
		}
		
	}
	
	public void addFavouriteMatch(Match match) throws ElementAlreadyInCollectionException
	{
		if(match != null && favMatches.contains(match) == false)
		{
			favMatches.add(match);
			saveFavMatches();
		}
		else
		{
			throw new ElementAlreadyInCollectionException("Match " + match.info() + " already in collection favTeams!");
		}
	}
	
	public void addTrackedMatch(Match match) throws ElementAlreadyInCollectionException
	{
		System.out.println(trackedMatches.contains(match));
		if(match != null && trackedMatches.contains(match) == false)
		{
			trackedMatches.add(match);
			//saveMatches();
		}
		else
		{
			throw new ElementAlreadyInCollectionException("Specified match: " + match.toString() + " is already in tracked matches repository");
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
	
	public LinkedList<Match> getFavouriteMatches()
	{
		return favMatches;
	}
	
	public boolean removeTrackedMatch(Match m)
	{
		if(trackedMatches.contains(m))
		{
			trackedMatches.remove(m);
			return true;
		}
		else
		{
			return false;			
		}
	}
	
	public boolean removeFavouriteMatch(Match m)
	{
		if(favMatches.contains(m))
		{
			favMatches.remove(m);
			return true;
		}
		else
		{
			return false;			
		}
	}
}


	
