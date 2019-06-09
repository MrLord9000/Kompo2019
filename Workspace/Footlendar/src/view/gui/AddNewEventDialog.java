package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.jgoodies.common.collect.LinkedListModel;

import controller.Match;
import controller.Team;
import model.MatchRepo;
import model.TeamRepo;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.util.Random;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class AddNewEventDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldHome;
	private JTextField textFieldAway;
	private LinkedList<String> teamNames;
	
	private JComboBox comboBoxHome, comboBoxAway;
	private JButton btnCreateHome, btnCreateAway;
	private String homeName, awayName;
	private JTextPane txtpnEnterDescriptionHere;
	private JDatePickerImpl datePicker;
	JSpinner spinnerMinutes, spinnerHours;
	/**
	 * Create the dialog.
	 */
	public AddNewEventDialog() 
	{
		setTitle("Add new event");
		setBounds(100, 100, 579, 408);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblHome = new JLabel("Home:");
		lblHome.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		teamNames = new LinkedList<String>();
		getTeamNames();
		
		comboBoxHome = new JComboBox(teamNames.toArray());
		
		JLabel lblSelectExisting = new JLabel("Select existing");
		lblSelectExisting.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		textFieldHome = new JTextField();
		textFieldHome.setColumns(10);
		
		JLabel lblCreateNew = new JLabel("Create New");
		lblCreateNew.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel lblAway = new JLabel("Away:");
		lblAway.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		btnCreateHome = new JButton("Create");
		btnCreateHome.addActionListener(new NewEventActionListener());
		btnCreateAway = new JButton("Create");
		btnCreateAway.addActionListener(new NewEventActionListener());
		
		textFieldAway = new JTextField();
		textFieldAway.setColumns(10);
		
		JLabel label = new JLabel("Create New");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		comboBoxAway = new JComboBox(teamNames.toArray());
		
		JLabel label_1 = new JLabel("Select existing");
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JLabel lblStartTime = new JLabel("Start time:");
		lblStartTime.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		JPanel panelDatePicker = new JPanel();
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		
		class DateLabelFormatter extends AbstractFormatter {

		    private String datePattern = "yyyy-MM-dd";
		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		    @Override
		    public Object stringToValue(String text) throws ParseException {
		        return dateFormatter.parseObject(text);
		    }

		    @Override
		    public String valueToString(Object value) throws ParseException {
		        if (value != null) {
		            Calendar cal = (Calendar) value;
		            return dateFormatter.format(cal.getTime());
		        }

		        return "";
		    }

		}
		
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		 
		panelDatePicker.add(datePicker);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		txtpnEnterDescriptionHere = new JTextPane();
		txtpnEnterDescriptionHere.setText("Enter description here...");
		
		spinnerHours = new JSpinner();
		spinnerHours.setModel(new SpinnerNumberModel(0, null, 23, 1));
		
		spinnerMinutes = new JSpinner();
		spinnerMinutes.setModel(new SpinnerNumberModel(0, null, 59, 1));
		
		JLabel lblH = new JLabel("H");
		
		JLabel lblMin = new JLabel("min");
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(10)
									.addComponent(lblSelectExisting, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addContainerGap()
									.addComponent(comboBoxHome, 0, 268, Short.MAX_VALUE)))
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCreateNew, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldHome, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAway, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 347, Short.MAX_VALUE)
							.addComponent(btnCreateHome, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblStartTime, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(311)
									.addComponent(btnCreateAway, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(comboBoxAway, 0, 268, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
											.addGap(125)))
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldAway, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblHome))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelDatePicker, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(spinnerHours, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(17)
									.addComponent(lblH)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(spinnerMinutes, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(10)
									.addComponent(lblMin)))
							.addGap(186))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtpnEnterDescriptionHere, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblHome)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectExisting, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCreateNew, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldHome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxHome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAway, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreateHome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldAway, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxAway, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(btnCreateAway))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblStartTime, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panelDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinnerHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblH)
								.addComponent(lblMin))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnEnterDescriptionHere, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add event");
				okButton.addActionListener(new CreateEventListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new CreateEventListener());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void getTeamNames()
	{
		if(teamNames != null)
		{
			teamNames.clear();
			for(Team item : TeamRepo.getInstance().getAll())
			{
				teamNames.add(item.getName());
			}			
		}
	}
	
	private void updateComboBoxes()
	{
		comboBoxHome.removeAllItems();
		for(String item : teamNames)
		{
			comboBoxHome.addItem(item);
		}
		comboBoxAway.removeAllItems();
		for(String item : teamNames)
		{
			comboBoxAway.addItem(item);
		}
		
		if(homeName != null)
		{
			comboBoxHome.setSelectedItem(homeName);			
		}
		if(awayName != null)
		{			
			comboBoxAway.setSelectedItem(awayName);
		}
	}
	
	private class CreateEventListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(arg0.getActionCommand() == "OK")
			{
				MatchRepo.getInstance().add(new Match(new Random().nextInt(), 
						TeamRepo.getInstance().get((String) comboBoxHome.getSelectedItem()), 
						TeamRepo.getInstance().get((String) comboBoxAway.getSelectedItem()), 
						new GregorianCalendar(datePicker.getModel().getYear(), datePicker.getModel().getMonth(), datePicker.getModel().getDay(), (int)spinnerHours.getValue(), (int)spinnerMinutes.getValue(), 0),
						txtpnEnterDescriptionHere.getText()));
				CalendarHandler.updateMatches();
				MainWindow.getMainWindow().revalidate();
			}
			else if(arg0.getActionCommand() == "Cancel")
			{
				dispose();
			}
			
		}
		
	}
	
	private class NewEventActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(arg0.getSource() == btnCreateHome)
			{
				System.out.println("create home pressed");
				homeName = textFieldHome.getText();
				TeamRepo.getInstance().add(new Team(homeName));
				getTeamNames();
				updateComboBoxes();
				
			}
			else if(arg0.getSource() == btnCreateAway)
			{
				System.out.println("create away pressed");
				awayName = textFieldAway.getText();
				TeamRepo.getInstance().add(new Team(awayName));
				getTeamNames();
				updateComboBoxes();
			}
		}
	}
}
