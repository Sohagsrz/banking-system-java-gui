package Views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentPanel extends JPanel {
    PaymentPanel(AccountFrame accountFrame){
        this.setLayout(null);
        this.setBounds(205, 0, 600, 600);
        this.setBackground(Color.gray);
        this.makeDesign();
    }

    private void makeDesign() {
        //heading
        JLabel headingJLabel = new JLabel("Payment");
        headingJLabel.setBounds(200,10,200, 100);
        headingJLabel.setForeground(Color.BLACK);   
        headingJLabel.setFont(new Font("Arial", Font.BOLD , 30));
        this.add(headingJLabel);


        
        // input name
       JLabel nameLabel = new JLabel("Merchant Number");
        nameLabel.setBounds(220, 115, 150, 30);
        nameLabel.setForeground(Color.BLACK);   
        nameLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(nameLabel);

       JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(200, 150, 180, 40);
        this.add(phoneNumberField);


        
       JLabel amountLabel = new JLabel("Amount to Send");
        amountLabel.setBounds(210, 190, 180, 30);
        amountLabel.setForeground(Color.BLACK);   
        amountLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(amountLabel);

       JTextField amountField = new JTextField();
        amountField.setBounds(200, 220, 180, 40);
        this.add(amountField);

        JButton sendBtn = new JButton("Make Payment");
        sendBtn.setBounds(200, 270, 180, 40);
        sendBtn.setFont(new Font("Arial", Font.PLAIN , 16));
        sendBtn.setBackground(new Color(20,134,198));
        sendBtn.setForeground(Color.white);

        this.add(sendBtn);

    }
}
