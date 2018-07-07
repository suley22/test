package com.tato.expenses.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tato.expenses.R;

public class parameter_config_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameter_config);

        Button btnSaveConfig = findViewById(R.id.parameter_config_btn_save);
        btnSaveConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtIncome = findViewById(R.id.parameter_config_txt_income);
                String income = txtIncome.getText().toString();

                Toast.makeText(parameter_config_activity.this, income, Toast.LENGTH_SHORT).show();
                Intent viewMainWithExtra = new Intent(parameter_config_activity.this,main_activity.class);
                viewMainWithExtra.putExtra(ExtraKeys.SET_INCOME_RESPONSE,income);
                setResult(RESULT_OK,viewMainWithExtra);
                finish();
            }
        });
    }
}
