package Presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import Persistence.Administrator;

public class LoginPage implements ActionListener{
    JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel titleLabel = new JLabel("Hair for You");
	JLabel subTitleLabel = new JLabel("Appointment Management System");
	JLabel userIDLabel = new JLabel("User ID:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel messageLabel = new JLabel();

    HashMap<String,String> logininfo = new HashMap<String,String>();
    
    public LoginPage(HashMap<String,String> loginInfoTemp){
        logininfo = loginInfoTemp;

		titleLabel.setBounds(150,25,250,25);
		titleLabel.setFont(new Font(null,Font.ITALIC,25));

		subTitleLabel.setBounds(50,50,300,25);
		subTitleLabel.setFont(new Font(null,Font.ITALIC,18));

        userIDLabel.setBounds(50,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);
		
		messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
		
		userIDField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		
		loginButton.setBounds(170,200,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		frame.add(titleLabel);
		frame.add(subTitleLabel);
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
    }

    @SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==loginButton){
			
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			if(logininfo.containsKey(userID)){
				if(logininfo.get(userID).equals(password)){
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login Successful.");
					// frame.dispose();
					Menu menu = new Menu(userID);
				}
				else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Wrong Password.");
				}

			}
			else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Username not found.");
			}
		}
    }
}