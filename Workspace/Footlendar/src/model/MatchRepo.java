package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import controller.Match;
import controller.Team;

public class MatchRepo implements IRepository<Match, Long> {

	private LinkedList<Match> matches;
	private static MatchRepo instance = new MatchRepo();
	
	public static MatchRepo getInstance()
	{
		return instance;
	}
	
	public MatchRepo() {
		this.matches = new LinkedList<>();
		
		// Temp
		matches.add(new Match(0, new Team("Francja U20"), new Team("USA U20"), (GregorianCalendar) GregorianCalendar.getInstance(), "to 2323est opis"));
		matches.add(new Match(1, new Team("Włochy U20"), new Team("Polska U20"), (GregorianCalendar) GregorianCalendar.getInstance(), "to j11est opis"));
		matches.add(new Match(2, new Team("Argentyna U20"), new Team("Mali U20"), new GregorianCalendar(2019, Calendar.JUNE, 22), "to jest o434pis"));
		// Temp end
	}

	public void load() {
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-41IQBFQ\\WINCCPLUSMIG2014;databaseName=FootlendarDB;integratedSecurity=true");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM Matches");
			
			while(rs.next())
			{
				
				GregorianCalendar cal = convertSQLDateToGregorianCalendar(rs.getDate("startTime"), rs.getTime("startTime"));
				Match m = new Match(rs.getInt("id"), TeamRepo.getInstance().get(rs.getString("home")), TeamRepo.getInstance().get(rs.getString("away")), cal, rs.getString("descr"));
				//System.out.println(cal.get(Calendar.HOUR));
				//System.out.println(TeamRepo.getInstance().get(rs.getString("home")));
				matches.add(m);
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
	}
		
		
	private GregorianCalendar convertSQLDateToGregorianCalendar(java.sql.Date date, java.sql.Time time)
	{
		//System.out.println(date.getHours());
		//System.out.println(date.getMinutes());
		GregorianCalendar c = new GregorianCalendar(date.getYear() + 1900, date.getMonth(), date.getDate(), time.getHours(), time.getMinutes());
		return c;
	}

}
