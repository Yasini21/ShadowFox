import java.util.*;

public class bankAccount {
    private String accountHolder;
    private double balance;
    private List<transaction> transactions;
    public bankAccount(String accountHolder,double balance){
        this.accountHolder=accountHolder;
        this.balance=balance;
        this.transactions=new ArrayList<>();

    }
    public void deposit(double amount){
        if(amount<=0){
            throw new IllegalArgumentException("Depositing amount should be positive.");
        }
        balance+=amount;
        transactions.add(new transaction("Deposite",amount));

    }
    public void withdraw(double amount){
        if(amount<=0){
            throw new IllegalArgumentException("Amount cannot be negative or zero");
        }
        if(amount>balance){
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance-=amount;
        transactions.add(new transaction("Withdraw",amount));
    }
    public double getBalance() {
        return balance;
    }

    public List<transaction> getTransactionHistory() {
        return transactions;
    }
    
}
