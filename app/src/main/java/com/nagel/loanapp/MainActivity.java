package com.nagel.loanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Issue 18 - EditText and Button were private. Changed it to public in MainActivity.java
    public EditText txtCost, txtLoan, txtRate, txtPaym, txtYear, txtTerm;
    public Button btnAmortisation, btnCalculate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Issue 5 - All EditText's were missing (in MainActivity.java) the
        // reference to the EditText in xml file (activity_main.xml)
        txtCost = findViewById(R.id.txtCost);
        txtLoan = findViewById(R.id.txtLoan);
        txtRate = findViewById(R.id.txtRate);
        txtPaym = findViewById(R.id.txtPaym);
        txtYear = findViewById(R.id.txtYear);
        txtTerm = findViewById(R.id.txtTerm);

        //Issue 6 - 2 buttons (in MainActivity.java) were referenced after they were needed
        // in xml file (activity_main.xml)
        btnAmortisation = findViewById(R.id.btnAmortisation);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);

        btnAmortisation.setOnClickListener(new View.OnClickListener() {
            //Issue 17 - intent was missing "mainActivity.this" in MainActivity.java
            @Override
            public void onClick(View v) {
                if (Loan.getInstance().getPeriods() > 0) {
                    Intent intent = new Intent(MainActivity.this, PlanActivity.class);
                    startActivity(intent);
                }
            }
        });
        disable(txtPaym);

    }

    //Issue 7 - "disable" method wasn't created in MainActivity.java but was used in onCreate method
    public void disable(EditText editText){
        editText.setEnabled(false);
    }

    public void onClear(View view) {
        //Issue 8 - setText was marked as "setTextTo" in MainActivity.java. Needs to be "setText".
        txtCost.setText("");
        txtLoan.setText("");
        txtRate.setText("");
        txtPaym.setText("");
        txtYear.setText("");
        txtTerm.setText("");
        Loan.getInstance().setPrincipal(1);
        Loan.getInstance().setInterestRate(1);
        Loan.getInstance().setPeriods(1);
        txtCost.requestFocus();
    }

    public void onCalculate(View view) {
        double cost = 0;
        double loan;
        double rate;
        //Issue 9 - "year" and "term" were declared as strings in MainActivity.java
        int year = 0;
        int term = 0;
        //Issue 10 - all Toast messages (5) were written falsely in MainActivity.java
        //Issue 11 - 1 case where getInstance() was written with a typo in MainActivity.java
        try {
            String text = txtCost.getText().toString().trim();
            if (text.length() > 0) {
                cost = Double.parseDouble(text);
                if (cost < 0) throw new Exception();
            }
        } catch (Exception ex) {
            Toast.makeText(this, getString(R.string.ilvc), Toast.LENGTH_LONG).show();
            txtCost.requestFocus();
            return;
        }
        try {
            loan = Double.parseDouble(txtLoan.getText().toString().trim());
            if (loan < 0) throw new Exception();
        } catch (Exception ex) {
            Toast.makeText(this, getString(R.string.ilvl), Toast.LENGTH_LONG).show();
            txtLoan.requestFocus();
            return;
        }
        //Issue 22 - "rate" limits were set falsely. Fixed those in MainActivity.java
        try {
            rate = Double.parseDouble(txtRate.getText().toString().trim());
            if (rate < 1 || rate >= 50) throw new Exception();
        } catch (Exception ex) {
            Toast.makeText(this, getString(R.string.iir), Toast.LENGTH_LONG).show();
            txtRate.requestFocus();
            return;
        }
        //Issue 12 - "year" and "term" were falsely converted into int in MainActivity.java
        //Issue 16 - Validation of "year" was incorrect in MainActivity.java. Fixed limits and "or" was needed.
        try {
            year = Integer.parseInt(txtYear.getText().toString());
            if (year < 1 || year > 60) throw new Exception();
        } catch (Exception ex) {
            Toast.makeText(this, getString(R.string.iny), Toast.LENGTH_LONG).show();
            txtYear.requestFocus();
            return;
        }
        //Issue 21 - "term" was including 0, but it should be 1 to 12 in MainActivity.java
        try {
            term = Integer.parseInt(txtTerm.getText().toString());
            if (term < 1 || term > 12) throw new Exception();
        } catch (Exception ex) {
            Toast.makeText(this, getString(R.string.ilvt), Toast.LENGTH_LONG).show();
            txtTerm.requestFocus();
            return;
        }
        Loan.getInstance().setPrincipal(loan + cost);
        Loan.getInstance().setInterestRate(rate / 100 / term);
        Loan.getInstance().setPeriods(year * term);
        txtPaym.setText(String.format("%1.2f", Loan.getInstance().payment()));
    }

    public void onAmort(View view) {
        if (Loan.getInstance().getPeriods() > 0) {
            Intent intent = new Intent(this, PlanActivity.class);
            startActivity(intent);
        }
    }
}

