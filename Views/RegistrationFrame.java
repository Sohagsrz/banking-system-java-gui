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
import Services.DataManage;
import Services.InputValidator;
import Services.TitleManager;

public class RegistrationFrame extends JFrame implements ActionListener{
    JLabel headingLabel, accountTypeLabel, phoneNumLabel, pinLabel, developeByLabel,
    nameLabel;
    JTextField phoneNumberField , pinTextField, nameTextField;
    JButton logiButton, regButton;
    JRadioButton personalRadioButton, agentRadioButton,merchantRadioButton;
    ButtonGroup accountTypeButtonGroup;
    StartApp main;

    
    public RegistrationFrame(StartApp startApp ){
        super(TitleManager.titleBuild("Registration"));
        this.main = startApp;
        this.setSize(400 , 600);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.makeDesign();
    }
 
    private void makeDesign() {
        // heading 
        headingLabel = new JLabel("Create an Account");
        headingLabel.setBounds(100, 10, 190, 30);
        headingLabel.setFont(new Font("Arial", Font.BOLD , 20));
        this.add(headingLabel);

        // label account type
        phoneNumLabel = new JLabel("Your Account Type");
        phoneNumLabel.setBounds(135, 60, 150, 25);
        phoneNumLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(phoneNumLabel);

        //radio buttons
        personalRadioButton = new JRadioButton("Personal");
        personalRadioButton.setBounds(70, 85, 90, 30);
        personalRadioButton.setFont(new Font("Arial", Font.BOLD , 14));
        this.add(personalRadioButton);
        
        
        agentRadioButton = new JRadioButton("Agent");
        agentRadioButton.setBounds(160, 85, 70, 30);
        agentRadioButton.setFont(new Font("Arial", Font.BOLD , 14));
        this.add(agentRadioButton);
         
        
        
        // button group
         accountTypeButtonGroup = new ButtonGroup();
        accountTypeButtonGroup.add(personalRadioButton);
        accountTypeButtonGroup.add(agentRadioButton);
        
        // input name
        nameLabel = new JLabel("Your Name");
        nameLabel.setBounds(150, 115, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(nameLabel);

        nameTextField = new JTextField("");
        nameTextField.setBounds(100, 150, 180, 40);
        this.add(nameTextField);


        // input phone
        phoneNumLabel = new JLabel("Your Phone Number");
        phoneNumLabel.setBounds(125, 190, 150, 30);
        phoneNumLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(phoneNumLabel);

        
        phoneNumberField = new JTextField("");
        phoneNumberField.setBounds(100, 220, 180, 40);
        //only number take
        phoneNumberField.addKeyListener(new InputValidator(phoneNumberField));
        this.add(phoneNumberField);

        pinLabel = new JLabel("Your PIN");
        pinLabel.setBounds(155, 260, 150, 30);
        pinLabel.setFont(new Font("Arial", Font.PLAIN , 16));
        this.add(pinLabel);

        
        pinTextField = new JTextField("");
        pinTextField.setBounds(100, 290, 180, 40);
        //number take only
        pinTextField.addKeyListener(new InputValidator(pinTextField));
        this.add(pinTextField);

 
        // buttons
        regButton = new JButton("Register");
        regButton.setBounds(120, 340, 140, 40);
        regButton.addActionListener(this);
        this.add(regButton);

        logiButton = new JButton("Login");
        logiButton.setBounds(140, 390, 100, 30);
        logiButton.addActionListener(this);
        this.add(logiButton);

    
        //footer text 
        developeByLabel = new JLabel(TitleManager.footerText);
        developeByLabel.setBounds(55, 530, 380 , 30);
        developeByLabel.setFont(new Font("Arial", Font.ITALIC , 15));
        this.add(developeByLabel);
    }
    
   
    
    public void actionPerformed(ActionEvent e) {
        String type = "";
      

        if(e.getActionCommand().equals("Login")){
            main.regFrame.setVisible(false);
            main.login.setVisible(true);
        }else if(e.getActionCommand().equals("Register")){
            
         String msg="";
        boolean isContinue= true;

        if(personalRadioButton.isSelected()){
            type="personal";
        }
        if(agentRadioButton.isSelected()){
            type="agent";
        }


        if(nameTextField.getText().isEmpty()){
            isContinue = false;
            msg +="Empty Name\n";
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

            if(!isAccount){
                //account creation
                this.main.dataUser.addAccount(nameTextField.getText(), phoneNumberField.getText(), pinTextField.getText(), type, 0.00);
                this.main.sucessMessage("Account Created Success Fully! Login to your account now");
                this.backToLogin();

            }else{

                //account exists
                this.main.message("Account not created", "This Phone number already used!");
            }
   

        }else{
            this.main.message("Error", msg);
        }
    }

    }
    public void backToLogin(){
        
            main.regFrame.setVisible(false);
            main.login.setVisible(true);
    }
}
