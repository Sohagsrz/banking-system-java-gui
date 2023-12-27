package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.AgentAccount;
import Models.PersonalAccount;
import Services.InputValidator;

public class CashOutPanel extends JPanel implements ActionListener {
    public JTextField phoneNumberField ,amountField;
    AccountFrame accObj;
    JButton sendBtn;
    CashOutPanel(AccountFrame accountFrame){
        this.setLayout(null);
        this.accObj = accountFrame;
        this.setBounds(205, 0, 600, 600);
        this.setBackground(Color.gray);
        this.makeDesign();
    }

    private void makeDesign() {
        //heading
        JLabel headingJLabel = new JLabel("Cash Out");
        headingJLabel.setBounds(200,10,200, 100);
        headingJLabel.setForeground(Color.BLACK);   
        headingJLabel.setFont(new Font("Arial", Font.BOLD , 30));
        this.add(headingJLabel);


        
        // input name
       JLabel nameLabel = new JLabel("Enter Agent Number");
        nameLabel.setBounds(220, 115, 150, 30);
        nameLabel.setForeground(Color.BLACK);   
        nameLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(nameLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.addKeyListener(new InputValidator(phoneNumberField));
        phoneNumberField.setBounds(200, 150, 180, 40);
        this.add(phoneNumberField);


        
       JLabel amountLabel = new JLabel("Enter Amount");
        amountLabel.setBounds(240, 190, 180, 30);
        amountLabel.setForeground(Color.BLACK);   
        amountLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(200, 220, 180, 40);
        //only numbers
        amountField.addKeyListener(new InputValidator(amountField));
        this.add(amountField);

         sendBtn = new JButton("Cash Out");
        sendBtn.setBounds(200, 270, 180, 40);
        sendBtn.setFont(new Font("Arial", Font.PLAIN , 16));
        sendBtn.setBackground(new Color(20,134,198));
        sendBtn.setForeground(Color.white);
        sendBtn.addActionListener(this);
        

        this.add(sendBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg="";
        boolean isContinue= true;

      
         if(phoneNumberField.getText().isEmpty()){
            isContinue = false;
            msg +="Empty Phone Number\n";
        }
         if(amountField.getText().isEmpty()){
            isContinue = false;
            msg +="Empty PIN\n";
        }
       
        
         if(((PersonalAccount)this.accObj.main.myaccount) == null
         ||
         ((PersonalAccount)this.accObj.main.myaccount).getAccountBalance() < Double.parseDouble(amountField.getText())
         
         ){
            
            isContinue = false;
            msg +="Dont have enough balance!\n"; 
        }
         if(this.accObj.main.myaccount.getPhoneNumber().equals(phoneNumberField.getText())){

            
            isContinue = false;
            msg +="You cant send money yourself!\n"; 
        }
        
         if(!this.accObj.main.dataUser.accountExists(phoneNumberField.getText())){
            
            isContinue = false;
            msg +="Sender Account not found\n"; 
        }

        if(isContinue){
            if(this.accObj.main.dataUser.getAccountByPhone(phoneNumberField.getText()) instanceof AgentAccount){


                String result = (String)JOptionPane.showInputDialog(
               null,
               "Your Pin", 
               "Confirm The Request",            
               JOptionPane.PLAIN_MESSAGE,
               null,            
               null, 
               ""
            );
            if(result != null && result.length() > 0){
                 
                if(this.accObj.main.myaccount.getPin().equals(result)){
                    System.out.println("Pin matched");
                    double balance =( (PersonalAccount) this.accObj.main.myaccount).getAccountBalance();
                    balance= balance- Double.parseDouble(amountField.getText());
                   ((PersonalAccount) this.accObj.main.myaccount).setBalance(balance);
                   this.accObj.dispose();
                   
                   this.accObj.main.showAccount();

                }else{
                    this.accObj.main.message("Wrong Pin", "You entered wrong PIN!");
                    
                
                } 
            }else {

                    this.accObj.main.message("Empty Pin", "You not entered PIN!");

            }
            } else{
                   this.accObj.main.message("Invalid Agent Account", "You entered wrong AGENt Number!");
                 
            }
       
      
        }else{
            this.accObj.main.message("Error", msg);
        }

    
    }
}
