package view.gui;

import java.awt.Component;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.User;
import model.Match;

/**
 * Class responsible for controlling the calendar panels, updating matches from MatchRepo and switching current month view.
 * @author Filip Mazurek
 *
 */
public class CalendarHandler
{
	private static GregorianCalendar currentDate;
	private static CalendarPanel[] dayPanels;
	private static JPanel parentPanel;
	private static JLabel headLabel;

	/**
	 * Class sonstructor. Creates calendar handler
	 * @param parentPanel Panel the handler should be assigned to
	 * @param headLabel   Head label for displaying current date
	 */
	public CalendarHandler(JPanel parentPanel, JLabel headLabel)
	{
		this.parentPanel = parentPanel;
		this.headLabel = headLabel;
	}
	
	/**
	 * Method for switching to next month
	 */
	public void nextMonth()
	{
		createMonth(currentDate.get(Calendar.MONTH) + 1, currentDate.get(Calendar.YEAR));
	}
	
	/**
	 * Method for switching to previous month
	 */
	public void prevMonth()
	{
		createMonth(currentDate.get(Calendar.MONTH) - 1, currentDate.get(Calendar.YEAR));
	}
	
	/**
	 * Creates new month calendar panel, for specified month and year
	 * @param month Specified year
	 * @param year  Specified month
	 */
	public static void createMonth(int month, int year)
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
		headLabel.setText(currentDate.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.US) + " " + currentDate.get(Calendar.YEAR));
		
		int daysInMonth = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		dayPanels = new CalendarPanel[daysInMonth];
		// This condition should return the amount of days in one month
		for(int i = 0; i < daysInMonth; i++)
		{
			//dayPanels[i] = CalendarFactory.createDayPanel(calendarPanel, currentDate);
			dayPanels[i] = new CalendarPanel(parentPanel, currentDate);
			currentDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		parentPanel.repaint();
	}
	
	/**
	 * Method useful for updating match data displayed on the calendar
	 */
	public static void updateMatches()
	{
		// Get only the events from current month
		LinkedList<Match> matches = User.getInstance().getMonthMatches(currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
		System.out.println("Current month matches length: " + matches.size());
		
		for(CalendarPanel item : dayPanels)
		{
			item.clearEvents();
		}
		
		// find a day with matches and update it
		for(Match item : matches)
		{
			//matches = User.getInstance().getDayMatches(currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR));
			int day = item.getStartTime().get(Calendar.DAY_OF_MONTH) - 1;
			dayPanels[day].addEvent(item);
		}
		
		for(CalendarPanel item : dayPanels)
		{
			item.updatePopup();
		}
	}
}
