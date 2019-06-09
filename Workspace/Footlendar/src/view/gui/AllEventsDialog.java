package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;

public class AllEventsDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			AllEventsDialog dialog = new AllEventsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AllEventsDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblAllMatches = new JLabel("All Matches");
			lblAllMatches.setFont(new Font("Century Gothic", Font.PLAIN, 16));
			lblAllMatches.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblAllMatches, BorderLayout.NORTH);
		}
		{
			JList list = new JList();
			contentPanel.add(list, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			contentPanel.add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			{
				JButton btnDetails = new JButton("Details");
				buttonPane.add(btnDetails);
			}
		}
	}

}
