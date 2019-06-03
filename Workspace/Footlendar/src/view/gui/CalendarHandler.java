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
	private Calendar currentDate;
	private JPanel calendarPanel;
	private JLabel headLabel;
	private JPanel[] dayPanels;

	public CalendarHandler(JPanel calendarPanel, JLabel headLabel)
	{
		this.calendarPanel = calendarPanel;
		this.headLabel = headLabel;
	}
	
	public void nextMonth()
	{
		createMonth(currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
	}
	
	public void prevMonth()
	{
		createMonth(currentDate.get(Calendar.MONTH) - 2, currentDate.get(Calendar.YEAR));
		//createMonth(0, currentDate.get(Calendar.YEAR));
	}
	
	public void createMonth(int month, int year)
	{
		calendarPanel.removeAll();
		
		if(month > 11) 
		{
			month = 0;
			year++;
		}
		else if (month < 0)
		{
			month = 11;
			year--;
		}
		
		currentDate = new GregorianCalendar(year, month, 1);
		Locale locale = Locale.getDefault();
		headLabel.setText(currentDate.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, locale) + " " + currentDate.get(Calendar.YEAR));
		
		int daysInMonth = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		dayPanels = new JPanel[daysInMonth];
		// This condition should return the amount of days in one month
		for(int i = 0; i < daysInMonth; i++)
		{
			dayPanels[i] = CalendarFactory.createDayPanel(calendarPanel, currentDate);
			currentDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		calendarPanel.repaint();
	}
	
	public void updateMatches()
	{
		LinkedList<Match> matches = User.getInstance().getMonthMatches(currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
		for(Match item : matches)
		{
			int day = item.getStartTime().get(Calendar.DAY_OF_MONTH) - 1;
			JLabel eventInfo = (JLabel)dayPanels[day].getComponent(0);
			eventInfo.setText("New unread event.");
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
