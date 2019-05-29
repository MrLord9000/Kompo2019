package view.gui;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.LayoutManager;

public final class DayPanel {

	public void createDay(int day, int posX, int posY)
	{
		JPanel DayPanel = new JPanel();
		DayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//CalendarPanel.add(DayPanel, "2, 4, fill, fill");
		
		JLabel dayNumber = new JLabel("1");
		dayNumber.setFont(new Font("Century Gothic", Font.PLAIN, 47));
		
		JLabel ballPic = new JLabel("");
		ballPic.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/football_64.png")));
		ballPic.setHorizontalAlignment(SwingConstants.CENTER);
		ballPic.setFont(new Font("Century Gothic", Font.PLAIN, 47));
		
		JLabel lblNewEvents = new JLabel("2 new events");
		GroupLayout gl_DayPanel = new GroupLayout(DayPanel);
		gl_DayPanel.setHorizontalGroup(
			gl_DayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DayPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_DayPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewEvents, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
						.addGroup(gl_DayPanel.createSequentialGroup()
							.addComponent(dayNumber, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ballPic, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_DayPanel.setVerticalGroup(
			gl_DayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DayPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_DayPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ballPic, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
						.addComponent(dayNumber, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewEvents, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
					.addContainerGap())
		);
		DayPanel.setLayout(gl_DayPanel);
	}
}