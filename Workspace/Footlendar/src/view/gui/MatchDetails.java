package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.Match;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

public class MatchDetails extends JDialog
{

	/**
	 * Main constructor creating the dialog window
	 */
	public MatchDetails(Match match) 
	{
		setBounds(100, 100, 455, 321);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("72px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "2, 2, fill, fill");
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Century Gothic", Font.BOLD, 14));
		
		JLabel label_2 = new JLabel(match.getHome().getName());
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
						.addComponent(lblHome))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, "4, 2, fill, fill");
		
		JLabel lblAway = new JLabel("Away");
		lblAway.setFont(new Font("Century Gothic", Font.BOLD, 14));
		
		JLabel label = new JLabel(match.getAway().getName());
		label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(lblAway))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAway)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, "2, 4, 3, 1, fill, fill");
		
		JTextPane txtpnTeamDescription = new JTextPane();
		txtpnTeamDescription.setEditable(false);
		txtpnTeamDescription.setText(match.getDescription());
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Century Gothic", Font.BOLD, 14));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnTeamDescription, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
						.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnTeamDescription, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, "2, 6, 3, 1, fill, fill");
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Century Gothic", Font.BOLD, 18));
		panel_2.add(lblResult);
		
		JLabel label_1;
		if(match.getScore() != null)
		{
			label_1 = new JLabel(match.getScore().toString());			
		}
		else
		{
			label_1 = new JLabel("N/A");
		}
		label_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
		panel_2.add(label_1);
	}
}
