package com.nagel.loanapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {

    List<String> items = new ArrayList();
    ArrayAdapter <String> adapter;
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Issue 14 - "R.id.foo" was referenced although "R.string.foo" was needed in PlanActivity.java
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        items.add(getResources().getString(R.string.per));
        items.add(getResources().getString(R.string.intr));
        items.add(getResources().getString(R.string.rep));
        items.add(getResources().getString(R.string.outs));

        grid = findViewById(R.id.grid);
        //Issue 19 - adapter was falsely set in PlanActivity.java
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        grid.setAdapter(adapter);
        for (int n = 1; n <= Loan.getInstance().getPeriods(); ++n)
        {
            items.add("" + n);
            items.add(String.format("%1.2f", Loan.getInstance().interest(n)));
            items.add(String.format("%1.2f", Loan.getInstance().repayment(n)));
            items.add(String.format("%1.2f", Math.abs(Loan.getInstance().outstanding(n))));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
