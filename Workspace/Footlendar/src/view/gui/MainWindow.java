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

import controller.Match;
import controller.Team;
import controller.User;
import model.MatchRepo;
import model.TeamRepo;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import view.tui.ConsoleNotifier;

import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JCheckBoxMenuItem;

public class MainWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static NotificationPanel notificationPanel;
	private CalendarHandler calendarHandler;
	private JPanel contentPane;
	private JButton prevMonthBtn;
	private JButton nextMonthBtn;
	private JButton btnFavorites, btnAllEvents, btnAllTeams;
	
	private static MainWindow frame;
	public static MainWindow getMainWindow() { return frame; }
	
	public static NotificationPanel getNotificationPanel() { return notificationPanel; }
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
					TeamRepo.getInstance().load();
					MatchRepo.getInstance().load();
					User.getInstance().load();
					User.getInstance().setNotifier(new GuiNotifier());
					frame = new MainWindow();
					frame.setVisible(true);
				} 
				catch (Exception e)
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
		setTitle("Footlendar v.0.1");
		// Window default font
		setFont(new Font("Century Gothic", Font.PLAIN, 14));
		// Default close operation - set to DISPOSE_ON_CLOSE for best compatibility
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ------------------------------------------------------------------------
		// --- Set window bounds - keep in mind this should be resized ------------
		
			setBounds(100, 100, 1491, 795);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
			
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
		notificationPanel = new NotificationPanel();
		
		// Center days of week panel creation
		JPanel daysOfWeekPanel = new JPanel();
		
		// Main frame group layout configuration ----------------------------------------------------------------------------------
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(notificationPanel, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(MenuPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(daysOfWeekPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(CalendarPanel, GroupLayout.PREFERRED_SIZE, 1092, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(notificationPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(MenuPanel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(daysOfWeekPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(CalendarPanel, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)))
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
		
		
		// #### Month label ####
		
		JLabel lblMonth = new JLabel("Month Year");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		MenuPanel.add(lblMonth, "3, 1, 5, 1");
		
		// #### Month traversal buttons ####
		
		prevMonthBtn = new JButton("<");
		MenuPanel.add(prevMonthBtn, "1, 1, fill, center");
		MonthChangeListener lForPrevMonth = new MonthChangeListener();
		prevMonthBtn.addActionListener(lForPrevMonth);
		
		nextMonthBtn = new JButton(">");
		MenuPanel.add(nextMonthBtn, "9, 1, fill, center");
		MonthChangeListener lForNextMonth = new MonthChangeListener();
		nextMonthBtn.addActionListener(lForNextMonth);
		
		// #### Option buttons ####
		
		btnFavorites = new JButton("Favorites");
		btnFavorites.addActionListener(new OptionsListener());
		MenuPanel.add(btnFavorites, "1, 3");
		
		btnAllEvents = new JButton("All Events");
		btnAllEvents.addActionListener(new OptionsListener());
		MenuPanel.add(btnAllEvents, "3, 3");
		
		btnAllTeams = new JButton("All Teams");
		btnAllTeams.addActionListener(new OptionsListener());
		MenuPanel.add(btnAllTeams, "5, 3");
		
		JButton btnOption_3 = new JButton("Option 4");
		MenuPanel.add(btnOption_3, "7, 3");
		
		JButton btnOption_4 = new JButton("Option 5");
		MenuPanel.add(btnOption_4, "9, 3");
		contentPane.setLayout(gl_contentPane);
		
		
		// #### Calendar initialization ####
		calendarHandler = new CalendarHandler(CalendarPanel, lblMonth);
		calendarHandler.createMonth(GregorianCalendar.getInstance().get(Calendar.MONTH) + 1, GregorianCalendar.getInstance().get(Calendar.YEAR));
		calendarHandler.updateMatches();
	}
	
	private class OptionsListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(arg0.getSource() == btnFavorites)
			{
				FavoritesDialog dialog = new FavoritesDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			else if(arg0.getSource() == btnAllEvents)
			{
				AllEventsDialog dialog = new AllEventsDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			else if(arg0.getSource() == btnAllTeams)
			{
				
			}
		}
		
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
