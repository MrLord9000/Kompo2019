package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import java.awt.event.*;

import javax.swing.*;

public class HelloWorldSwing extends JFrame 
{
	private static final long serialVersionUID = 400683373025690209L;
	
	JButton button0;
	JTextField textField0;
	JTextArea textArea0;
	int buttonClicked;
	

	public static void main(String[] args)
	{
		new HelloWorldSwing();
	}
	
	public HelloWorldSwing()
	{
		// Set window's size
		this.setSize(400, 400);
		
		//this.setLocationRelativeTo(null);
		
		// Create the default toolkit to get the dimensions of the screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		// Getting the position to center the window on our screen
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		
		// Set the window position to the conter of the screen
		this.setLocation(xPos, yPos);
		
		// Disable window resizablility
		this.setResizable(false);
		
		// Quit by close button, this is important
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Footlendar");
		
		// Panels and labels
			JPanel panel0 = new JPanel();
			JLabel label0 = new JLabel("Hello There!");
				
				panel0.add(label0);

				label0.setFont(new Font("Lucida Console", 0, 20));
				label0.setText("GENERAL KENOBI!");
				label0.setToolTipText("You are a bold one!");
			
			button0 = new JButton("This is a button");
				
				panel0.add(button0);

				ListenForButton lForButton = new ListenForButton();
				button0.addActionListener(lForButton);
				
				button0.setToolTipText("Yep");
				
			textField0 = new JTextField("", 15);
				
				panel0.add(textField0);
			
				ListenForKeys lForKeys = new ListenForKeys();
				textField0.addKeyListener(lForKeys);
				
				textField0.setBackground(new Color(255, 0, 128));
				textField0.setToolTipText("Or maybe not");
			
			textArea0 = new JTextArea(15, 30);
			
				panel0.add(textArea0);
			
				textArea0.setAutoscrolls(true);
				textArea0.setText("Tracking Events:\n");
				textArea0.setLineWrap(true);
				textArea0.setWrapStyleWord(true);
				
				int numberOfLines = textArea0.getLineCount();
				textArea0.append("\nLines created: " + numberOfLines);
				
			JScrollPane scrollBar0 = new JScrollPane(textArea0, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
				scrollBar0.setAutoscrolls(true);
				panel0.add(scrollBar0);
				
				
			// Adding the panel to the frame
			this.add(panel0);
			
			ListenForWindow lForWindow = new ListenForWindow();
			this.addWindowListener(lForWindow);
			
			
		this.setVisible(true);
		
		ListenForMouse lForMouse = new ListenForMouse();
		panel0.addMouseListener(lForMouse);
		
		// Sets default focus for textField0
		textField0.requestFocus();
	}
	
	// ************************************************************
	// *** Listeners implementation *******************************
	
	private class ListenForButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == button0)
			{
				buttonClicked++;
				textArea0.append("Button clicked " + buttonClicked + " times\n");
				
			}
			
		}
		
	}
	
	private class ListenForKeys implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent key)
		{
			textArea0.append("Key Hit: " + key.getKeyChar() + "\n");
			
		}

		@Override
		public void keyReleased(KeyEvent key)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent key)
		{
			// TODO Auto-generated method stub
			
		}

	}
	
	private class ListenForWindow implements WindowListener
	{

		@Override
		public void windowActivated(WindowEvent event)
		{
			textArea0.append("Window is active\n");
			
		}

		@Override
		public void windowClosed(WindowEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0)
		{
			textArea0.append("Window is inactive");
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0)
		{
			textArea0.append("Window is de minimized");
			
		}

		@Override
		public void windowIconified(WindowEvent arg0)
		{
			textArea0.append("Window is minimized");
			
		}

		@Override
		public void windowOpened(WindowEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ListenForMouse implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent event)
		{
			textArea0.append("Mouse Panel position: " + event.getX() + " " + event.getY() + "\n");
			textArea0.append("Mouse Screen position: " + event.getXOnScreen() + " " + event.getYOnScreen() + "\n");
			textArea0.append("Mouse Button: " + event.getButton() + "\n");
			textArea0.append("Mouse Clicks: " + event.getClickCount() + "\n");
		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
}