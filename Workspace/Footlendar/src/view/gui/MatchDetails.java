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
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

public class MatchDetails extends JDialog
{

	/**
	 * Create the dialog.
	 */
	public MatchDetails() {
		setBounds(100, 100, 455, 321);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GLUE_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "2, 2, fill, fill");
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Century Gothic", Font.BOLD, 14));
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Century Gothic", Font.BOLD, 14));
		
		JLabel label_2 = new JLabel("Team name");
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		
		JTextPane txtpnTeamDescription = new JTextPane();
		txtpnTeamDescription.setEditable(false);
		txtpnTeamDescription.setText("Team description");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnTeamDescription, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
						.addComponent(lblHome)
						.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnTeamDescription, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, "4, 2, fill, fill");
		
		JLabel lblAway = new JLabel("Away");
		lblAway.setFont(new Font("Century Gothic", Font.BOLD, 14));
		
		JLabel label = new JLabel("Team name");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		
		JLabel label_3 = new JLabel("Description");
		label_3.setFont(new Font("Century Gothic", Font.BOLD, 14));
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Team description");
		textPane.setEditable(false);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAway)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(85, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(9))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAway)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addGap(18)
					.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addGap(12))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, "2, 4, 3, 1, fill, fill");
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Century Gothic", Font.BOLD, 18));
		panel_2.add(lblResult);
		
		JLabel label_1 = new JLabel("0 : 0");
		label_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
		panel_2.add(label_1);
	}
}
