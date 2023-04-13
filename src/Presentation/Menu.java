package Presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import Business.IDandPasswords;
import Business.Schedule;
import Business.EmailSender;


public class Menu implements ActionListener{

	JFrame frame = new JFrame();
	JLabel welcomeLabel = new JLabel();
	JButton viewScheduleBtn = new JButton("View Schedule");
	JButton sendEmailBtn = new JButton("Send Email");
	JButton addCustomerBtn = new JButton("Add Customer");
	JButton addEmployeeBtn = new JButton("Add Employee");
	
	public Menu(String userID){
		
		viewScheduleBtn.setBounds(100,100,200,40);
		viewScheduleBtn.setFocusable(false);
		viewScheduleBtn.addActionListener(this);
		
		sendEmailBtn.setBounds(100,150,200,40);
		sendEmailBtn.setFocusable(false);
		sendEmailBtn.addActionListener(this);
		
		addCustomerBtn.setBounds(100,200,200,40);
		addCustomerBtn.setFocusable(false);
		addCustomerBtn.addActionListener(this);

		addEmployeeBtn.setBounds(100,250,200,40);
		addEmployeeBtn.setFocusable(false);
		addEmployeeBtn.addActionListener(this);
		
		welcomeLabel.setBounds(10,10,400,35);
		welcomeLabel.setFont(new Font(null,Font.PLAIN,20));
		welcomeLabel.setText("Welcome, "+userID);
						
		frame.add(welcomeLabel);
		frame.add(viewScheduleBtn);
		frame.add(sendEmailBtn);
		// frame.add(addCustomerBtn);
		// frame.add(addEmployeeBtn);
		if(userID.equals("ADMIN")) {
			frame.add(addEmployeeBtn);			
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if(e.getSource()==viewScheduleBtn) {
		      SwingUtilities.invokeLater(() -> {
		          try
		          {
		        	 frame.dispose();
		             Schedule frame = new Schedule();
		             frame.setSize(700, 450);
		             Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		             frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		             frame.setResizable(false);
		             frame.setVisible(true);
		          }
		          catch (Exception e1)
		          {
		             e1.printStackTrace();
		          }
		       });
		 }
		 if(e.getSource()==sendEmailBtn) {
			SwingUtilities.invokeLater(() -> {
				try
				{
				   frame.dispose();
				   EmailSender frame = new EmailSender();
				   frame.setSize(700, 450);
				   Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				   frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
				   frame.setResizable(false);
				   frame.setVisible(true);
				}
				catch (Exception e1)
				{
				   e1.printStackTrace();
				}
			 });
		 }
		 if(e.getSource()==addCustomerBtn) {
			SwingUtilities.invokeLater(() -> {
				try
				{
				   frame.dispose();
				//    addEmployeeGUI frame = new addEmployeeGUI();
				//    frame.setSize(700, 450);
				//    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				//    frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
				//    frame.setResizable(false);
				//    frame.setVisible(true);
				}
				catch (Exception e1)
				{
				   e1.printStackTrace();
				}
			 });
		 }
		 if(e.getSource()==addEmployeeBtn) {
			SwingUtilities.invokeLater(() -> {
				try
				{
                    addEmployeeGUI frame = new addEmployeeGUI();
                    // frame.setSize(700, 450);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
                    frame.setResizable(false);
                    frame.setVisible(true);
				   /*addCustomer frame = new AddCustomer();
				   frame.setSize(700, 450);
				   Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				   frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
				   frame.setResizable(false);
				   frame.setVisible(true);*/
				}
				catch (Exception e1)
				{
				   e1.printStackTrace();
				}
			 });
		}
	}
}