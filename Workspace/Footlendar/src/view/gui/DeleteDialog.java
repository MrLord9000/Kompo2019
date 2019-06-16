package view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.MatchRepo;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Dialog for deleting matches before a set date in time.
 * @author Filip Mazurek
 *
 */
public class DeleteDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JDatePickerImpl datePicker;

	/**
	 * Create the dialog.
	 */
	public DeleteDialog() {
		setBounds(100, 100, 447, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
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
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		contentPanel.add(datePicker);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblDeleteAllBefore = new JLabel("Delete all matches before:");
		lblDeleteAllBefore.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblDeleteAllBefore.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblDeleteAllBefore, BorderLayout.NORTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Delete");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MatchRepo.getInstance().removeBefore(
								new GregorianCalendar(	datePicker.getModel().getYear(), 
														datePicker.getModel().getMonth(), 
														datePicker.getModel().getDay(), 0, 0, 0));
						CalendarHandler.updateMatches();
						MainWindow.getMainWindow().revalidate();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
