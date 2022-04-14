package com.example.sql;

public class Category {
    private String category_name;
    private int category_code;

    public Category(String category_name, int category_code) {
        this.category_name = category_name;
        this.category_code = category_code;
    }

    public String getCategory_name() {
        return category_name;
    }
    public int getCategory_code() {
        return category_code;
    }
}
