package view.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.GoogleCalendarExporter;
import controller.Match;
import controller.User;
import model.DataBaseMatchLoader;
import model.DataBaseTeamLoader;
import model.DataBaseWriter;
import model.MatchRepo;
import model.TeamRepo;
import model.XMLFileWriter;
import model.XMLMatchLoader;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JMenuBar;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

/**
 * Main application window.
 * Displays the calendar, options bar and notifications panel.
 * @author Filip Mazurek
 *
 */
public class MainWindow extends JFrame
{
	//private static final String databasePath = "jdbc:sqlserver://localhost:1433;databaseName=FootlendarDB;integratedSecurity=true";
	private static final String databasePath = "jdbc:sqlserver://DESKTOP-41IQBFQ\\\\WINCCPLUSMIG2014;databaseName=FootlendarDB;integratedSecurity=true";
	private static final long serialVersionUID = 1L;
	private static NotificationPanel notificationPanel;
	private CalendarHandler calendarHandler;
	private JPanel contentPane;
	private JButton prevMonthBtn;
	private JButton nextMonthBtn;
	private JButton btnFavorites, btnAllEvents, btnAllTeams, btnAddEvent, btnDelete;
	
	private static MainWindow frame;
	/**
	 * Returns the main window component responsible for all the primary acitons.
	 * @return		The main window component
	 */
	public static MainWindow getMainWindow() { return frame; }
	/**
	 * Returns the left hand notification/tracked matches panel
	 * @return		The notification panel component
	 */
	public static NotificationPanel getNotificationPanel() { return notificationPanel; }

	/**
	 * Class constructor responsible for creating the main frame.
	 */
	public MainWindow() {
		
		TeamRepo.getInstance().setLoader(new DataBaseTeamLoader(databasePath));
		TeamRepo.getInstance().setSaver(new DataBaseWriter(databasePath));
		TeamRepo.getInstance().load();
		//MatchRepo.getInstance().setLoader(new DataBaseMatchLoader(".\\src\\resources\\data.xml"));
		MatchRepo.getInstance().setLoader(new DataBaseMatchLoader(databasePath));
		MatchRepo.getInstance().setSaver(new DataBaseWriter(databasePath));
		MatchRepo.getInstance().load();
		User.getInstance().load();
		User.getInstance().setNotifier(new GuiNotifier());
		
		// Temp
				GregorianCalendar cal1 = (GregorianCalendar) Calendar.getInstance();
				cal1.add(Calendar.MINUTE, 1);
				GregorianCalendar cal2 = (GregorianCalendar) Calendar.getInstance();
				cal2.add(Calendar.HOUR_OF_DAY, -1);
				MatchRepo.getInstance().add( new Match(666, TeamRepo.getInstance().get("Ukraina U20"), TeamRepo.getInstance().get("Włochy U20"), cal1, "World Cup U20 Final Stage") );
				MatchRepo.getInstance().add( new Match(667, TeamRepo.getInstance().get("Ecuador U20"), TeamRepo.getInstance().get("Korea Po?udniowa U20"), cal2, "World Cup U20 Final Stage") );
				MatchRepo.getInstance().add( new Match(668, TeamRepo.getInstance().get("Ukraina U20"), TeamRepo.getInstance().get("Włochy U20"), new GregorianCalendar(2019, 5, 12, 14, 7), "World Cup U20 Final Stage") );
		// Temp end
		
		frame = this;
		frame.setVisible(true);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/resources/football_64.png")));
		
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
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save all matches");
		KeyStroke keyShortSave = KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		mntmSave.setAccelerator(keyShortSave);
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("XML file (.xml)", "xml"));
				int returnVal = chooser.showSaveDialog(getMainWindow());
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					// Here file saving should happen
					XMLFileWriter xmlWriter = new XMLFileWriter(chooser.getSelectedFile().getAbsolutePath());
					MatchRepo.getInstance().setSaver(xmlWriter);
					MatchRepo.getInstance().save();
					MatchRepo.getInstance().setSaver(new DataBaseWriter(databasePath));
				}
			
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmOpen = new JMenuItem("Open saved matches");
		KeyStroke keyShortOpen = KeyStroke.getKeyStroke(
		        KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK);
		mntmOpen.setAccelerator(keyShortOpen);
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("XML file (.xml)", "xml"));
				int returnVal = chooser.showOpenDialog(getMainWindow());
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					// Here file opening should happen
					XMLMatchLoader xmlLoader = new XMLMatchLoader(chooser.getSelectedFile().getAbsolutePath());
					MatchRepo.getInstance().setLoader(xmlLoader);
					MatchRepo.getInstance().load();
					MatchRepo.getInstance().setLoader(new DataBaseMatchLoader(databasePath));
					CalendarHandler.updateMatches();
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExport = new JMenuItem("Export to Google Calendar");
		KeyStroke keyShortExport = KeyStroke.getKeyStroke(
		        KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
		mntmExport.setAccelerator(keyShortExport);
		mntmExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("CSV file (.csv)", "csv"));
				int returnVal = fileChooser.showSaveDialog(getMainWindow());
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						GoogleCalendarExporter.export(MatchRepo.getInstance().getAll(), fileChooser.getSelectedFile().getAbsolutePath());
					} 
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmExport);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				CalendarHandler.updateMatches();
			}
		});
		mnEdit.add(mntmRefresh);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutThisProgram = new JMenuItem("About this program");
		mntmAboutThisProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog dialog = new AboutDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnAbout.add(mntmAboutThisProgram);
			
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
					.addComponent(notificationPanel, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(CalendarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(daysOfWeekPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(MenuPanel, GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(notificationPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(MenuPanel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(daysOfWeekPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(CalendarPanel, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)))
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
		
		btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new OptionsListener());
		MenuPanel.add(btnAddEvent, "7, 3");
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new OptionsListener());
		MenuPanel.add(btnDelete, "9, 3");
		
		contentPane.setLayout(gl_contentPane);
		
		
		// #### Calendar initialization ####
		calendarHandler = new CalendarHandler(CalendarPanel, lblMonth);
		calendarHandler.createMonth(GregorianCalendar.getInstance().get(Calendar.MONTH) + 1, GregorianCalendar.getInstance().get(Calendar.YEAR));
		calendarHandler.updateMatches();
	}
	
	/**
	 * Options listener class implementing ActionListener interface
	 * Responsible for option buttons in the main window
	 * @author Lord9000
	 *
	 */
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
				AllElementsDialog dialog = new AllElementsDialog("Matches");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			else if(arg0.getSource() == btnAllTeams)
			{
				AllElementsDialog dialog = new AllElementsDialog("Teams");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			else if(arg0.getSource() == btnAddEvent)
			{
				AddNewEventDialog dialog = new AddNewEventDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			else if(arg0.getSource() == btnDelete)
			{
				DeleteDialog dialog = new DeleteDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		}
		
	}
	
	/**
	 * Listener class for switching between months 
	 * @author Lord9000
	 *
	 */
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
