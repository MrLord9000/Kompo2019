package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.AbstractListModel;

public class FavoritesDialog extends JDialog
{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			FavoritesDialog dialog = new FavoritesDialog();
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
	public FavoritesDialog() {
		setBounds(100, 100, 497, 255);
		getContentPane().setLayout(new BorderLayout());
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setResizeWeight(0.5);
			getContentPane().add(splitPane, BorderLayout.NORTH);
			{
				JPanel panel = new JPanel();
				splitPane.setLeftComponent(panel);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblFavoriteMatches = new JLabel("Favorite Matches");
					lblFavoriteMatches.setFont(new Font("Century Gothic", Font.PLAIN, 13));
					lblFavoriteMatches.setHorizontalAlignment(SwingConstants.CENTER);
					panel.add(lblFavoriteMatches, BorderLayout.NORTH);
				}
				{
					JList list = new JList();
					list.setModel(new AbstractListModel() {
						String[] values = new String[] {"Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019", "Team 1 vs Team 2; 2 June 2019"};
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
					panel.add(list, BorderLayout.CENTER);
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.SOUTH);
					{
						JButton btnDetails = new JButton("Details");
						panel_1.add(btnDetails);
					}
					{
						JButton btnRemove = new JButton("Remove");
						panel_1.add(btnRemove);
					}
				}
			}
			{
				JPanel panel = new JPanel();
				splitPane.setRightComponent(panel);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblFavoriteTeams = new JLabel("Favorite Teams");
					lblFavoriteTeams.setHorizontalAlignment(SwingConstants.CENTER);
					lblFavoriteTeams.setFont(new Font("Century Gothic", Font.PLAIN, 13));
					panel.add(lblFavoriteTeams, BorderLayout.NORTH);
				}
				{
					JList list = new JList();
					list.setModel(new AbstractListModel() {
						String[] values = new String[] {"Team 1", "Team 2", "Team 3", "Team 4"};
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
					panel.add(list, BorderLayout.CENTER);
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.SOUTH);
					{
						JButton btnDetails_1 = new JButton("Details");
						panel_1.add(btnDetails_1);
					}
					{
						JButton btnRemove_1 = new JButton("Remove");
						panel_1.add(btnRemove_1);
					}
				}
			}
		}
	}

}
