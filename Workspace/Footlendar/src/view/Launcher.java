package view;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import controller.User;
import model.DataBaseMatchLoader;
import model.DataBaseTeamLoader;
import model.DataBaseWriter;
import model.Match;
import model.MatchRepo;
import model.TeamRepo;
import view.gui.GuiNotifier;
import view.gui.MainWindow;
import view.tui.TextInterface;

/**
 * Main program entry point, responsible for receiving and interpreting console arguments
 * Current available console arguments:
 * -t	Launch program in text interface mode
 * @author Filip Mazurek
 *
 */
public class Launcher
{
	private static final String databasePath = "jdbc:sqlserver://DESKTOP-41IQBFQ\\WINCCPLUSMIG2014;databaseName=FootlendarDB;integratedSecurity=true";
	
	public static void main(String[] args)
	{
		TeamRepo.getInstance().setLoader(new DataBaseTeamLoader(databasePath));
		TeamRepo.getInstance().setSaver(new DataBaseWriter(databasePath));
		TeamRepo.getInstance().load();
		//MatchRepo.getInstance().setLoader(new DataBaseMatchLoader(".\\src\\resources\\data.xml"));
		MatchRepo.getInstance().setLoader(new DataBaseMatchLoader(databasePath));
		MatchRepo.getInstance().setSaver(new DataBaseWriter(databasePath));
		MatchRepo.getInstance().load();
		User.getInstance().load();
		// Temp
		GregorianCalendar cal1 = (GregorianCalendar) Calendar.getInstance();
		cal1.add(Calendar.MINUTE, 1);
		GregorianCalendar cal2 = (GregorianCalendar) Calendar.getInstance();
		cal2.add(Calendar.HOUR_OF_DAY, -1);
		MatchRepo.getInstance().add( new Match(666, TeamRepo.getInstance().get("Ukraina U20"), TeamRepo.getInstance().get("W³ochy U20"), cal1, "World Cup U20 Final Stage") );
		MatchRepo.getInstance().add( new Match(667, TeamRepo.getInstance().get("Ecuador U20"), TeamRepo.getInstance().get("Korea Po?udniowa U20"), cal2, "World Cup U20 Final Stage") );
		MatchRepo.getInstance().add( new Match(668, TeamRepo.getInstance().get("Ukraina U20"), TeamRepo.getInstance().get("W³ochy U20"), new GregorianCalendar(2019, 5, 12, 14, 7), "World Cup U20 Final Stage") );
// Temp end
		if(args.length > 0)
		{
			if(args[0].equals("-t"))
			{
				TextInterface.runText(null);				
			}
		}
		else
		{
			EventQueue.invokeLater(new Runnable()
			{

				@Override
				public void run()
				{
					MainWindow mainWindow = new MainWindow();
					
				}
		
			});
		}
		
	}
}
