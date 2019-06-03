package view.tui;

import java.util.Scanner;

import controller.User;
import model.MatchRepo;
import model.TeamRepo;

public class Main
{
	
	private static void showMenu()
	{
		System.out.println("1. Show your events");
		System.out.println("2. Show your favourites teams");
		System.out.println("3. Show all events");
		System.out.println("4. Show all teams");
		System.out.println("5. Add events to track");
		System.out.println("6. Add team to favourites");
		System.out.println("7. Exit");
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

	public static void main(String[] args)
	{
		TeamRepo.getInstance().load();
		MatchRepo.getInstance().load();
		User.getInstance().load();
		User.getInstance().setNotifier(new ConsoleNotifier());
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
				User.getInstance().addTrackedMatch(MatchRepo.getInstance().get(id));
				//returnToMenu();
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;
			case 6:
				System.out.println("Type team name to add:");
				buff = in.nextLine();
				User.getInstance().addFavouriteTeam(TeamRepo.getInstance().get(buff));
				//returnToMenu();
				System.out.println("Press ENTER to return to menu");
				in.next();
				break;
				
			case 7:
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
