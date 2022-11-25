package com.jun_final.model;

public class Account extends Customer {

    int accountNum;
    String date;
    int balance;

    public Account(String name, String family, int phone, int sin, int accountNum, String date, int balance) {
        super(name, family, phone, sin);
        this.accountNum = accountNum;
        this.date = date;
        this.balance = balance;
    }

    public Account(String name, String family, int phone, int sin, Account account, int accountNum, String date, int balance) {
        super(name, family, phone, sin, account);
        this.accountNum = accountNum;
        this.date = date;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "accountNum: " + accountNum +
                ", date: " + date +
                ", balance: " + balance +
                '\n';
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
