package Business;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.apache.commons.mail.*;

public class EmailSender extends JFrame implements ActionListener {
    private JTextField recipientField, subjectField;
    private JTextArea messageArea;

    public EmailSender() {
        super("E-mail Sender");

        // Set up GUI components
        JLabel recipientLabel = new JLabel("Recipient:");
        JLabel subjectLabel = new JLabel("Subject:");
        JLabel messageLabel = new JLabel("Message:");
        recipientField = new JTextField(30);
        subjectField = new JTextField(30);
        messageArea = new JTextArea(10, 30);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(this);

        // Add components to frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(recipientLabel);
        panel.add(recipientField);
        panel.add(subjectLabel);
        panel.add(subjectField);
        panel.add(messageLabel);
        panel.add(new JScrollPane(messageArea));
        panel.add(sendButton);
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            // Set up email message
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("hairforyou862@gmail.com", "m8wjLN3P"));
            email.setSSLOnConnect(true);
            email.setFrom("hairforyou862@gmail.com");
            email.addTo(recipientField.getText());
            email.setSubject(subjectField.getText());
            email.setMsg(messageArea.getText());

            // Send email
            email.send();

            // Show success dialog
            JOptionPane.showMessageDialog(this, "Email sent successfully!");
        } catch (Exception ex) {
            // Show error dialog
            JOptionPane.showMessageDialog(this, "Error sending email: " + ex.getMessage());
        }
    }
}