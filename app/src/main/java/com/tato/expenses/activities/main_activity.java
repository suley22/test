package com.tato.expenses.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tato.expenses.R;
import com.tato.expenses.adapter.Item_list_expense_view_adapter;
import com.tato.expenses.model.expense_model;
import com.tato.expenses.model.parameter_config_model;

import java.util.ArrayList;
import java.util.List;


public class main_activity extends AppCompatActivity {

    private static final int SET_INCOME_REQUEST = 1;
    private static final int NEW_EXPENSE_REQUEST = 2;
    private static final parameter_config_model Config = new parameter_config_model();

    private TextView TxtViewIncome;
    private FloatingActionButton btnAddItemExpenseList;
    private Button btnConfig;
    private ListView listView;
    Item_list_expense_view_adapter expenseListEntityAdapter;
    private List<expense_model> expenseListEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Find element view
        btnConfig = findViewById(R.id.main_btnConfig);
        btnAddItemExpenseList = (FloatingActionButton) findViewById(R.id.main_btn_add_item_expense_list);
        listView = (ListView) findViewById(R.id.main_expenseList);

        expenseListEntity = new ArrayList<>();
        expenseListEntityAdapter = new Item_list_expense_view_adapter(main_activity.this, R.layout.item_list_expense_view, expenseListEntity);
        listView.setAdapter(expenseListEntityAdapter);

        //Event onclick btnConfig
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIncome();
            }
        });
        //Event onclick btnAddItemExpenseList
        btnAddItemExpenseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent view_new_expense = new Intent(main_activity.this, new_expense_activity.class);
                startActivityForResult(view_new_expense,NEW_EXPENSE_REQUEST);
            }
        });

        registerIncome("26000");
    }

    private void setIncome(){
        Intent viewParameterConfig = new Intent(main_activity.this,parameter_config_activity.class);
        startActivityForResult(viewParameterConfig,SET_INCOME_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SET_INCOME_REQUEST:
                if(resultCode == RESULT_OK){
                    String income = data.getStringExtra(ExtraKeys.SET_INCOME_RESPONSE);
                    registerIncome(income);
                }else{
                    Toast.makeText(main_activity.this, "Ocurrio un problema al setear ingreso.", Toast.LENGTH_SHORT).show();
                }
                break;
            case NEW_EXPENSE_REQUEST:
                if(resultCode == RESULT_OK){
                    expense_model expense = (expense_model) data.getSerializableExtra(ExtraKeys.NEW_EXPENSE_RESPONSE);
                    if(expense != null)
                    {
                        //calculate current income
                        int currentIncome = Config.getIncome() - expense.getExpense();

                        registerIncome(String.valueOf(currentIncome));

                        // this line adds the data of your expense model and puts in your entity list
                        expenseListEntity.add(expense);
                        // next thing you have to do is check if your adapter has changed
                        expenseListEntityAdapter.notifyDataSetChanged();
                    }

                }else{
                    Toast.makeText(main_activity.this, "Ocurrio un problema al setear ingreso.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    public void registerIncome(String pIncome){
        if(Config!=null)
        {
            Config.setIncome(Integer.parseInt(pIncome));
        }

        TextView viewIncome = (TextView) findViewById(R.id.main_txt_view_income);
        if(viewIncome != null)
        {
            viewIncome.setText(pIncome);
        }
    }
}
