package com.example.firstapplication;

public class Item {
    private String name;
    private int quantity;
    private double cost;
    private String description;
    private Boolean frozen;
    private String location;

    public Item(String name, int quantity, double cost, String description, Boolean frozen, String location) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
        this.frozen = frozen;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public String getLocation() {
        return location;
    }
}
