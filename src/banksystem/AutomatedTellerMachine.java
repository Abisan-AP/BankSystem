/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem;
import java.util.Scanner;
/**
 *
 * @author Abisan Poothapillai
 */
public class AutomatedTellerMachine {
    private String atmId;
    private User user;
    private Account account;
    private static int nextId = 1;

    
    public AutomatedTellerMachine() {
        this.atmId = String.format("%06d", nextId++);
        this.user = null;
        this.account = null;
    }
    
    public AutomatedTellerMachine(String atmId) {
        this.atmId = atmId;
        this.user = null;
        this.account = null;
    }
    
    public AutomatedTellerMachine(AutomatedTellerMachine automatedTellerMachine) {
        this.atmId = automatedTellerMachine.atmId;
        this.user = new User(automatedTellerMachine.user);
        this.account = new Account(automatedTellerMachine.account);
    }
    
    /**
     * The pipeline of the ATM if someone wants to use it
     */
    public void pipeline() {
        printWelcome();
        readUserId();
        if (user == null)
            System.exit(1);     // cannot find the user
        if (!inputPin())
            System.exit(2);     // wrong password
        
        chooseAccount();
        do {
            int oper = chooseOperation();
            switch (oper) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                default:
                    displayBalance();
            }
        } while (doesContinue());
        
        printGoodBye();
    }
    
    /**
     * To print a welcome message at the very beginning 
     */
    public void printWelcome() {
        System.out.println("Welcome to use our ATM");
    }
    
    /**
     * To ask the user to input the user id, if the id exists, then return
     * the user object, else will shutdown the entire process.
     */
    public void readUserId() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please enter your user ID");
        String inputId = console.next();
        
        for (int i = 0; i < Bank.getUsers().size(); i++)
            if (inputId.equals(Bank.getUsers().get(i).getUserId())) {
                user = Bank.getUsers().get(i);
                return;
            }
        user = null;
    }
    
    /**
     * To ask the user to input the password. The user has three times to give 
     * the right password, if the user failed 3 times, then the process will be shutdown.
     * @return if the pin is correct
     */
    public boolean inputPin() {
        Scanner console = new Scanner(System.in);
        int maxTry = 3;
        
        for (int i = 0; i < maxTry; i++) {
            System.out.println("Please enter your user pin");
            String password = console.next();
            if (user.getPassword().equals(password))
                return true;
            System.out.println("Your pin is wrong");
        }
        System.out.println("You inputed pin wrong for 3 times");
        return false;
    }
    
    /**
     * To ask the user to choose the account, could be:
     * 1. checking account
     * 2. saving account
     */
    public void chooseAccount() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please choose the account you want to operate with" 
                + "\n\t1. Checking account" 
                + "\n\t2. Saving account");
        int accountChoice = console.nextInt();
        
        account = accountChoice == 1 ? user.getCheckingAccount() : user.getSavingAccount();
    }
    
    /**
     * To ask the user to choose the operation, could be:
     * 1. withdraw
     * 2. deposit
     * 3. display balance
     * @return the operation the user select
     */
    public int chooseOperation() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please choose the operation" 
                + "\n\t1. Withdraw" 
                + "\n\t2. Deposit" 
                + "\n\t3. Diplay balance");
        int operation = console.nextInt();
        
        return operation;
    }
    
    /**
     * To withdraw from the ATM
     * @return true if withdraw successfully
     */
    public boolean withdraw() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("How much do you want to withdraw? ");
        double amount = console.nextDouble();
        if (account.getBalance() < amount) {
            System.out.println("Sorry, you don't have enough balance.");
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdraw successfully");
        user.getHistory().add(new Record("withdraw", amount, atmId));

        return true;
    }
    
    /**
     * To deposit to the ATM  
     * @return true if deposit successfully
     */
    public boolean deposit() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("How much do you want to deposit? ");
        double amount = console.nextDouble();
        
        account.setBalance(account.getBalance() + amount);
        System.out.println("deposit successfully");
        user.getHistory().add(new Record("deposit", amount, atmId));
        
        return true;
    }
    
    /**
     * To display the balance of the account.
     */
    public void displayBalance() {
        System.out.printf("Your current balance is $%.2f\n", account.getBalance());
    }
    
    /**
     * Asks the user if he/ she wants to do another operation
     * @return true if the user if he/ she wants to do another operation
     */
    public boolean doesContinue() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Do you want to do another operation? ");
        System.out.println("0, No ");
        System.out.println("1, Yes ");
        int answer = console.nextInt();     // 0, 1
        
        return answer == 1;
    }
            
    /**
     * To print a goodbye message
     */
    public void printGoodBye() {
        System.out.println("Thank you for using our ATM. Goodbye");
    }
    
    public boolean equals(AutomatedTellerMachine automatedTellerMachine) {
        return this.atmId.equals(automatedTellerMachine.atmId);
    }
    
    @Override
    public String toString() {
        return String.format("%-10s: %s", "ATM ID", atmId);
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}