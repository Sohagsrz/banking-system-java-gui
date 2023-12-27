package Models;

public class AgentAccount extends Account implements IAccount{
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
