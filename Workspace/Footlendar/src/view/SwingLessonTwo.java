package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
// Two events we'll need
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
// Lib for formatting the numbers
import java.text.NumberFormat;

public class SwingLessonTwo extends JFrame
{
	private static final long serialVersionUID = 6906947715987691983L;

	
	JButton button1;
	JLabel label1, label2, label3;
	JTextField textField1, textField2;
	JCheckBox dollarSign, commaSeparator;
	JRadioButton addNums, subtractNums, multNums, divideNums;
	JSlider howManyTimes;
	
	double number1, number2, totalCalc;
	
	public static void main(String[] args)
	{
		System.out.println("Hello!");
		new SwingLessonTwo();
	}

	public SwingLessonTwo()
	{
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Java 2 - the better Java");
		
		JPanel mainPanel = new JPanel();
		
		
		// Creating the calculate button and hookling up the listener for it
		button1 = new JButton("Calculate");
		
			mainPanel.add(button1);
		
			ListenForButton lForButton = new ListenForButton();
			button1.addActionListener(lForButton);
			
		label1 = new JLabel("Number 1");
		
			mainPanel.add(label1);	
			label1.setToolTipText("The first number for the operation");
			
		textField1 = new JTextField("", 5);
		
			mainPanel.add(textField1);
			
		label2 = new JLabel("Number 2");
			
			mainPanel.add(label2);
			label2.setToolTipText("The second number for the operation");
			
		textField2 = new JTextField("", 5);
		
			mainPanel.add(textField2);
	
		dollarSign = new JCheckBox("Dollars");
		commaSeparator = new JCheckBox("Commas");
		
			mainPanel.add(dollarSign);
			mainPanel.add(commaSeparator, true);
			
		addNums = new JRadioButton("Add");
		subtractNums = new JRadioButton("Subtract");
		multNums = new JRadioButton("Multiply");
		divideNums = new JRadioButton("Divide");
		
			ButtonGroup operation = new ButtonGroup();
			operation.add(addNums);
			operation.add(subtractNums);
			operation.add(multNums);
			operation.add(divideNums);
			
			JPanel operPanel = new JPanel();
			Border operBorder = BorderFactory.createTitledBorder("Operation");
			
				operPanel.setBorder(operBorder);
			
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
