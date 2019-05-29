package view.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
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

	private JPanel contentPane;

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
		setTitle("Footlendar v.0.06");
		setFont(new Font("Century Gothic", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1401, 795);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel MenuPanel = new JPanel();
		
		JPanel CalendarPanel = new JPanel();
		CalendarPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		
		JScrollPane NotificationScrollPane = new JScrollPane();
		NotificationScrollPane.setViewportBorder(null);
		NotificationScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		NotificationScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(NotificationScrollPane, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(MenuPanel, GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
						.addComponent(CalendarPanel, GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(NotificationScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(MenuPanel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(CalendarPanel, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)))
					.addContainerGap())
		);
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
				FormSpecs.DEFAULT_ROWSPEC,
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
		
		JLabel lblMonday = new JLabel("Monday");
		lblMonday.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
		CalendarPanel.add(lblMonday, "2, 2");
		
		JLabel lblTuesday = new JLabel("Tuesday");
		lblTuesday.setHorizontalAlignment(SwingConstants.CENTER);
		lblTuesday.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		CalendarPanel.add(lblTuesday, "4, 2");
		
		JLabel lblWednesday = new JLabel("Wednesday");
		lblWednesday.setHorizontalAlignment(SwingConstants.CENTER);
		lblWednesday.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		CalendarPanel.add(lblWednesday, "6, 2");
		
		JLabel lblThursday = new JLabel("Thursday");
		lblThursday.setHorizontalAlignment(SwingConstants.CENTER);
		lblThursday.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		CalendarPanel.add(lblThursday, "8, 2");
		
		JLabel lblFriday = new JLabel("Friday");
		lblFriday.setHorizontalAlignment(SwingConstants.CENTER);
		lblFriday.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		CalendarPanel.add(lblFriday, "10, 2");
		
		JLabel lblSaturday = new JLabel("Saturday");
		lblSaturday.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaturday.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		CalendarPanel.add(lblSaturday, "12, 2");
		
		JLabel lblSunday = new JLabel("Sunday");
		lblSunday.setHorizontalAlignment(SwingConstants.CENTER);
		lblSunday.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		CalendarPanel.add(lblSunday, "14, 2");
		
		JPanel DayPanel = new JPanel();
		DayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		CalendarPanel.add(DayPanel, "2, 4, fill, fill");
		
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
		
		JPanel panel = new JPanel();
		CalendarPanel.add(panel, "14, 12, fill, fill");
		
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
		
		JLabel lblJanuary = new JLabel("January 2019");
		lblJanuary.setHorizontalAlignment(SwingConstants.CENTER);
		lblJanuary.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		MenuPanel.add(lblJanuary, "1, 1, 9, 1");
		
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
	}
}
