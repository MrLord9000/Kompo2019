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
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

public class LiveScoresDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			LiveScoresDialog dialog = new LiveScoresDialog();
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
	public LiveScoresDialog() {
		setBounds(100, 100, 778, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblMatchName = new JLabel("Match name");
			lblMatchName.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			lblMatchName.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblMatchName, BorderLayout.NORTH);
		}
		{
			JLabel lblCurrentScore = new JLabel("Current score: ");
			lblCurrentScore.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			lblCurrentScore.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCurrentScore, BorderLayout.SOUTH);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setLayout(new MigLayout("", "[grow,fill]", "[][][][][]"));
				{
					JLabel lblNotificationPanel = new JLabel("Notification panel 1");
					lblNotificationPanel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					panel.add(lblNotificationPanel, "cell 0 0");
				}
				{
					JLabel label = new JLabel("Notification panel 1");
					label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					panel.add(label, "cell 0 1");
				}
				{
					JLabel label = new JLabel("Notification panel 1");
					label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					panel.add(label, "cell 0 2");
				}
				{
					JLabel label = new JLabel("Notification panel 1");
					label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					panel.add(label, "cell 0 3");
				}
				{
					JLabel label = new JLabel("Notification panel 1");
					label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					panel.add(label, "cell 0 4");
				}
			}
		}
	}

}
