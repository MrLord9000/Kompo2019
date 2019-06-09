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
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AboutDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setBounds(100, 100, 267, 217);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblAbout = new JLabel("About");
		lblAbout.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		JLabel lblProgramCreatedBy = new JLabel("Program created by:");
		lblProgramCreatedBy.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		JLabel lblFilipMazurek = new JLabel("Filip Mazurek");
		lblFilipMazurek.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		JLabel lblAdrianZieliski = new JLabel("Adrian Zieliński");
		lblAdrianZieliski.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		JLabel lblProgramowanieKomponentowe = new JLabel("Programowanie Komponentowe 2019");
		lblProgramowanieKomponentowe.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAbout)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProgramCreatedBy)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAdrianZieliski, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFilipMazurek)))))
						.addComponent(lblProgramowanieKomponentowe))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAbout)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblProgramCreatedBy)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFilipMazurek)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAdrianZieliski)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addComponent(lblProgramowanieKomponentowe, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
