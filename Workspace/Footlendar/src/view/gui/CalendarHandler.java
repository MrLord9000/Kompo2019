package view.gui;

import java.awt.Component;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Match;
import controller.User;

public class CalendarHandler
{
	private GregorianCalendar currentDate;
	private JPanel parentPanel;
	private JLabel headLabel;
	private CalendarPanel[] dayPanels;

	public CalendarHandler(JPanel parentPanel, JLabel headLabel)
	{
		this.parentPanel = parentPanel;
		this.headLabel = headLabel;
	}
	
	public void nextMonth()
	{
		createMonth(currentDate.get(Calendar.MONTH) + 1, currentDate.get(Calendar.YEAR));
	}
	
	public void prevMonth()
	{
		createMonth(currentDate.get(Calendar.MONTH) - 1, currentDate.get(Calendar.YEAR));
	}
	
	public void createMonth(int month, int year)
	{
		// Reset all the day panels
		parentPanel.removeAll();
		
		if(month > 11) 
		{
			month = 0;
			year++;
		}
		if (month < 0)
		{
			month = 11;
			year--;
		}
		
		// Update the current date
		currentDate = new GregorianCalendar(year, month - 1, 1);
		
		// Update the display name
		Locale locale = Locale.getDefault();
		headLabel.setText(currentDate.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, locale) + " " + currentDate.get(Calendar.YEAR));
		
		int daysInMonth = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		dayPanels = new CalendarPanel[daysInMonth];
		// This condition should return the amount of days in one month
		for(int i = 0; i < daysInMonth; i++)
		{
			//dayPanels[i] = CalendarFactory.createDayPanel(calendarPanel, currentDate);
			dayPanels[i] = new CalendarPanel(parentPanel, currentDate, new LinkedList<Match>());
			currentDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		parentPanel.repaint();
	}
	
	public void updateMatches()
	{
		LinkedList<Match> matches = User.getInstance().getMonthMatches(currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
		for(Match item : matches)
		{
			matches = User.getInstance().getDayMatches(currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
			int day = item.getStartTime().get(Calendar.DAY_OF_MONTH) - 1;
			dayPanels[day].setNewEventsInfo(matches);
//			Component[] eventInfo = dayPanels[day].getComponents();
//			for(Component elem : eventInfo)
//			{
//				if(elem instanceof JLabel)
//				{
//					((JLabel) elem).setText("New unread event.");					
//				}
//			}
		}
	}
}