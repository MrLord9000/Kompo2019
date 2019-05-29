import java.sql.Statement;

import model.MatchRepo;
import model.TeamRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main 
{

	
	public static void print(Statement stat)
	{
		ResultSet rs =
		null;
		try
		{
			rs = stat.executeQuery("SELECT * FROM Teams");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			while(rs.next())
			{
				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
//		try 
//		{
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-41IQBFQ\\WINCCPLUSMIG2014;databaseName=FootlendarDB;integratedSecurity=true");
//			System.out.println("xD");
//			Statement stat = con.createStatement();
//			ResultSet rs = stat.executeQuery("SELECT * FROM Teams");
//			//print(stat);
//			//rs = stat.executeQuery("INSERT INTO Teams VALUES ('Argentina')");
//			print(stat);
//			
//			
//		}
//		catch(SQLException e)
//		{
//			System.out.println(e.getMessage());
//		} 
//		catch (InstantiationException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		TeamRepo rep2 = TeamRepo.getInstance();
		rep2.load();
		System.out.println(rep2.getAll());
		
		MatchRepo rep = MatchRepo.getInstance();
		rep.load();
		System.out.println(rep.getAll());

		
		
	}
	
}
