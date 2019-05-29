package view.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class MainWindowOld
{

	private JFrame frmFootlendarAlpha;

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
					MainWindowOld window = new MainWindowOld();
					window.frmFootlendarAlpha.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindowOld() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmFootlendarAlpha = new JFrame();
		frmFootlendarAlpha.setResizable(false);
		frmFootlendarAlpha.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		frmFootlendarAlpha.setTitle("Footlendar alpha 0.01");
		frmFootlendarAlpha.setBounds(100, 100, 1024, 649);
		frmFootlendarAlpha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFootlendarAlpha.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(94dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px:grow"),
				ColumnSpec.decode("100px:grow"),
				ColumnSpec.decode("100px:grow"),
				ColumnSpec.decode("100px:grow"),
				ColumnSpec.decode("100px:grow"),
				ColumnSpec.decode("100px:grow"),
				ColumnSpec.decode("100px:grow"),},
			new RowSpec[] {
				RowSpec.decode("26px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("28px"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("100px:grow"),
				RowSpec.decode("100px:grow"),
				RowSpec.decode("100px:grow"),
				RowSpec.decode("100px:grow"),
				RowSpec.decode("100px:grow"),}));
		
		JLabel lblFootlendarV = new JLabel("Footlendar v0.01");
		lblFootlendarV.setHorizontalAlignment(SwingConstants.CENTER);
		lblFootlendarV.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		frmFootlendarAlpha.getContentPane().add(lblFootlendarV, "1, 1, 9, 1, fill, top");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 3, true));
		frmFootlendarAlpha.getContentPane().add(panel, "1, 3, 1, 8, fill, fill");
		
		JButton btnMyMatches = new JButton("My Matches");
		
		JButton btnMatchHistory = new JButton("Match History");
		btnMatchHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnIncomingEvents = new JButton("Incoming events");
		
		JButton btnNextOption = new JButton("Next option");
		
		JButton btnNextOption_1 = new JButton("Next option");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnMyMatches, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnIncomingEvents, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNextOption, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNextOption_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnMatchHistory, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(167, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnMyMatches, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMatchHistory, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnIncomingEvents, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNextOption, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNextOption_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(367, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblJanuary = new JLabel("January 2019");
		lblJanuary.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblJanuary.setHorizontalAlignment(SwingConstants.CENTER);
		frmFootlendarAlpha.getContentPane().add(lblJanuary, "3, 3, 7, 1");
		
		JLabel lblMonday = new JLabel("Monday");
		lblMonday.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblMonday.setHorizontalAlignment(SwingConstants.CENTER);
		frmFootlendarAlpha.getContentPane().add(lblMonday, "3, 4");
		
		JLabel lblTuesday = new JLabel("Tuesday");
		lblTuesday.setHorizontalAlignment(SwingConstants.CENTER);
		lblTuesday.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frmFootlendarAlpha.getContentPane().add(lblTuesday, "4, 4");
		
		JLabel lblWednesday = new JLabel("Wednesday");
		lblWednesday.setHorizontalAlignment(SwingConstants.CENTER);
		lblWednesday.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frmFootlendarAlpha.getContentPane().add(lblWednesday, "5, 4");
		
		JLabel lblThursday = new JLabel("Thursday");
		lblThursday.setHorizontalAlignment(SwingConstants.CENTER);
		lblThursday.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frmFootlendarAlpha.getContentPane().add(lblThursday, "6, 4");
		
		JLabel lblFriday = new JLabel("Friday");
		lblFriday.setHorizontalAlignment(SwingConstants.CENTER);
		lblFriday.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frmFootlendarAlpha.getContentPane().add(lblFriday, "7, 4");
		
		JLabel lblSaturday = new JLabel("Saturday");
		lblSaturday.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaturday.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frmFootlendarAlpha.getContentPane().add(lblSaturday, "8, 4");
		
		JLabel lblSunady = new JLabel("Sunday");
		lblSunady.setForeground(Color.RED);
		lblSunady.setHorizontalAlignment(SwingConstants.CENTER);
		lblSunady.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frmFootlendarAlpha.getContentPane().add(lblSunady, "9, 4");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.DARK_GRAY));
		frmFootlendarAlpha.getContentPane().add(panel_1, "3, 6, fill, fill");
		
		JLabel label = new JLabel("1");
		label.setFont(new Font("Century Gothic", Font.BOLD, 36));
		
		JLabel lblEvent = new JLabel("1 event ");
		lblEvent.setForeground(Color.RED);
		lblEvent.setHorizontalAlignment(SwingConstants.LEFT);
		lblEvent.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel label_1 = new JLabel("");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(MainWindowOld.class.getResource("/resources/football_64.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 62, Short.MAX_VALUE))
						.addComponent(lblEvent, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEvent)
					.addContainerGap(384, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.DARK_GRAY));
		frmFootlendarAlpha.getContentPane().add(panel_2, "4, 6, fill, fill");
		
		JLabel lblEvents = new JLabel("0 events");
		lblEvents.setHorizontalAlignment(SwingConstants.LEFT);
		lblEvents.setForeground(Color.BLACK);
		lblEvents.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel label_3 = new JLabel("2");
		label_3.setFont(new Font("Century Gothic", Font.BOLD, 36));
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
						.addComponent(lblEvents, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addComponent(label_4))
					.addGap(26)
					.addComponent(lblEvents)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.DARK_GRAY));
		frmFootlendarAlpha.getContentPane().add(panel_3, "5, 6, fill, fill");
		
		JLabel label_2 = new JLabel("0 events");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel label_5 = new JLabel("3");
		label_5.setFont(new Font("Century Gothic", Font.BOLD, 36));
		
		JLabel label_6 = new JLabel("");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 102, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(label_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 112, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(label_5)
						.addComponent(label_6))
					.addGap(26)
					.addComponent(label_2)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.DARK_GRAY));
		frmFootlendarAlpha.getContentPane().add(panel_4, "6, 6, fill, fill");
		
		JLabel label_7 = new JLabel("0 events");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel label_8 = new JLabel("4");
		label_8.setFont(new Font("Century Gothic", Font.BOLD, 36));
		
		JLabel label_9 = new JLabel("");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.WHITE);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 102, Short.MAX_VALUE)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_9, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 112, Short.MAX_VALUE)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(label_8)
						.addComponent(label_9))
					.addGap(26)
					.addComponent(label_7)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.DARK_GRAY));
		frmFootlendarAlpha.getContentPane().add(panel_5, "7, 6, fill, fill");
		
		JLabel label_10 = new JLabel("0 events");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel label_11 = new JLabel("5");
		label_11.setFont(new Font("Century Gothic", Font.BOLD, 36));
		
		JLabel label_12 = new JLabel("");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setForeground(Color.WHITE);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 102, Short.MAX_VALUE)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(label_11)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_12, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 112, Short.MAX_VALUE)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(label_11)
						.addComponent(label_12))
					.addGap(26)
					.addComponent(label_10)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.DARK_GRAY));
		frmFootlendarAlpha.getContentPane().add(panel_6, "8, 6, fill, fill");
		
		JLabel label_13 = new JLabel("0 events");
		label_13.setHorizontalAlignment(SwingConstants.LEFT);
		label_13.setForeground(Color.BLACK);
		label_13.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel label_14 = new JLabel("6");
		label_14.setFont(new Font("Century Gothic", Font.BOLD, 36));
		
		JLabel label_15 = new JLabel("");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setForeground(Color.WHITE);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 102, Short.MAX_VALUE)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(label_14)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_15, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
						.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 112, Short.MAX_VALUE)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(label_14)
						.addComponent(label_15))
					.addGap(26)
					.addComponent(label_13)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.DARK_GRAY));
		frmFootlendarAlpha.getContentPane().add(panel_7, "9, 6, fill, fill");
		
		JLabel label_16 = new JLabel("0 events");
		label_16.setHorizontalAlignment(SwingConstants.LEFT);
		label_16.setForeground(Color.BLACK);
		label_16.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel label_17 = new JLabel("7");
		label_17.setForeground(Color.RED);
		label_17.setFont(new Font("Century Gothic", Font.BOLD, 36));
		
		JLabel label_18 = new JLabel("");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setForeground(Color.WHITE);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 102, Short.MAX_VALUE)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addComponent(label_17)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_18, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 112, Short.MAX_VALUE)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(label_17)
						.addComponent(label_18))
					.addGap(26)
					.addComponent(label_16)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
	}
}
