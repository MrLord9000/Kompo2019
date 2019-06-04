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
import controller.User;

public class CalendarPanel extends JPanel
{
	private static final Font mainFont = new Font("Century Gothic", Font.PLAIN, 13);
	
	private static final BevelBorder loweredBorder = new BevelBorder(BevelBorder.LOWERED, null, null, null, null);
	private static final BevelBorder raisedBorder = new BevelBorder(BevelBorder.RAISED, null, null, null, null);

	private static final String ballNormal = "/resources/football_64.png";
	
	private LinkedList<Match> dayEvents;
	
	private JPanel parentPanel;
	private GregorianCalendar panelDate;
	
	private JLabel ballPicture;
	private JLabel lblNewEvents;
	
	private JPopupMenu popupMenu;
	private DefaultListModel<String> defaultModel;
	private JList<String> eventNamesList;
	
	public CalendarPanel(JPanel calendarPanel, GregorianCalendar calendarDate, final LinkedList<Match> dayEvents)
	{
		parentPanel = calendarPanel;
		panelDate = calendarDate;
		
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
		
		this.dayEvents = dayEvents;
		
		ballPicture = new JLabel("");
		this.add(ballPicture);
		lblNewEvents = new JLabel("");
		this.add(lblNewEvents);
		
		// Set the match notification picture
//		ballPicture.setIcon(new ImageIcon(MainWindow.class.getResource(ballNormal)));
//		ballPicture.setHorizontalAlignment(SwingConstants.CENTER);

		// Add the event notification label
//		lblNewEvents.setText(dayEvents.size() + " events");
//		lblNewEvents.setFont(mainFont.deriveFont(11f));
//		lblNewEvents.setForeground(Color.RED);
		
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
			
			for(Match item : dayEvents)
			{
				defaultModel.addElement(item.getHome().getName() + " - " + item.getAway().getName() + " at " + item.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + item.getStartTime().get(Calendar.MINUTE));
			}

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
			final JButton btnAddToTracked = new JButton("Add to tracked");
			btnAddToTracked.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					if(arg0.getSource() == btnAddToTracked)
					{
						int[] eventIndices = eventNamesList.getSelectedIndices();
						for(int i : eventIndices)
						{
							defaultModel.setElementAt("<trk> " + defaultModel.getElementAt(i), i);
							User.getInstance().addTrackedMatch(dayEvents.get(i));
						}
					}
					
				}
				
			});
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
	
	public void setNewEventsInfo(LinkedList<Match> matches)
	{
		addPopup(this, popupMenu);
		dayEvents = matches;
		for(Match item : dayEvents)
		{
			defaultModel.addElement(item.getHome().getName() + " - " + item.getAway().getName() + " at " + item.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + item.getStartTime().get(Calendar.MINUTE));
		}
		eventNamesList = new JList<String>(defaultModel);
		
		// Set the match notification picture
		ballPicture.setIcon(new ImageIcon(MainWindow.class.getResource(ballNormal)));
		ballPicture.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Add the event notification label
		lblNewEvents.setText(dayEvents.size() + " events");
		lblNewEvents.setFont(mainFont.deriveFont(11f));
		lblNewEvents.setForeground(Color.RED);
	}
	
	private static void addPopup(final JPanel component, final JPopupMenu popup) 
	{
		component.addMouseListener(new MouseAdapter() 
		{
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
		});
	}
}
