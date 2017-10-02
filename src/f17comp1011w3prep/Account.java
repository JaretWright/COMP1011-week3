package f17comp1011w3prep;

import java.io.Serializable;

/**
 *
 * @author JWright
 */
public class Account implements Serializable{
    private int account;
    private String firstName, lastName;
    private double balance;
    
    public Account()
    {
        this(0, "", "", 0); //calls teh 4 argument constructor
    }
    
    public Account(int account, String first, String last, double balance)
    {
        this.account=account;
        firstName = first;
        lastName = last;
        this.balance=balance;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public String toString()
    {
        return String.format("%d %s %s with balance: $%.2f", this.account, this.firstName, this.lastName, this.balance);
    }
    
}

