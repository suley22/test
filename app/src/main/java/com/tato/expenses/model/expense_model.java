package com.tato.expenses.model;

import java.io.Serializable;

public class expense_model implements Serializable {
    private int expense;
    private String description;

    public String getDescription() {
        return this.description;
    }
    public int getExpense() {
        return this.expense;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setExpense(int expense) {
        this.expense = expense;
    }

    public expense_model(int expense, String description) {
        this.expense = expense;
        this.description = description;
    }
}


