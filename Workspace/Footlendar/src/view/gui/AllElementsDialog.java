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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Match;
import controller.Team;
import model.MatchRepo;
import model.TeamRepo;

import javax.swing.JScrollPane;

public class AllElementsDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String collectionName;

	/**
	 * Main class constructor
	 * Creates the all elements dialog.
	 * Note - the way this component chooses variants is safe only when used locally so this class shouldn't be used outside of this program
	 * @param	collectionName	should be set to either "Matches" or "Teams", responsible for diferrent variants of this component
	 * 
	 */
	public AllElementsDialog(String collectionName) {
		this.collectionName = collectionName;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAllElements = new JLabel("All " + collectionName);
		lblAllElements.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblAllElements.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblAllElements, BorderLayout.NORTH);
	
	
		JPanel buttonPane = new JPanel();
		contentPanel.add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnDetails = new JButton("Details");
		btnDetails.addActionListener(new MatchDetailsListener());
		buttonPane.add(btnDetails);
		
	
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
	
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		SimpleDateFormat mainFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		
		if(collectionName == "Matches")
		{
			LinkedList<Match> allMatches = (LinkedList<Match>) MatchRepo.getInstance().getAll();
			Object[][] matchesModel = new Object[MatchRepo.getInstance().getAll().size()][5];
			
			for(int i = 0; i < allMatches.size(); i++)
			{
				matchesModel[i][0] = allMatches.get(i).getId();
				matchesModel[i][1] = allMatches.get(i).getHome();
				matchesModel[i][2] = allMatches.get(i).getAway();
				matchesModel[i][3] = mainFormat.format(allMatches.get(i).getStartTime().getTime());
				matchesModel[i][4] = allMatches.get(i).getDescription();
			}
			
			table.setModel(new DefaultTableModel(
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
			
			table.getColumnModel().getColumn(3).setPreferredWidth(117);
			table.getColumnModel().getColumn(4).setPreferredWidth(123);
		}
		else if(collectionName == "Teams")
		{
			LinkedList<Team> allTeams = (LinkedList<Team>) TeamRepo.getInstance().getAll();
			Object[][] teamsModel = new Object[TeamRepo.getInstance().getAll().size()][1];
			
			for(int i = 0; i < allTeams.size(); i++)
			{
				teamsModel[i][0] = allTeams.get(i).getName();
			}
			
			table.setModel(new DefaultTableModel(
					teamsModel,
					new String[] {
						"Name"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		}
		
		scrollPane.setViewportView(table);

	}

	/**
	 * Listener class responsible for displaying details about selected matches/teams
	 * @author Lord9000
	 *
	 */
	private class MatchDetailsListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(collectionName == "Matches")
			{
				int row = table.getSelectedRow();
				long id = (long) table.getValueAt(row, 0);
				MatchDetails dialog = new MatchDetails(MatchRepo.getInstance().get(id));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			else if(collectionName == "Teams")
			{
				int row = table.getSelectedRow();
				String name = (String) table.getValueAt(row, 0);
				TeamDetails dialog = new TeamDetails(TeamRepo.getInstance().get(name));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			
		}
	}
}
