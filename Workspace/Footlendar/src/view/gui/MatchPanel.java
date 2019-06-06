package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import controller.Match;

public class MatchPanel extends JPanel
{
	private Match trackedMatch;
	
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
		
		JTextPane txtpnNotes = new JTextPane();
		txtpnNotes.setText(trackedMatch.getDescription());
		GridBagConstraints gbc_txtpnNotes = new GridBagConstraints();
		gbc_txtpnNotes.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnNotes.fill = GridBagConstraints.BOTH;
		gbc_txtpnNotes.gridx = 0;
		gbc_txtpnNotes.gridy = 3;
		this.add(txtpnNotes, gbc_txtpnNotes);
		
		JCheckBox chckbxNotifyMe = new JCheckBox("Notify me");
		GridBagConstraints gbc_chckbxNotifyMe = new GridBagConstraints();
		gbc_chckbxNotifyMe.gridx = 0;
		gbc_chckbxNotifyMe.gridy = 4;
		this.add(chckbxNotifyMe, gbc_chckbxNotifyMe);
		
	}
}
