package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import controller.ElementAlreadyInCollectionException;
import controller.User;
import model.Match;
import model.MatchRepo;

/**
 * Class responsible for handling calendar panel popups.
 * It covers basic "Add to tracked" functionality as well as "Add to favourite" functionality
 * @author Lord9000
 *
 */
public class CalendarPopupMenu extends JPopupMenu
{
	private DefaultListModel<String> defaultModelIncoming, defaultModelPast;
	private JList<String> listIncoming, listPast;
	private JButton btnAddToTracked, btnAddToFavorite;
	private LinkedList<Match> incomingMatches, pastMatches;
	
	public CalendarPopupMenu(GregorianCalendar panelDate, LinkedList<Match> dayEvents)
	{
		defaultModelIncoming = new DefaultListModel<String>();
		defaultModelPast = new DefaultListModel<String>();
		incomingMatches = new LinkedList<Match>();
		pastMatches = new LinkedList<Match>();
		this.setLabel(panelDate.get(Calendar.DAY_OF_MONTH) + panelDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
		
		JPanel groupingPane = new JPanel();
		this.add(groupingPane);
		groupingPane.setLayout(new BorderLayout(0, 0));
		
		if(dayEvents.size() > 0)
		{
			update(dayEvents);
			
			if(defaultModelIncoming.size() > 0)
			{
				// --- Incoming matches panel handling ------------------------------------
				JPanel paneIncomingMatches = new JPanel();
				groupingPane.add(paneIncomingMatches, BorderLayout.NORTH);
				paneIncomingMatches.setLayout(new BorderLayout(0, 0));
				
				JLabel lblIncomingMatches = new JLabel("Incoming Matches");
				lblIncomingMatches.setFont(new Font("Century Gothic", Font.PLAIN, 13));
				lblIncomingMatches.setHorizontalAlignment(SwingConstants.CENTER);
				paneIncomingMatches.add(lblIncomingMatches, BorderLayout.NORTH);
				
				listIncoming = new JList<String>(defaultModelIncoming);
				listIncoming.setFont(CalendarPanel.mainFont);
				listIncoming.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				listIncoming.setLayoutOrientation(JList.VERTICAL);
				listIncoming.setVisibleRowCount(-1);
				
				paneIncomingMatches.add(listIncoming, BorderLayout.CENTER);
				
				btnAddToTracked = new JButton("Add to tracked");
				btnAddToTracked.addActionListener(new AddToTrackedAction());
				btnAddToTracked.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				paneIncomingMatches.add(btnAddToTracked, BorderLayout.SOUTH);
			}
			if(defaultModelPast.size() > 0)
			{
				// --- Past matches panel handling ------------------------------------
				JPanel panePastEvents = new JPanel();
				groupingPane.add(panePastEvents, BorderLayout.SOUTH);
				panePastEvents.setLayout(new BorderLayout(0, 0));
				
				JLabel lblPastEvents = new JLabel("Past Events");
				lblPastEvents.setFont(new Font("Century Gothic", Font.PLAIN, 13));
				lblPastEvents.setHorizontalAlignment(SwingConstants.CENTER);
				panePastEvents.add(lblPastEvents, BorderLayout.NORTH);
				
				listPast = new JList<String>(defaultModelPast);
				listPast.setFont(CalendarPanel.mainFont);
				listPast.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				listPast.setLayoutOrientation(JList.VERTICAL);
				listPast.setVisibleRowCount(-1);
				panePastEvents.add(listPast, BorderLayout.CENTER);
				
				btnAddToFavorite = new JButton("Add to favorite");
				btnAddToFavorite.addActionListener(new AddToTrackedAction());
				btnAddToFavorite.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				panePastEvents.add(btnAddToFavorite, BorderLayout.SOUTH);
			}
		}
		else
		{
			JLabel lblNoMatches = new JLabel("No matches available.");
			lblNoMatches.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			lblNoMatches.setForeground(Color.RED);
			lblNoMatches.setHorizontalAlignment(SwingConstants.CENTER);
			groupingPane.add(lblNoMatches, BorderLayout.NORTH);
		}
		
	}
	
	public void resetButtons()
	{
		if(btnAddToFavorite != null)
		{
			btnAddToFavorite.setText("Add to favorite");
			btnAddToFavorite.setForeground(Color.BLACK);
		}
		if(btnAddToTracked != null)
		{
			btnAddToTracked.setText("Add to tracked");
			btnAddToTracked.setForeground(Color.BLACK);
		}
	}
	
	public void update(LinkedList<Match> dayEvents)
	{
		defaultModelIncoming.clear();
		defaultModelPast.clear();
		for(Match item : dayEvents)
		{
			if(GregorianCalendar.getInstance().before(item.getStartTime()))
			{
				if(User.getInstance().getTrackedMatches().contains(item))
				{
					defaultModelIncoming.addElement("<tracked> " + item.getHome().getName() + " - " + item.getAway().getName() + " at " + item.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + item.getStartTime().get(Calendar.MINUTE));
				}
				else
				{
					defaultModelIncoming.addElement(item.getHome().getName() + " - " + item.getAway().getName() + " at " + item.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + item.getStartTime().get(Calendar.MINUTE));
				}
				incomingMatches.add(item);
			}
			else
			{
				if(User.getInstance().getFavouriteMatches().contains(item))
				{
					defaultModelPast.addElement("<favorite> " + item.getHome().getName() + " - " + item.getAway().getName() + " at " + item.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + item.getStartTime().get(Calendar.MINUTE));
				}
				else
				{
					defaultModelPast.addElement(item.getHome().getName() + " - " + item.getAway().getName() + " at " + item.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + item.getStartTime().get(Calendar.MINUTE));
				}
				pastMatches.add(item);
			}
		}
	}
	
	private class AddToTrackedAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(arg0.getSource() == btnAddToTracked)
			{
				int[] eventIndices = listIncoming.getSelectedIndices();
				for(int i : eventIndices)
				{
					try
					{
						User.getInstance().addTrackedMatch(incomingMatches.get(i));
						defaultModelIncoming.setElementAt("<tracked> " + defaultModelIncoming.getElementAt(i), i);
						btnAddToTracked.setText("Match added succesfully!");
						btnAddToTracked.setForeground(Color.decode("#006E51"));
					} 
					catch (ElementAlreadyInCollectionException e)
					{
						btnAddToTracked.setText("Match already in collection!");
						btnAddToTracked.setForeground(Color.RED);
					}
					finally
					{
						MainWindow.getNotificationPanel().update();
					}		
				}
			}
			else if(arg0.getSource() == btnAddToFavorite)
			{
				int[] eventIndices = listPast.getSelectedIndices();
				for(int i : eventIndices)
				{
					try
					{
						User.getInstance().addFavouriteMatch(pastMatches.get(i));
						defaultModelPast.setElementAt("<favorite> " + defaultModelPast.getElementAt(i), i);
						btnAddToFavorite.setText("Match added succesfully!");
						btnAddToFavorite.setForeground(Color.decode("#006E51"));
					} 
					catch (ElementAlreadyInCollectionException e)
					{
						btnAddToFavorite.setText("Match already in collection!");
						btnAddToFavorite.setForeground(Color.RED);
					}
					finally
					{
						MainWindow.getNotificationPanel().update();
					}		
				}
			}
			MainWindow.getMainWindow().revalidate();
		}
	}
}
