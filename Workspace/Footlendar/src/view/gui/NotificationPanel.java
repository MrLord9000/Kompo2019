package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.Match;
import controller.Team;
import controller.User;
import model.MatchRepo;
import net.miginfocom.swing.MigLayout;

public class NotificationPanel extends JScrollPane
{
	private LinkedList<MatchPanel> matchPanels;
	
	private JPanel viewPanel; 
	
	public NotificationPanel()
	{
		matchPanels = new LinkedList<MatchPanel>();
		
		this.setViewportBorder(null);
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblNotifications = new JLabel("Tracked Matches");
		lblNotifications.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		this.setColumnHeaderView(lblNotifications);
		
		// Here's the main scroll bar notification panel!!!
		viewPanel = new JPanel();
		this.setViewportView(viewPanel);
		viewPanel.setLayout(new MigLayout("", "[210px,grow,fill]", "[28px,fill][28px][]"));
		
		//Temp
		//viewPanel.add(new MatchPanel(new Match(10, new Team("Polska U20"), new Team("Niemcy U20"), (GregorianCalendar) GregorianCalendar.getInstance(), "Opis")), "cell 0 " + (matchPanels.size() - 1));
	}
	
	public void update()
	{
		matchPanels.clear();
		viewPanel.removeAll();
		
		for(Match item : User.getInstance().getTrackedMatches())
		{
			MatchPanel tmp = new MatchPanel(item);
			matchPanels.add(tmp);
			viewPanel.add(tmp, "cell 0 " + (matchPanels.size() - 1));
		}
		
		this.repaint();
	}
}
