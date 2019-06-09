package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Match;
import controller.Team;
import controller.User;
import model.MatchRepo;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.AbstractListModel;

public class FavoritesDialog extends JDialog
{

	private JTable favMatchesTable;
	private JList favTeamsList;
	/**
	 * Create the dialog.
	 */
	public FavoritesDialog() {
		setBounds(100, 100, 605, 594);
		getContentPane().setLayout(new BorderLayout());
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setResizeWeight(0.5);
			getContentPane().add(splitPane, BorderLayout.NORTH);
			{
				JPanel panel = new JPanel();
				splitPane.setLeftComponent(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JLabel lblFavoriteMatches = new JLabel("Favorite Matches");
				lblFavoriteMatches.setFont(new Font("Century Gothic", Font.PLAIN, 13));
				lblFavoriteMatches.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblFavoriteMatches, BorderLayout.NORTH);
				
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				
				favMatchesTable = new JTable();
				favMatchesTable.setAutoCreateRowSorter(true);
				
				LinkedList<Match> allMatches = (LinkedList<Match>) User.getInstance().getFavouriteMatches();
				
				updateMatchesTable(allMatches);
				
				scrollPane.setViewportView(favMatchesTable);
			
			
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.SOUTH);
				{
					JButton btnDetails = new JButton("Details");
					btnDetails.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0)
						{
							int row = favMatchesTable.getSelectedRow();
							long id = (long) favMatchesTable.getValueAt(row, 0);
							MatchDetails dialog = new MatchDetails(MatchRepo.getInstance().get(id));
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						}
						
					});
					panel_1.add(btnDetails);
				}
				{
					JButton btnRemove = new JButton("Remove");
					btnRemove.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e)
						{
							int row = favMatchesTable.getSelectedRow();
							long id = (long) favMatchesTable.getValueAt(row, 0);
							User.getInstance().removeFavouriteMatch(MatchRepo.getInstance().get(id));
							updateMatchesTable(User.getInstance().getFavouriteMatches());
						}
						
					});
					panel_1.add(btnRemove);
				}
				
			}
			{
				JPanel panel = new JPanel();
				splitPane.setRightComponent(panel);
				panel.setLayout(new BorderLayout(0, 0));
				
				JLabel lblFavoriteTeams = new JLabel("Favorite Teams");
				lblFavoriteTeams.setHorizontalAlignment(SwingConstants.CENTER);
				lblFavoriteTeams.setFont(new Font("Century Gothic", Font.PLAIN, 13));
				panel.add(lblFavoriteTeams, BorderLayout.NORTH);
				
				LinkedList<Team> favTeams = User.getInstance().getFavTeams();
				
				favTeamsList = new JList();
				updateTeamsList(favTeams);
				
				panel.add(favTeamsList, BorderLayout.CENTER);
			
			
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.SOUTH);
				{
					JButton btnDetails_1 = new JButton("Details");
					btnDetails_1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e)
						{
							TeamDetails dialog = new TeamDetails(User.getInstance().getFavTeams().get(favTeamsList.getSelectedIndex()));
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						}
						
					});
					panel_1.add(btnDetails_1);
				}
				{
					JButton btnRemove_1 = new JButton("Remove");
					btnRemove_1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e)
						{
							User.getInstance().getFavTeams().remove(favTeamsList.getSelectedIndex());
							updateTeamsList(User.getInstance().getFavTeams());
						}
						
					});
					panel_1.add(btnRemove_1);
				}
				
			}
		}
	}
	
	private void updateTeamsList(LinkedList<Team> favTeams)
	{
		final String[] teamNames = new String[favTeams.size()];
		
		for(int i = 0; i < favTeams.size(); i++)
		{
			teamNames[i] = favTeams.get(i).getName();
		}
	
		
		favTeamsList.setModel(new AbstractListModel() {
			String[] values = teamNames;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	private void updateMatchesTable(LinkedList<Match> allMatches)
	{
		Object[][] matchesModel = new Object[MatchRepo.getInstance().getAll().size()][5];
		SimpleDateFormat mainFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		
		for(int i = 0; i < allMatches.size(); i++)
		{
			matchesModel[i][0] = allMatches.get(i).getId();
			matchesModel[i][1] = allMatches.get(i).getHome();
			matchesModel[i][2] = allMatches.get(i).getAway();
			matchesModel[i][3] = mainFormat.format(allMatches.get(i).getStartTime().getTime());
			matchesModel[i][4] = allMatches.get(i).getDescription();
		}
		
		favMatchesTable.setModel(new DefaultTableModel(
			matchesModel,
			new String[] {
				"Id", "Home", "Away", "Start time", "Description"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, Object.class, Object.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

}
