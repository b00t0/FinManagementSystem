package com.cbcode.fin.model;

public class ServiceType {
    private String name;

    public ServiceType(String name) {
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
