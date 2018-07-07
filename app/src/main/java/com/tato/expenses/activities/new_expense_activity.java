package com.tato.expenses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tato.expenses.R;
import com.tato.expenses.model.expense_model;

public class new_expense_activity extends AppCompatActivity {

    private Button btnAddNewExpense;
    private EditText txtExpense;
    private EditText txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_expense_activity);

        btnAddNewExpense = (Button)findViewById(R.id.new_expense_activity_btn_add_new_expense);
        txtExpense = (EditText) findViewById(R.id.new_expense_activity_txt_expense);
        txtDescription = (EditText) findViewById(R.id.new_expense_activity_txt_description);

        btnAddNewExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendAdd();
            }
        });
    }

    public void SendAdd(){
        String expense;
        String description;

        expense = txtExpense.getText().toString();
        description = txtDescription.getText().toString();

        if(expense.isEmpty() || description.isEmpty())
        {
            Toast.makeText(new_expense_activity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        expense_model expenseModel = new expense_model(Integer.parseInt(expense),description);
        Intent iMainActivity = new Intent(new_expense_activity.this,main_activity.class);
        iMainActivity.putExtra(ExtraKeys.NEW_EXPENSE_RESPONSE,expenseModel);
        setResult(RESULT_OK,iMainActivity);
        finish();
    }


}
