/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem;
import java.util.ArrayList;
/**
 *
 * @author Abisan Poothapillai
 */
public class Bank {
    private static ArrayList<AutomatedTellerMachine> atm = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();

    /**
     * To add a new user to the user name
     * @param userName the name of the user 
     * @param pin the pin of the user
     */
    public static void addUser(String userName, String pin) {
        users.add(new User(userName, pin));
    }
    
    public static ArrayList<User> getUsers() {
        return users;
    }
}

