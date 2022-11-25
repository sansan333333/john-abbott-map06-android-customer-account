package com.jun_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jun_final.model.Account;
import com.jun_final.model.Customer;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Customer> accountList = new ArrayList<>();
    EditText editText_account_number, editText_open_date, editText_balance, editText_name, editText_family, editText_phone, editText_sin;
    Button btn_add, btn_find, btn_remove, btn_update, btn_clear, btn_showAll;
    ArrayList<Customer> findAccountList = new ArrayList<>();
    TextView show_find;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        editText_account_number = findViewById(R.id.editText_account_number);
        editText_open_date = findViewById(R.id.editText_open_date);
        editText_balance = findViewById(R.id.editText_balance);
        editText_name = findViewById(R.id.editText_name);
        editText_family = findViewById(R.id.editText_family);
        editText_phone = findViewById(R.id.editText_phone);
        editText_sin = findViewById(R.id.editText_sin);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_find = findViewById(R.id.btn_find);
        btn_find.setOnClickListener(this);
        btn_remove = findViewById(R.id.btn_remove);
        btn_remove.setOnClickListener(this);
        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
        btn_showAll = findViewById(R.id.btn_showAll);
        btn_showAll.setOnClickListener(this);

        show_find = findViewById(R.id.show_find);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                addCustomer();
                break;
            case R.id.btn_find:
                findCustomer();
                break;
            case R.id.btn_remove:
                removeCustomer();
                break;
            case R.id.btn_update:
                updateCustomer();
                break;
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_showAll:
                showAll();
                break;
        }
    }

    private void showAll() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleExtra", accountList);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("intentExtra", bundle);
        startActivity(intent);
        return;
    }

    private void updateCustomer() {
        Customer accountUpdate = openAccount();
        boolean flag = false;
        for (Customer account : accountList) {
            if (account.getAccount().getAccountNum() == accountUpdate.getAccount().getAccountNum()) {
                processRemove(account);
                accountList.add(accountUpdate);
                flag = true;
                Toast.makeText(this, accountUpdate.getAccount().getAccountNum() + " has been updated", Toast.LENGTH_LONG).show();
                clear();
            } else
                Toast.makeText(this, "account not updated!", Toast.LENGTH_LONG).show();
        }
    }

    private void removeCustomer() {
        Customer accountRemove = openAccount();
        processRemove(accountRemove);
        Toast.makeText(this, "account Number " + accountRemove.getAccount().getAccountNum() + "is deleted successfully!"
                + accountList.size(), Toast.LENGTH_LONG).show();
        return;
    }

    private void processRemove(Customer account) {
        boolean find = false;
        Iterator<Customer> iterator = accountList.iterator();
        while (!find && iterator.hasNext()) {
            Customer account1 = iterator.next();
            if (account1.getAccount().getAccountNum() == account.getAccount().getAccountNum()) {
                iterator.remove();
                find = true;
                Toast.makeText(this, "found", Toast.LENGTH_LONG).show();
                clear();
            }
        }
    }

    private void findCustomer() {
        Customer accountFind = openAccount();
        for (Customer a : accountList)
            if (a.getAccount().getAccountNum() == accountFind.getAccount().getAccountNum()) {
                findAccountList.add(accountFind);
                show_find.setText(accountFind.toString());
                clear();
                Toast.makeText(this, "account number " + accountFind.getAccount().getAccountNum() + " found", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "account not find!", Toast.LENGTH_LONG).show();
            }
    }

    public Customer openAccount() {
        int accountNumber = Integer.parseInt(editText_account_number.getText().toString());
        String openDate = editText_open_date.getText().toString();
        int balance = Integer.parseInt(editText_balance.getText().toString());
        String name = editText_name.getText().toString();
        String family = editText_family.getText().toString();
        int phone = Integer.parseInt(editText_phone.getText().toString());
        int sin = Integer.parseInt(editText_sin.getText().toString());

        Account newAccount = new Account(name, family, phone, sin, accountNumber, openDate, balance);
        Customer newCustomer = new Customer(name, family, phone, sin, newAccount);
        return newCustomer;
    }

    private void addCustomer() {
        Customer newAccount = openAccount();
        boolean flag = false;
        for (Customer account : accountList) {
            if (account.getAccount().getAccountNum() == (newAccount.getAccount().getAccountNum())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            Toast.makeText(this, "customer already exists!", Toast.LENGTH_LONG).show();
        } else {
            accountList.add(newAccount);
            Toast.makeText(this, newAccount.getName() + "account added", Toast.LENGTH_LONG).show();
        }
        clear();
    }

    private void clear() {
        editText_account_number.setText(null);
        editText_open_date.setText(null);
        editText_balance.setText(null);
        editText_name.setText(null);
        editText_family.setText(null);
        editText_phone.setText(null);
        editText_sin.setText(null);
    }
}