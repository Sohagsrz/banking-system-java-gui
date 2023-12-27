package Controller;

import javax.swing.JOptionPane;

import Models.Account;
import Services.DataManage;
import Views.AccountFrame;
import Views.LoginFrame;
import Views.RegistrationFrame;

public class StartApp {
      public LoginFrame login;
      public RegistrationFrame regFrame;
      public AccountFrame accountFrame;
      public DataManage dataUser;
      public Account myaccount;
      public boolean isLogged = false;
      
      public StartApp() {
        System.out.println("STARTAPP");
        
        myaccount= new Account();
        dataUser = new DataManage();

        //FRAMES
        accountFrame = new AccountFrame(this);
        regFrame = new RegistrationFrame(this);
        login = new LoginFrame(this);
        
        login.setVisible(true); 
    }
    public void showAccount(){
        accountFrame = new AccountFrame(this);
        
        login.setVisible(false);
        regFrame.setVisible(false);
        accountFrame.setVisible(true);
    }
    public void message(String title, String msg){
        JOptionPane.showMessageDialog(null,msg, title,JOptionPane.ERROR_MESSAGE);
    }

    public void sucessMessage(String msg){
        JOptionPane.showMessageDialog(null,msg);
    }
}
