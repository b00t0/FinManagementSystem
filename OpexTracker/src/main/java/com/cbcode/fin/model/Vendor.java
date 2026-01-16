package com.cbcode.fin.model;

public class Vendor {
    private String name;

    public Vendor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
