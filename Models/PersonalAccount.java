package Models;

public class PersonalAccount extends Account implements IAccount{
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
 
    public double getAccountBalance() {
        
        return this.balance;
    }
    
}
