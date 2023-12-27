package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.StartApp;
import Models.AgentAccount;
import Models.PersonalAccount;
import Services.TitleManager;
 

public class AccountFrame extends JFrame implements MouseListener {
    public JPanel panel,panelMenu;
    SendMoneyPanel sendMoney;
    CashOutPanel cashPanel;
    PaymentPanel paymentPanel;
    CashInPanel cashInPanel;
    WithdrawPanel withdrawPanel;
    JLabel sendMoneyLabel, cashOutLable,paymentLable;
    StartApp main;

    public AccountFrame(StartApp startApp){ 
        super(TitleManager.titleBuild("My Account"));
        this.main = startApp;
        this.setSize(800 , 600);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.makeDesign();
     }

    private void makeDesign() {
        panel = new JPanel();
        panelMenu = new JPanel();
        // user icon
         ImageIcon iconLogo = new ImageIcon("D:/java/com/MobileBanking/imgs/user.png");
        Image image = iconLogo.getImage(); // transform it 
        Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        ImageIcon newImageIcon = new ImageIcon(newimg);
        JLabel myUserIcon = new JLabel(newImageIcon);
        myUserIcon.setBounds(50,20, 70, 70);
        panel.add(myUserIcon);

        // user name
 
        JLabel accountNameLabel = new JLabel(this.main.myaccount.getName());
        accountNameLabel.setBounds(30,75, 200, 50);
        accountNameLabel.setForeground(Color.white);
        accountNameLabel.setFont(new Font("Arial", Font.BOLD , 16));
        panel.add(accountNameLabel);

        //account type
        String accType="";
        String balance ="";
        String sendMoneyText ="";
        
        String cashOutText ="";
        if(this.main.myaccount instanceof PersonalAccount){
            accType="(Personal)";
            sendMoneyText =" Send Money";
            cashOutText= " Cash Out";
            balance = ((PersonalAccount) this.main.myaccount).getBalance() + "";
        }
         if(this.main.myaccount instanceof AgentAccount){
            accType="(Agent)";
            sendMoneyText =" Cash In";
            cashOutText=" Withdraw";
            balance = ((AgentAccount) this.main.myaccount).getBalance() + "";
        }
        JLabel accountTypeLabel = new JLabel(accType);
        accountTypeLabel.setBounds(55,95, 200, 50);
        accountTypeLabel.setForeground(Color.white);
        accountTypeLabel.setFont(new Font("Arial", Font.PLAIN , 12));
        panel.add(accountTypeLabel);
        // balance  
        JLabel accountBalanceLabel = new JLabel(balance);
        accountBalanceLabel.setBounds(50,135, 70, 30);
        accountBalanceLabel.setForeground(Color.black);      
        accountBalanceLabel.setBackground(Color.white);
        accountBalanceLabel.setOpaque(true);
        accountBalanceLabel.setFont(new Font("Arial", Font.BOLD , 17));
        panel.add(accountBalanceLabel);


        // menu 
        sendMoneyLabel = new JLabel(sendMoneyText);
        sendMoneyLabel.setBounds(0,50,200 ,50);
        sendMoneyLabel.setForeground(Color.white);      
        sendMoneyLabel.setBackground(new Color(20,134,198));
        sendMoneyLabel.setOpaque(true);
        sendMoneyLabel.setFont(new Font("Arial", Font.BOLD , 17));
        sendMoneyLabel.addMouseListener(this);
        panelMenu.add(sendMoneyLabel);

         cashOutLable = new JLabel(cashOutText);
        cashOutLable.setBounds(0,102,200 ,50);
        cashOutLable.setForeground(Color.white);      
        cashOutLable.setBackground(Color.gray);
        cashOutLable.setOpaque(true);
        cashOutLable.setFont(new Font("Arial", Font.BOLD , 17));
        cashOutLable.addMouseListener(this);
        panelMenu.add(cashOutLable);

        

        JLabel logoutLabel = new JLabel(" Log Out");
        // logoutLabel.setBounds(0,258,200 ,50);
        
        logoutLabel.setBounds(0,154,200 ,50);
        logoutLabel.setForeground(Color.white);      
        logoutLabel.setBackground(Color.gray);
        logoutLabel.setOpaque(true);
        logoutLabel.addMouseListener(this);
        logoutLabel.setFont(new Font("Arial", Font.BOLD , 17));
        panelMenu.add(logoutLabel);



        JLabel devloJLabel = new JLabel(TitleManager.footerText);
        devloJLabel.setBounds(5,390,200,50);
        devloJLabel.setForeground(Color.gray);
      
        devloJLabel.setFont(new Font("Arial", Font.BOLD , 10));
      

        panelMenu.add(devloJLabel);


 
        panel.setBackground(new Color(20,134,198));
        // panel.setLayout(null);
        panel.setLayout(null);
        
        panel.setBounds(0,0,200,180);   
        
        //menu panel
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 135, 200, 450);
        panelMenu.setBackground(new Color(255,255,255));


        
         if(this.main.myaccount instanceof PersonalAccount){
            
         sendMoney = new SendMoneyPanel(this);
        this.add(sendMoney);
         cashPanel = new CashOutPanel(this);
        this.add(cashPanel);
        paymentPanel = new PaymentPanel(this);
        this.add(paymentPanel);
         }

        
         if(this.main.myaccount instanceof AgentAccount){
            
        cashInPanel = new CashInPanel(this);
        this.add(cashInPanel);

        withdrawPanel   = new WithdrawPanel(this);
        this.add(withdrawPanel);

         }

        //add the panel
        this.add(panel);
        this.add(panelMenu);
    }
 
    public void mouseClicked(MouseEvent e) {
        
        if(e.getComponent() instanceof JLabel){
        String cmd = ((JLabel) e.getComponent()).getText(); 
        if(cmd.equals(" Send Money")){
            
            this.setTitle(TitleManager.titleBuild("Send Money"));

            //menu color
            sendMoneyLabel.setForeground(Color.white);      
            sendMoneyLabel.setBackground(new Color(20,134,198));
            cashOutLable.setForeground(Color.white);      
            cashOutLable.setBackground(Color.gray); 

            sendMoney.setVisible(true);
            cashPanel.setVisible(false);
        }else if(cmd.equals(" Cash Out")){
            //coloe
            this.setTitle(TitleManager.titleBuild("Cash Out"));

            cashOutLable.setForeground(Color.white);      
            cashOutLable.setBackground(new Color(20,134,198));
            sendMoneyLabel.setForeground(Color.white);      
            sendMoneyLabel.setBackground(Color.gray); 

            cashPanel.setVisible(true);
            sendMoney.setVisible(false);  
        }
        else if(cmd.equals(" Cash In")){
            //coloe
            this.setTitle(TitleManager.titleBuild("Cash In"));
 
            sendMoneyLabel.setForeground(Color.white);      
            sendMoneyLabel.setBackground(new Color(20,134,198));
            cashOutLable.setForeground(Color.white);      
            cashOutLable.setBackground(Color.gray); 
            


            cashInPanel.setVisible(true);
            withdrawPanel.setVisible(false);  
  
        } 
        else if(cmd.equals(" Withdraw")){
            //coloe
            this.setTitle(TitleManager.titleBuild("Withdraw"));
 

            cashOutLable.setForeground(Color.white);      
            cashOutLable.setBackground(new Color(20,134,198));
            sendMoneyLabel.setForeground(Color.white);      
            sendMoneyLabel.setBackground(Color.gray); 

            cashInPanel.setVisible(false);
            withdrawPanel.setVisible(true);  
  
        } 
        else if(cmd.equals(" Log Out")){
            this.setVisible(false);
            this.main.login.setVisible(true);
        }
        System.out.println(cmd);


        }else{
 
        }

    }

    public void mousePressed(MouseEvent e) {
      
    }

    public void mouseReleased(MouseEvent e) {
         

    }

    public void mouseEntered(MouseEvent e) {
         

    }

    public void mouseExited(MouseEvent e) {
     
     }    
}
