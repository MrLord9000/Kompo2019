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
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

/**
 * Notification dialog class. Responsible for displaying notification message in GUI interface.
 * @author Filip Mazurek
 *
 */
public class NotificationDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public NotificationDialog(String message) {
		setBounds(100, 100, 793, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("Alert!");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JTextPane txtpnEnterText = new JTextPane();
			txtpnEnterText.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			txtpnEnterText.setText(message);
			contentPanel.add(txtpnEnterText, BorderLayout.CENTER);
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
