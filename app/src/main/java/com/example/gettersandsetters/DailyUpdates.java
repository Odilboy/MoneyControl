package com.example.gettersandsetters;

import java.util.ArrayList;

public class DailyUpdates {

    private String type;
    private String description;
    private String amount;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
