package view;

import java.awt.EventQueue;

import controller.User;
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
	public static void main(String[] args)
	{
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
