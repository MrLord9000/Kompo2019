package view.gui;

import java.awt.Font;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

public final class CalendarFactory
{
	public static JPanel createDayPanel(JPanel calendarPanel, Calendar calendarDate)
	{
		
		JPanel DayPanel = new JPanel();
		DayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		// Panel positioning handling
		int column = calendarDate.get(Calendar.DAY_OF_WEEK) - 1;
		if(column == 0) column = 7;
		column = column * 2;
		
		int row = calendarDate.get(Calendar.WEEK_OF_MONTH);
		row = (row + 1) * 2;
		
		calendarPanel.add(DayPanel, column + ", " + row + ", fill, fill");
		
		JLabel dayNumber = new JLabel(calendarDate.get(Calendar.DAY_OF_MONTH) + "");
		dayNumber.setFont(new Font("Century Gothic", Font.PLAIN, 47));
		
		JLabel ballPic = new JLabel("");
		ballPic.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/football_64.png")));
		ballPic.setHorizontalAlignment(SwingConstants.CENTER);
		ballPic.setFont(new Font("Century Gothic", Font.PLAIN, 47));
		ballPic.setVisible(false);
		
		JLabel lblNewEvents = new JLabel("");
		GroupLayout gl_DayPanel = new GroupLayout(DayPanel);
		gl_DayPanel.setHorizontalGroup(
			gl_DayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DayPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_DayPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewEvents, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addGroup(gl_DayPanel.createSequentialGroup()
							.addComponent(dayNumber, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(ballPic, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_DayPanel.setVerticalGroup(
			gl_DayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DayPanel.createSequentialGroup()
					.addGroup(gl_DayPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(dayNumber, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(ballPic, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewEvents, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		DayPanel.setLayout(gl_DayPanel);
		return DayPanel;
	}
}
