package view.tui;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import controller.ElementAlreadyInCollectionException;
import controller.Match;
import controller.User;
import model.DataBaseMatchLoader;
import model.DataBaseTeamLoader;
import model.MatchRepo;
import model.TeamRepo;

public class TextInterface
{
	
	private static void showMenu()
	{
		System.out.println("1. Show your events");
		System.out.println("2. Show your favourites teams");
		System.out.println("3. Show all events");
		System.out.println("4. Show all teams");
		System.out.println("5. Add events to track");
		System.out.println("6. Add team to favourites");
		System.out.println("7. Remove events older than...");
		System.out.println("8. Exit");
	}
	
	private static void returnToMenu()
	{
		System.out.println("Press ENTER to return to menu");
		Scanner in = new Scanner(System.in);
		in.nextLine();
		in.close();
		//System.in.read();
		showMenu();
	}

	public static void runText(String[] args)
	{
		TeamRepo.getInstance().setLoader(new DataBaseTeamLoader("jdbc:sqlserver://DESKTOP-41IQBFQ\\WINCCPLUSMIG2014;databaseName=FootlendarDB;integratedSecurity=true"));
		MatchRepo.getInstance().setLoader(new DataBaseMatchLoader("jdbc:sqlserver://DESKTOP-41IQBFQ\\WINCCPLUSMIG2014;databaseName=FootlendarDB;integratedSecurity=true"));
		TeamRepo.getInstance().load();
		MatchRepo.getInstance().load();
		User.getInstance().load();
		User.getInstance().setNotifier(new ConsoleNotifier());
		GregorianCalendar cal1 = (GregorianCalendar) Calendar.getInstance();
		cal1.add(Calendar.HOUR_OF_DAY, 1);
		GregorianCalendar cal2 = (GregorianCalendar) Calendar.getInstance();
		cal2.add(Calendar.HOUR_OF_DAY, -1);
		MatchRepo.getInstance().add( new Match(666, TeamRepo.getInstance().get("Ukraina U20"), TeamRepo.getInstance().get("W³ochy U20"), cal1, "World Cup U20 Final Stage") );
		MatchRepo.getInstance().add( new Match(667, TeamRepo.getInstance().get("Ecuador U20"), TeamRepo.getInstance().get("Korea Po?udniowa U20"), cal2, "World Cup U20 Final Stage") );
		MatchRepo.getInstance().add( new Match(668, TeamRepo.getInstance().get("Ukraina U20"), TeamRepo.getInstance().get("W³ochy U20"), new GregorianCalendar(2019, 5, 12, 14, 7), "World Cup U20 Final Stage") );
		boolean exit = false;
		while(exit == false)
		{
			showMenu();
			int w = 0;
//			String buff = new String();
//			Scanner in = new Scanner(System.in);

			String buff = new String();
			Scanner in = new Scanner(System.in);
			buff = in.nextLine();
			//in.close();
			w = Integer.parseInt(buff);
			
			switch (w)
			{
			case 1:
				System.out.println(User.getInstance().getTrackedMatches());
				//returnToMenu();
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;
			case 2:
				System.out.println(User.getInstance().getFavTeams());
				//returnToMenu();
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;
			case 3:
				System.out.println(MatchRepo.getInstance().getAll());
				//returnToMenu();
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;
			case 4:
				System.out.println(TeamRepo.getInstance().getAll());
				//returnToMenu();
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;
			case 5:
				System.out.println("Type match id to track:");
				buff = in.nextLine();
				long id = Long.parseLong(buff);
				System.out.println(id);
				try
				{
					User.getInstance().addTrackedMatch(MatchRepo.getInstance().get(id));
				} 
				catch (ElementAlreadyInCollectionException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//returnToMenu();
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;
			case 6:
				System.out.println("Type team name to add:");
				buff = in.nextLine();
				try
				{
					User.getInstance().addFavouriteTeam(TeamRepo.getInstance().get(buff));
				} 
				catch (ElementAlreadyInCollectionException e)
				{
					e.printStackTrace();
				}
				//returnToMenu();
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;
			case 7:
				System.out.println("Type number of days:");
				buff = in.nextLine();
				int days = Integer.parseInt(buff);
				GregorianCalendar before = new GregorianCalendar();
				before.add(Calendar.DAY_OF_MONTH, -days);
				MatchRepo.getInstance().removeBefore(before);
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;	
			case 8:
				exit = true;
				User.getInstance().save();
				in.close();
				break;
			default:
				break;
			}

		}
		
		
		
	}

}
