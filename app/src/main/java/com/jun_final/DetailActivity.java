package com.jun_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jun_final.model.Customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView show_list;
    RadioGroup radio_group;
    RadioButton radio_family, radio_balance, radio_open_date, radio_acc_num;
    Button btn_back;
    List<Customer> accountList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initialize();
    }

    private void initialize() {
        btn_back = findViewById(R.id.btn_back);
        show_list = findViewById(R.id.show_list);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("intentExtra");
        Serializable bundleListOfAccount = bundle.getSerializable("bundleExtra");
        accountList = (ArrayList<Customer>) bundleListOfAccount;
        showAccount(accountList);

        radio_group = findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radio_group, int checked) {
                switch (checked) {
                    case R.id.radio_family:
                        displaySortByFamily();
                        break;
                    case R.id.radio_balance:
                        displaySortByBalance();
                        break;
                    case R.id.radio_open_date:
                        d:
                        displaySortByOpenDate();
                        break;
                    case R.id.radio_acc_num:
                        displaySortByAccountNumber();
                        break;
                }
            }
        });
    }

    private void showAccount(List<Customer> accountList) {
        String str = "";
        for (Customer a : accountList) {
            str = str + a + "\n";
        }
        show_list.setText(str);
    }

    @Override
    public void onClick(View v) {
    }

    private void displaySortByFamily() {
        ArrayList<Customer> temp = (ArrayList<Customer>) accountList;
        Collections.sort(temp, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getFamily().compareTo(o2.getFamily());
            }
        });
        String str = "";
        for (Customer oneCustomer : temp) {
            str = str + oneCustomer + "\n";
        }
        show_list.setText(str);
    }

    public void displaySortByBalance() {
        ArrayList<Customer> temp = (ArrayList<Customer>) accountList;
        Collections.sort(temp, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1.getAccount().getBalance() < o2.getAccount().getBalance()) {
                    return 1;
                } else if (o1.getAccount().getBalance() > o2.getAccount().getBalance()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        String str = "";
        for (Customer oneCustomer : temp) {
            str = str + oneCustomer + "\n";
        }
        show_list.setText(str);
    }

    public void displaySortByOpenDate() {
        ArrayList<Customer> dup = (ArrayList<Customer>) accountList;
        Collections.sort(dup, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getAccount().getDate().compareTo(o2.getAccount().getDate());
            }
        });
        String str = "";
        for (Customer oneCustomer : dup) {
            str = str + oneCustomer + "\n";
        }
        show_list.setText(str);
    }

    public void displaySortByAccountNumber() {
        ArrayList<Customer> dup = (ArrayList<Customer>) accountList;
        Collections.sort(dup, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return String.valueOf(o1.getAccount().getAccountNum()).compareTo(String.valueOf(o2.getAccount().getAccountNum()));
            }
        });
        String str = "";
        for (Customer oneCustomer : dup) {
            str = str + oneCustomer + "\n";
        }
        show_list.setText(str);
    }

    public void goBack() {
        finish();
    }
}