package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.StartApp;
import Models.Account;
import Models.AgentAccount;
import Models.PersonalAccount;
import Services.InputValidator;
import Services.TitleManager;

public class LoginFrame extends JFrame implements ActionListener{
    JLabel headingLabel, accountTypeLabel, phoneNumLabel, pinLabel, developeByLabel;
    JTextField phoneNumberField , pinTextField;
    JButton logiButton, regButton;
    JRadioButton personalRadioButton, agentRadioButton,merchantRadioButton;
    ButtonGroup accountTypeButtonGroup;
    StartApp main;
    public LoginFrame(StartApp start){
        super(TitleManager.titleBuild("Login"));
        this.main= start;

        this.setSize(400 , 500);
        this.setLayout(null);
        this.setResizable(false);

        this.makeDesign();
    }

    private void makeDesign() {
        // heading welcome
        headingLabel = new JLabel("Welcome");
        headingLabel.setBounds(125, 10, 150, 30);
        headingLabel.setFont(new Font("Arial", Font.BOLD , 30));
        this.add(headingLabel);

        // label phone number
        phoneNumLabel = new JLabel("Account Type");
        phoneNumLabel.setBounds(135, 60, 150, 25);
        phoneNumLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(phoneNumLabel);

        //radio buttons
        personalRadioButton = new JRadioButton("Personal");
        personalRadioButton.setBounds(100, 85, 90, 30);
        personalRadioButton.setFont(new Font("Arial", Font.BOLD , 14));
        this.add(personalRadioButton);
        
        agentRadioButton = new JRadioButton("Agent");
        agentRadioButton.setBounds(200, 85, 70, 30);
        agentRadioButton.setFont(new Font("Arial", Font.BOLD , 14));
        this.add(agentRadioButton);
        
        // button group
        ButtonGroup accountTypeButtonGroup = new ButtonGroup();
        accountTypeButtonGroup.add(personalRadioButton);
        accountTypeButtonGroup.add(agentRadioButton);
       
        
        // input phone
        phoneNumLabel = new JLabel("Phone Number");
        phoneNumLabel.setBounds(135, 115, 150, 30);
        phoneNumLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(phoneNumLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(100, 150, 180, 40);
        //take only numbers
        phoneNumberField.addKeyListener(new InputValidator(phoneNumberField));
        this.add(phoneNumberField);


        // input pin
        pinLabel = new JLabel("Pin Code");
        pinLabel.setBounds(155, 190, 150, 30);
        pinLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(pinLabel);

        
        pinTextField = new JTextField();
        pinTextField.setBounds(100, 220, 180, 40);
        //only numbers
        pinTextField.addKeyListener(new InputValidator(pinTextField));
        this.add(pinTextField);

        // buttons
        logiButton = new JButton("Login");
        logiButton.setBounds(120, 270, 140, 40);
        
        logiButton.addActionListener(this);
        this.add(logiButton);
        
        regButton = new JButton("Register");
        regButton.setBounds(140, 320, 100, 30);
        regButton.addActionListener(this);
        this.add(regButton);
        
        //footer text
        developeByLabel = new JLabel(TitleManager.footerText);
        developeByLabel.setBounds(55, 430, 380 , 30);
        developeByLabel.setFont(new Font("Arial", Font.ITALIC , 15));
        this.add(developeByLabel);

    }

    
    public void actionPerformed(ActionEvent e) {
       
        
        if(e.getActionCommand().equals("Register")){
            main.login.setVisible(false);
            main.regFrame.setVisible(true);
        }else if(e.getActionCommand().equals("Login")){
            // accountFrame
            this.loginAction();
        }
    }
        

    
    void loginAction(){
        String type = "";
        String msg="";
        boolean isContinue= true;

        if(personalRadioButton.isSelected()){
            type="personal";
        }
        if(agentRadioButton.isSelected()){
            type="agent";
        }

         if(phoneNumberField.getText().isEmpty()){
            isContinue = false;
            msg +="Empty Phone Number\n";
        }
         if(pinTextField.getText().isEmpty()){
            isContinue = false;
            msg +="Empty PIN\n";
        }
         if(type.isEmpty()){
            isContinue = false;
            msg +="Select Account Type\n";
        }
        if(isContinue){

            boolean isAccount = this.main.dataUser.accountExists(phoneNumberField.getText());

            if(isAccount){
                this.main.myaccount = this.main.dataUser.getAccountByPhone(phoneNumberField.getText());
              
                // System.out.println(this.main.myaccount.getPin());
                if(this.main.myaccount.getPin().equals(pinTextField.getText()) ){
                      if(
                        (this.main.myaccount instanceof PersonalAccount && type.equals("personal"))
                      || 
                      (this.main.myaccount instanceof AgentAccount && type.equals("agent"))
                      ){
                        this.main.isLogged= true;
                        goDashboard();
                      }else{
                         this.main.message("Account Type Error", "This Account Not found!");
                      }
                }else{
                    this.main.message("Pin Incorrect", "You Entered Wrong PIN");
                }
            }else{
                this.main.message("Account not found", "DOnt have any account with this phone number!");
            }
        }else{
            this.main.message("Error", msg);
        }

    }
    void goDashboard(){
        this.main.showAccount();
    }
}
