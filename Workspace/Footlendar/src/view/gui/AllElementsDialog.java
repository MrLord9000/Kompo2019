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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AllElementsDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public AllElementsDialog(String collectionName) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblAllElements = new JLabel("All " + collectionName);
			lblAllElements.setFont(new Font("Century Gothic", Font.PLAIN, 16));
			lblAllElements.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblAllElements, BorderLayout.NORTH);
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
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{new Long(1L), "Team 1", "Team 1", "1 June 2019 22:32", "A match very good yes"},
				},
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
			contentPanel.add(table, BorderLayout.CENTER);
		}
	}

}
