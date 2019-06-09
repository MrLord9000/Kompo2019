package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotificationDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public NotificationDialog(String message) {
		setBounds(100, 100, 589, 124);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel(message);
			lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("Ok");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				panel.add(btnOk);
			}
		}
	}

}
