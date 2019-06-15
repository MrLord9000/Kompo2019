package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import controller.FlashScoreHtmlScoreReader;
import controller.ILoadable;
import controller.ISaveable;
import controller.Match;
import controller.Score;
import controller.Team;
import controller.TestHtmlScoreReader;
import controller.User;

/**
 * Implemments repository for all matches.
 * This class also holds references to default file loader and saver.
 * @author Adrian Zieliñski
 *
 */
public class MatchRepo implements IRepository<Match, Long> {

	private LinkedList<Match> matches;
	private static MatchRepo instance = new MatchRepo();
	private ILoadable<Match> loader;
	private ISaveable<Match> saver;
	
	/**
	 * Static method for getting singleton User instance
	 * @return singleton User instance
	 */
	public static MatchRepo getInstance()
	{
		return instance;
	}
	
	/*
	 * Class constructor. Initializes collections with empty LinkedLists
	 */
	public MatchRepo() 
	{
		this.matches = new LinkedList<>();
	}
	
	/**
	 * Sets default match repo loader
	 * @param l ILoadable to be set
	 */
	public void setLoader(ILoadable<Match> l)
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

	public void load() {
		
		matches = loader.load();
		
	}

	@Override
	public void save() {
		try
		{
			saver.saveCollection(matches);
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Match get(Long id) {
		for(Match m: matches)
		{
			if(id == m.getId()) return m;
		}
		return null;
	}

	@Override
	public List<Match> getAll() {
		return matches;
	}

	@Override
	public void add(Match item) {
		matches.add(item);
		if(saver != null)
		{
			try
			{
				saver.save(item);
			} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
	}
	
	public void removeBefore(GregorianCalendar before)
	{
		for(Iterator<Match> it = matches.iterator(); it.hasNext();)
		{
			Match m = it.next();
			if(m.getStartTime().before(before))
			{
				User.getInstance().removeTrackedMatch(m);
				it.remove();
			}
		}
	}
		
	private GregorianCalendar convertSQLDateToGregorianCalendar(java.sql.Date date, java.sql.Time time)
	{
		GregorianCalendar c = new GregorianCalendar(date.getYear() + 1900, date.getMonth(), date.getDate(), time.getHours(), time.getMinutes());
		return c;
	}

	
	public void updateScore(Match m)
	{
		if(saver != null)
		{
			try
			{
				saver.update(m);
			} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}