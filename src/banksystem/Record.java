/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem;
import java.util.Date;
/**
 *
 * @author Abisan Poothapillai
 */
public class Record {
    private Date date;
    private String operation;
    private double amount;
    private String atmId;

    public Record() {
        this.date = null;
        this.operation = null;
        this.amount = 0;
        this.atmId = null;
    }
    
    public Record(String operation, double amount, String atmId) {
        this.date = new Date();     // an object of current time
        this.operation = operation;
        this.amount = amount;
        this.atmId = atmId;
    }
    
    public Record(Record record) {
        this.date = record.date;
        this.operation = record.operation;
        this.amount = record.amount;
        this.atmId = record.atmId;
    }
    
    public boolean equals(Record record) {
        return this.date.equals(record.date) &&
                this.operation.equals(record.operation) &&
                this.amount == record.amount &&
                this.atmId.equals(record.atmId);
    }
    
    @Override
    public String toString() {
        String str = "";
        
        str += String.format("%-10s: %s\n", "Date", date);
        str += String.format("%-10s: %s\n", "Operation", operation);
        str += String.format("%-10s: $%.2f\n", "Amount", amount);
        str += String.format("%-10s: %s\n", "Atm Id", atmId);
        
        return str;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }
}