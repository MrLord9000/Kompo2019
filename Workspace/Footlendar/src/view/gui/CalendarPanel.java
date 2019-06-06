package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

import controller.Match;
import controller.MatchAlreadyInCollectionException;
import controller.User;

public class CalendarPanel extends JPanel
{
	private static final Font mainFont = new Font("Century Gothic", Font.PLAIN, 13);
	
	private static final BevelBorder loweredBorder = new BevelBorder(BevelBorder.LOWERED, null, null, null, null);
	private static final BevelBorder raisedBorder = new BevelBorder(BevelBorder.RAISED, null, null, null, null);

	private static final ImageIcon ballNormal = new ImageIcon(MainWindow.class.getResource("/resources/football_64.png"));
	
	private ListenForMouse mousePopupListener;
	
	private LinkedList<Match> dayEvents;
	
	private JPanel parentPanel;
	private GregorianCalendar panelDate;
	
	private JLabel ballPicture;
	private JLabel lblNewEvents;
	
	private JPopupMenu popupMenu;
	private DefaultListModel<String> defaultModel;
	private JList<String> eventNamesList;
	private JButton btnAddToTracked;
	
	public CalendarPanel(JPanel calendarPanel, GregorianCalendar calendarDate)
	{
		parentPanel = calendarPanel;
		panelDate = calendarDate;
		dayEvents = new LinkedList<Match>();
		
		this.setBorder(raisedBorder);
		
		// Panel positioning handling ----------------------------------------
			int column = calendarDate.get(Calendar.DAY_OF_WEEK) - 1;
			if(column == 0) column = 7;
			column = column * 2;
			
			int row = calendarDate.get(Calendar.WEEK_OF_MONTH);
			row = (row + 1) * 2;
		// -------------------------------------------------------------------
				
		// Add this calendar panel to the parent panel
		parentPanel.add(this, column + ", " + row +", fill, fill");

		// Set the label day number
		JLabel dayNumber = new JLabel(calendarDate.get(Calendar.DAY_OF_MONTH) + "");
		dayNumber.setFont(mainFont.deriveFont(47f));
		
		this.add(dayNumber);
		
		// Create and prepare the label with ball picture 
		ballPicture = new JLabel("");
		ballPicture.setIcon(ballNormal);
		ballPicture.setHorizontalAlignment(SwingConstants.CENTER);
		ballPicture.setVisible(false);
		this.add(ballPicture);
		// Create and prepare the label for notifications
		lblNewEvents = new JLabel("");
		lblNewEvents.setFont(mainFont.deriveFont(11f));
		lblNewEvents.setForeground(Color.RED);
		this.add(lblNewEvents);
		
		// ###################################################################
		
		// Create and add to this JPanel a popup menu
		popupMenu = new JPopupMenu();
		//addPopup(this, popupMenu);
		
		// Fill the popup menu with another JPanel
		JPanel popupPanel = new JPanel();
		popupMenu.add(popupPanel);
		popupPanel.setLayout(new BorderLayout(0, 0));
		
		// Create new default list model
		defaultModel = new DefaultListModel<String>();

		// Create a new list for displaying match names
		eventNamesList = new JList<String>(defaultModel);
		eventNamesList.setFont(mainFont);
		eventNamesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		eventNamesList.setLayoutOrientation(JList.VERTICAL);
		eventNamesList.setVisibleRowCount(-1);
		popupPanel.add(eventNamesList, BorderLayout.CENTER);
		
		// Create a label for popup menu
		JLabel lblIncomingEvents = new JLabel("Incoming events");
		lblIncomingEvents.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblIncomingEvents.setHorizontalAlignment(SwingConstants.CENTER);
		popupPanel.add(lblIncomingEvents, BorderLayout.NORTH);
		
		// Create a button to add events
		btnAddToTracked = new JButton("Add to tracked");
		btnAddToTracked.addActionListener(new AddToTrackedAction());
		btnAddToTracked.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		popupPanel.add(btnAddToTracked, BorderLayout.SOUTH);
		
		
		GroupLayout gl_DayPanel = new GroupLayout(this);
		gl_DayPanel.setHorizontalGroup(
			gl_DayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DayPanel.createSequentialGroup()
					.addGroup(gl_DayPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_DayPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(dayNumber, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ballPicture, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(gl_DayPanel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblNewEvents, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
							.addGap(0)))
					.addGap(9))
		);
		gl_DayPanel.setVerticalGroup(
			gl_DayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DayPanel.createSequentialGroup()
					.addGroup(gl_DayPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(dayNumber, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(ballPicture, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewEvents, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addGap(23))
		);
		this.setLayout(gl_DayPanel);
	}
	
	private class AddToTrackedAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(arg0.getSource() == btnAddToTracked)
			{
				int[] eventIndices = eventNamesList.getSelectedIndices();
				for(int i : eventIndices)
				{
					try
					{
						User.getInstance().addTrackedMatch(dayEvents.get(i));
						defaultModel.setElementAt("<trk> " + defaultModel.getElementAt(i), i);
						MainWindow.getNotificationPanel().update();
						btnAddToTracked.setText("Match added succesfully!");
						btnAddToTracked.setForeground(Color.GREEN);
					} 
					catch (MatchAlreadyInCollectionException e)
					{
						btnAddToTracked.setText("Match already in collection!");
						btnAddToTracked.setForeground(Color.RED);
					}
					
				}
			}
			
		}
		
	}
	
	public void addEvent(Match match)
	{
		if(dayEvents.size() == 0)
		{
			addPopup(this, popupMenu);
			
			// Set the match notification picture
			ballPicture.setVisible(true);
		}
		
		dayEvents.add(match);
		defaultModel.addElement(match.getHome().getName() + " - " + match.getAway().getName() + " at " + match.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + match.getStartTime().get(Calendar.MINUTE));
		
		// Add the event notification label
		if(dayEvents.size() == 1)
		{
			lblNewEvents.setText("1 event");			
		}
		else
		{
			lblNewEvents.setText(dayEvents.size() + " events");
		}
	}
	
	public void clearEvents()
	{
		dayEvents.clear();
		defaultModel.clear();
		removePopup(this);
		ballPicture.setVisible(false);
		lblNewEvents.setText("");
	}
	
	private void addPopup(final JPanel component, final JPopupMenu popup) 
	{
		mousePopupListener = new ListenForMouse(component, popup);
		component.addMouseListener(mousePopupListener);
	}
	
	private void removePopup(final JPanel component)
	{
		if(mousePopupListener != null)
		{
			component.removeMouseListener(mousePopupListener);
			mousePopupListener = null;
		}
	}
	
	private class ListenForMouse extends MouseAdapter
	{
		private JPanel component;
		private JPopupMenu popup;
		
		public ListenForMouse(JPanel component, JPopupMenu popup)
		{
			this.component = component;
			this.popup = popup;
		}
		
		public void mousePressed(MouseEvent e) 
		{
			if (e.getButton() == MouseEvent.BUTTON1) 
			{
				showMenu(e);
				component.setBorder(loweredBorder);
			}
		}
		public void mouseReleased(MouseEvent e) 
		{
			if (e.getButton() == MouseEvent.BUTTON1) 
			{
				//showMenu(e);
				component.setBorder(raisedBorder);
			}
		}
		private void showMenu(MouseEvent e) 
		{
			popup.show(e.getComponent(), e.getX() , e.getY());
			btnAddToTracked.setText("Add to tracked");
			btnAddToTracked.setForeground(Color.BLACK);
		}
		@Override
		public void mouseEntered(MouseEvent e) 
		{
			component.setBackground(Color.decode("#cfe0e8"));
		}
		@Override
		public void mouseExited(MouseEvent e) 
		{
			component.setBackground(Color.decode("#f0efef"));
		}
	}
}
