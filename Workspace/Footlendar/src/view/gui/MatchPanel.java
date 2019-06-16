package view.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import controller.Match;
import controller.NotifyTimer;

/**
 * Class responsible for displaying tracked match panel.
 * It also handles setting the notification time before the match.
 * @author Filip Mazurek
 *
 */
public class MatchPanel extends JPanel
{
	private Match trackedMatch;
	
	private JRadioButtonMenuItem chckbxmntmMinBefore;
	private JRadioButtonMenuItem chckbxmntmHBefore;
	private JRadioButtonMenuItem chckbxmntmHBefore_1;
	private JRadioButtonMenuItem chckbxmntmDayBefore;
	private JRadioButtonMenuItem chckbxmntmDaysBefore;
	private JTextPane txtpnNotes;
	
	public MatchPanel(Match trackedMatch)
	{
		this.trackedMatch = trackedMatch;
		
		// Creating the panel with appropriate border
		this.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		
		// Creating layout and elements within the match event panel
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{217, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 45, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel_2);
		
		JLabel lblTeamVs = new JLabel(trackedMatch.getHome() + " vs " + trackedMatch.getAway());
		lblTeamVs.setForeground(UIManager.getColor("textHighlight"));
		lblTeamVs.setFont(new Font("Century Gothic", Font.BOLD, 14));
		GridBagConstraints gbc_lblTeamVs = new GridBagConstraints();
		gbc_lblTeamVs.insets = new Insets(0, 0, 5, 0);
		gbc_lblTeamVs.anchor = GridBagConstraints.CENTER;
		gbc_lblTeamVs.gridx = 0;
		gbc_lblTeamVs.gridy = 0;
		this.add(lblTeamVs, gbc_lblTeamVs);
		
		JLabel lblJune = new JLabel(trackedMatch.getStartTime().get(Calendar.DAY_OF_MONTH) + " " + trackedMatch.getStartTime().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + trackedMatch.getStartTime().get(Calendar.YEAR) );
		lblJune.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		GridBagConstraints gbc_lblJune = new GridBagConstraints();
		gbc_lblJune.insets = new Insets(0, 0, 5, 0);
		gbc_lblJune.gridx = 0;
		gbc_lblJune.gridy = 1;
		this.add(lblJune, gbc_lblJune);
		
		JLabel lblStartsAt = new JLabel("Starts at " + trackedMatch.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" + trackedMatch.getStartTime().get(Calendar.MINUTE));
		lblStartsAt.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		GridBagConstraints gbc_lblStartsAt = new GridBagConstraints();
		gbc_lblStartsAt.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartsAt.gridx = 0;
		gbc_lblStartsAt.gridy = 2;
		this.add(lblStartsAt, gbc_lblStartsAt);
		
		txtpnNotes = new JTextPane();
		txtpnNotes.setText(trackedMatch.getDescription());
		txtpnNotes.addKeyListener(new DescriptionPanelListener());
		txtpnNotes.addFocusListener(new DescriptionFocusListener());
		GridBagConstraints gbc_txtpnNotes = new GridBagConstraints();
		gbc_txtpnNotes.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnNotes.fill = GridBagConstraints.BOTH;
		gbc_txtpnNotes.gridx = 0;
		gbc_txtpnNotes.gridy = 3;
		this.add(txtpnNotes, gbc_txtpnNotes);
		
		JButton btnNotification = new JButton("Notify me...");
		GridBagConstraints gbc_btnNotification= new GridBagConstraints();
		gbc_btnNotification.gridx = 0;
		gbc_btnNotification.gridy = 4;
		this.add(btnNotification, gbc_btnNotification);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopupBtn(btnNotification, popupMenu);
		//addPopup(this, popupMenu);
		
		ButtonGroup group = new ButtonGroup();
		
		chckbxmntmMinBefore = new JRadioButtonMenuItem("30 min before");
		chckbxmntmMinBefore.addActionListener(new TimerNotificationAction());
		group.add(chckbxmntmMinBefore);
		popupMenu.add(chckbxmntmMinBefore);
		
		chckbxmntmHBefore = new JRadioButtonMenuItem("1 h before");
		chckbxmntmHBefore.addActionListener(new TimerNotificationAction());
		group.add(chckbxmntmHBefore);
		popupMenu.add(chckbxmntmHBefore);
		
		chckbxmntmHBefore_1 = new JRadioButtonMenuItem("4 h before");
		chckbxmntmHBefore_1.addActionListener(new TimerNotificationAction());
		group.add(chckbxmntmHBefore_1);
		popupMenu.add(chckbxmntmHBefore_1);
		
		chckbxmntmDayBefore = new JRadioButtonMenuItem("1 day before");
		chckbxmntmDayBefore.addActionListener(new TimerNotificationAction());
		group.add(chckbxmntmDayBefore);
		popupMenu.add(chckbxmntmDayBefore);
		
		chckbxmntmDaysBefore = new JRadioButtonMenuItem("2 days before");
		chckbxmntmDaysBefore.addActionListener(new TimerNotificationAction());
		group.add(chckbxmntmDaysBefore);
		popupMenu.add(chckbxmntmDaysBefore);
		
	}
	
	private static void addPopupBtn(final JButton button, final JPopupMenu popup) 
	{
		button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(arg0.getSource() == button)
				{
					showMenu(button);
				}
				
			}
			private void showMenu(JButton e) {
				popup.show(e, 0, e.getHeight());
			}
		});
	}
	
	private class DescriptionFocusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent arg0)
		{
			txtpnNotes.setText(trackedMatch.getDescription());
			
		}

		@Override
		public void focusLost(FocusEvent arg0)
		{
			trackedMatch.setDescription(txtpnNotes.getText());
		}
		
	}
	
	private class DescriptionPanelListener implements KeyListener
	{
		
		@Override
		public void keyPressed(KeyEvent arg0)
		{
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
			{
				trackedMatch.setDescription(txtpnNotes.getText());
			}
			if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				txtpnNotes.setText(trackedMatch.getDescription());
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class TimerNotificationAction implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			AbstractButton abstractButton = (AbstractButton) e.getSource();
			boolean selected = abstractButton.getModel().isSelected();
			System.out.println("Button " + abstractButton.getText() + " is " + (selected ? "selected" : "unselected"));

			if(chckbxmntmMinBefore.isSelected())
			{
				trackedMatch.setminutesRemindBeforeStart(30);
			}
			else if(chckbxmntmHBefore.isSelected())
			{
				trackedMatch.setminutesRemindBeforeStart(60);
			}
			else if(chckbxmntmHBefore_1.isSelected())
			{
				trackedMatch.setminutesRemindBeforeStart(4 * 60);
			}
			else if(chckbxmntmDayBefore.isSelected())
			{
				trackedMatch.setminutesRemindBeforeStart(24 * 60);
			}
			else if(chckbxmntmDaysBefore.isSelected())
			{
				trackedMatch.setminutesRemindBeforeStart(48 * 60);
			}
			
			trackedMatch.resetTimer();
		}
		
	}
	
}
