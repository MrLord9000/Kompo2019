package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class GoogleCalendarExporter
{
	public static void export(List<Match> exportData, String filePath) throws IOException
	{
		FileWriter csvWriter = new FileWriter(filePath);
		
		// --------------------------------------------------
		// Google Calendar output format
		// Subject - "home - away"
		// Start Date - startDate - DD/MM/YYY
		// Start Time - startDate - 10:00 AM
		// Description - description
		// --------------------------------------------------
		
		// Writing header to the csv file
		csvWriter.append("Subject, Start Date, Start Time, Description\n");
		SimpleDateFormat startDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat startTimeFormat = new SimpleDateFormat("HH:mm");
		
		for(Match item : exportData)
		{
			csvWriter.append(item.getHome().getName() + " - " + item.getAway().getName() + ", ");
			csvWriter.append(startDateFormat.format(item.getStartTime().getTime()) + ", ");
			csvWriter.append(startTimeFormat.format(item.getStartTime().getTime()) + ", ");
			csvWriter.append(item.getDescription() + ",\n");
		}
		
		csvWriter.close();
	}
}
