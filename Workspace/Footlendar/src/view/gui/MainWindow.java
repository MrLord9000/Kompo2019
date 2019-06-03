package view.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import model.MatchRepo;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame
{
	private CalendarHandler calendarHandler;
	private JPanel contentPane;
	private JButton prevMonthBtn;
	private JButton nextMonthBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{				
				
				try
				{
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			try
			{
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} 
			catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// Window Title
		setTitle("Footlendar v.0.06");
		// Window default font
		setFont(new Font("Century Gothic", Font.PLAIN, 14));
		// Default close operation - set to DISPOSE_ON_CLOSE for best compatibility
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// ------------------------------------------------------------------------
		// --- Set window bounds - keep in mind this should be resized ------------
		
			setBounds(100, 100, 1401, 795);
			
		// ------------------------------------------------------------------------
		
		// Creating main content pane
		contentPane = new JPanel();
		// Setting content pane border to empty
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Setting the current content pane
		setContentPane(contentPane);
		
		// Left side Menu Panel constructor
		JPanel MenuPanel = new JPanel();
		
		// Left side Calendar Panel constructor
		JPanel CalendarPanel = new JPanel();
		CalendarPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		
		// Left side Notification Pane constructor
		JScrollPane NotificationScrollPane = new JScrollPane();
		NotificationScrollPane.setViewportBorder(null);
		NotificationScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		NotificationScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel daysOfWeekPanel = new JPanel();
		
		// Main frame group layout configuration ----------------------------------------------------------------------------------
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(NotificationScrollPane, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(daysOfWeekPanel, GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
						.addComponent(CalendarPanel, GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
						.addComponent(MenuPanel, GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(NotificationScrollPane, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(MenuPanel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(daysOfWeekPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(CalendarPanel, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)))
					.addGap(3))
		);
		daysOfWeekPanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		JLabel label = new JLabel("Monday");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		daysOfWeekPanel.add(label);
		
		JLabel label_2 = new JLabel("Tuesday");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		daysOfWeekPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Wednesday");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		daysOfWeekPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Thursday");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		daysOfWeekPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Friday");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		daysOfWeekPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Saturday");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		daysOfWeekPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Sunday");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		daysOfWeekPanel.add(label_7);
		// ------------------------------------------------------------------------------------------------------------------------
		
		// Calendar panel form layout configuration -------------------------------------------------------------------------------
		CalendarPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.GLUE_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.GLUE_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.GLUE_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.GLUE_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.GLUE_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.GLUE_ROWSPEC,}));
		// ------------------------------------------------------------------------------------------------------------------------
		
		
		
		// ------------------------------------------------------------------------------------------------------------------------
		// --- Here DayPanel factory should be implemented! -----------------------------------------------------------------------
//			JPanel DayPanel = new JPanel();
//			DayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//			CalendarPanel.add(DayPanel, "2, 4, fill, fill");
//			
//			JLabel dayNumber = new JLabel("99");
//			dayNumber.setFont(new Font("Century Gothic", Font.PLAIN, 47));
//			
//			JLabel ballPic = new JLabel("");
//			ballPic.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/football_64.png")));
//			ballPic.setHorizontalAlignment(SwingConstants.CENTER);
//			ballPic.setFont(new Font("Century Gothic", Font.PLAIN, 47));
//			
//			JLabel lblNewEvents = new JLabel("2 new events");
//			GroupLayout gl_DayPanel = new GroupLayout(DayPanel);
//			gl_DayPanel.setHorizontalGroup(
//				gl_DayPanel.createParallelGroup(Alignment.LEADING)
//					.addGroup(gl_DayPanel.createSequentialGroup()
//						.addContainerGap()
//						.addGroup(gl_DayPanel.createParallelGroup(Alignment.LEADING)
//							.addComponent(lblNewEvents, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
//							.addGroup(gl_DayPanel.createSequentialGroup()
//								.addComponent(dayNumber, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
//								.addGap(5)
//								.addComponent(ballPic, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
//						.addContainerGap())
//			);
//			gl_DayPanel.setVerticalGroup(
//				gl_DayPanel.createParallelGroup(Alignment.LEADING)
//					.addGroup(gl_DayPanel.createSequentialGroup()
//						.addGroup(gl_DayPanel.createParallelGroup(Alignment.LEADING)
//							.addComponent(dayNumber, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
//							.addComponent(ballPic, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
//						.addPreferredGap(ComponentPlacement.RELATED)
//						.addComponent(lblNewEvents, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//						.addContainerGap())
//			);
//			DayPanel.setLayout(gl_DayPanel);
		
		//DayPanelFactory.createDayPanel(CalendarPanel, Calendar.getInstance());
		//DayPanelFactory.createMonth(CalendarPanel, Calendar.MONTH, Calendar.YEAR);
		
		
		
		//CalendarPanel.add(, "4, 4, fill, fill");
		// ------------------------------------------------------------------------------------------------------------------------
		
		//JPanel panel = new JPanel();
		//CalendarPanel.add(panel, "14, 12, fill, fill");
		
		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		NotificationScrollPane.setColumnHeaderView(lblNotifications);
		
		JPanel panel_1 = new JPanel();
		NotificationScrollPane.setViewportView(panel_1);
		panel_1.setLayout(new MigLayout("", "[210px,grow,fill]", "[28px][28px]"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_1.add(panel_2, "cell 0 0,growx,aligny top");
		
		JLabel lblNotification = new JLabel("Notification 1");
		panel_2.add(lblNotification);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_1.add(panel_3, "cell 0 1,growx,aligny top");
		
		JLabel label_1 = new JLabel("Notification 1");
		panel_3.add(label_1);
		MenuPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("max(24dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.GLUE_ROWSPEC,}));
		
		
		JLabel lblMonth = new JLabel("Month Year");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		MenuPanel.add(lblMonth, "3, 1, 5, 1");
		
		prevMonthBtn = new JButton("<");
		MenuPanel.add(prevMonthBtn, "1, 1, fill, center");
		MonthChangeListener lForPrevMonth = new MonthChangeListener();
		prevMonthBtn.addActionListener(lForPrevMonth);
		
		nextMonthBtn = new JButton(">");
		MenuPanel.add(nextMonthBtn, "9, 1, fill, center");
		MonthChangeListener lForNextMonth = new MonthChangeListener();
		nextMonthBtn.addActionListener(lForNextMonth);
		
		
		
		JButton btnOption = new JButton("Option 1");
		MenuPanel.add(btnOption, "1, 3");
		
		JButton btnOption_1 = new JButton("Option 2");
		MenuPanel.add(btnOption_1, "3, 3");
		
		JButton btnOption_2 = new JButton("Option 3");
		MenuPanel.add(btnOption_2, "5, 3");
		
		JButton btnOption_3 = new JButton("Option 4");
		MenuPanel.add(btnOption_3, "7, 3");
		
		JButton btnOption_4 = new JButton("Option 5");
		MenuPanel.add(btnOption_4, "9, 3");
		contentPane.setLayout(gl_contentPane);
		
		
		calendarHandler = new CalendarHandler(CalendarPanel, lblMonth);
		calendarHandler.createMonth(Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR));
		calendarHandler.updateMatches();
	}
	
	private class MonthChangeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(arg0.getSource() == prevMonthBtn)
			{
				calendarHandler.prevMonth();
				calendarHandler.updateMatches();
			}
			else if(arg0.getSource() == nextMonthBtn)
			{
				calendarHandler.nextMonth();
				calendarHandler.updateMatches();
			}
			
		}
		
	}
}
