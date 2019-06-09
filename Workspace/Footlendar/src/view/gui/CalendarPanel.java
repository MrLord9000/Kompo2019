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
import controller.ElementAlreadyInCollectionException;
import controller.User;

public class CalendarPanel extends JPanel
{
	static final Font mainFont = new Font("Century Gothic", Font.PLAIN, 13);
	
	private static final BevelBorder loweredBorder = new BevelBorder(BevelBorder.LOWERED, null, null, null, null);
	private static final BevelBorder raisedBorder = new BevelBorder(BevelBorder.RAISED, null, null, null, null);

	private static final ImageIcon ballNormal = new ImageIcon(MainWindow.class.getResource("/resources/football_64.png"));
	
	private Color defaultColor;
	
	private ListenForMouse mousePopupListener;
	
	private LinkedList<Match> dayEvents;
	
	private JPanel parentPanel;
	private GregorianCalendar panelDate;
	
	private JLabel ballPicture;
	private JLabel lblNewEvents;
	
	private CalendarPopupMenu calendarPopupMenu;
	
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
				
		if(calendarDate.before(Calendar.getInstance()))
		{
			defaultColor = Color.decode("#B4B7BA");
		}
		else 
		{
			defaultColor = Color.decode("#f0efef");
		}
		
		this.setBackground(defaultColor);
			
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
	
	public void addEvent(Match match)
	{
		int initialSize = dayEvents.size();
		dayEvents.add(match);

		if(initialSize == 0)
		{
			addPopup(this);	
			// Set the match notification picture
			ballPicture.setVisible(true);
		}
		
//		defaultModel.addElement(match.getHome().getName() + " - " + match.getAway().getName() + " at " + match.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + match.getStartTime().get(Calendar.MINUTE));
		
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
		removePopup(this);
		ballPicture.setVisible(false);
		lblNewEvents.setText("");
	}
	
	private void addPopup(final JPanel component) 
	{
		calendarPopupMenu = new CalendarPopupMenu(panelDate, dayEvents);
		mousePopupListener = new ListenForMouse(component, calendarPopupMenu);
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
		private CalendarPopupMenu popup;
		
		public ListenForMouse(JPanel component, CalendarPopupMenu popup)
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
			popup.resetButtons();
		}
		@Override
		public void mouseEntered(MouseEvent e) 
		{
			component.setBackground(Color.decode("#cfe0e8"));
		}
		@Override
		public void mouseExited(MouseEvent e) 
		{
			component.setBackground(defaultColor);
		}
	}
}
