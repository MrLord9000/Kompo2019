package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ElementAlreadyInCollectionException;
import controller.User;
import model.Team;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

public class TeamDetails extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor for team details dialog window.
	 */
	public TeamDetails(final Team team) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		JLabel lblTeamName = new JLabel(team.getName());
		lblTeamName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setText("Description...");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnDescription, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(lblTeam, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTeamName, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTeam)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTeamName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnDescription, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						dispose();
						
					}
					
				});
				
				JButton btnAddToFavorites = new JButton("Add to favorites");
				btnAddToFavorites.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						try
						{
							User.getInstance().addFavouriteTeam(team);
						} 
						catch (ElementAlreadyInCollectionException e1)
						{
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(btnAddToFavorites);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
