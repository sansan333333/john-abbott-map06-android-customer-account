package com.jun_final.model;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable, Comparable<Customer> {
    String name;
    String family;
    int phone;
    int sin;
    Account account;

    public Customer(String name, String family, int phone, int sin) {
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.sin = sin;
    }

    public Customer(String name, String family, int phone, int sin, Account account) {
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.sin = sin;
        this.account = account;
    }

    @Override
    public String toString() {
        return "name:'" + name + '\'' +
                ", family: " + family + '\'' +
                ", phone: " + phone +
                ", sin: " + sin +
                '\n';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getSin() {
        return sin;
    }

    public void setSin(int sin) {
        this.sin = sin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phone == customer.phone && sin == customer.sin && Objects.equals(name, customer.name) && Objects.equals(family, customer.family) && Objects.equals(account, customer.account);
    }

    @Override
    public int compareTo(Customer o) {
        if (this.family.charAt(0) < o.family.charAt(0)) {
            return 1;
        } else if (this.family.charAt(0) > o.family.charAt(0)) {
            return -1;
        } else {
            return 0;
        }
    }
}