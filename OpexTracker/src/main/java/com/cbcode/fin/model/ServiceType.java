package com.cbcode.fin.model;

public record ServiceType(String name) {
    @Override
    public String toString() {
        return name;
    }
}