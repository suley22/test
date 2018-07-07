package com.tato.expenses.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tato.expenses.R;
import com.tato.expenses.model.expense_model;

import java.util.List;

public class Item_list_expense_view_adapter extends ArrayAdapter<expense_model> {

    private int layoutResource;

    public Item_list_expense_view_adapter(Context context, int layoutResource, List<expense_model> expenseListItem) {
        super(context, layoutResource, expenseListItem);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        expense_model expense = getItem(position);

        if (expense != null) {
            TextView txt_expense = (TextView) view.findViewById(R.id.item_list_expense_txt_expense);
            TextView txt_description = (TextView) view.findViewById(R.id.item_list_expense_txt_description);

            if (txt_expense != null) {
                String exp = String.valueOf(expense.getExpense());
                txt_expense.setText("$ " + exp);
            }
            if (txt_description != null) {
                String desc = expense.getDescription();
                txt_description.setText(desc);
            }
        }

        return view;
    }
}
