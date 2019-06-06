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
		
		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		this.setColumnHeaderView(lblNotifications);
		
		// Here's the main scroll bar notification panel!!!
		viewPanel = new JPanel();
		this.setViewportView(viewPanel);
		viewPanel.setLayout(new MigLayout("", "[210px,grow,fill]", "[28px,fill][28px][]"));
				
		//panel_1.add(new MatchPanel(new Match(4, new Team("Japonia U20"), new Team("Korea Po?udniowa U20"), new GregorianCalendar(2019, Calendar.JUNE, 22), "to jest o434pis")), "cell 0 0");
		//MatchPanel matchPanel = new MatchPanel(new Match(4, new Team("Japonia U20"), new Team("Korea Po?udniowa U20"), new GregorianCalendar(2019, Calendar.JUNE, 22), "to jest o434pis"));
		//panel_1.add(matchPanel, "cell 0 1");
	}
	
	public void update()
	{
		matchPanels.clear();
		
		for(Match item : User.getInstance().getTrackedMatches())
		{
			MatchPanel tmp = new MatchPanel(item);
			matchPanels.add(tmp);
			viewPanel.add(tmp, "cell 0 " + (matchPanels.size() - 1));
		}
		
		this.repaint();
	}
}
