package com.cbcode.fin.model;

public record Vendor(String name) {
    @Override
    public String toString() {
        return name;
    }
}
