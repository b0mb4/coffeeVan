package com.example.mycoffee.entities;

public class Coffee {

    private int coffeeId;

    private int physicCondition;

    private int coffeeVolume;

    private int coffeeCost;

    private int vanId;

    private double sortValue;

    public int getCoffeeId() {
        return coffeeId;
    }

    public Coffee setCoffeeId(int coffeeId) {
        this.coffeeId = coffeeId;
        return this;
    }

    public int getPhysicCondition() {
        return physicCondition;
    }

    public Coffee setPhysicCondition(int physicCondition) {
        this.physicCondition = physicCondition;
        return this;
    }

    public int getCoffeeVolume() {
        return coffeeVolume;
    }

    public Coffee setCoffeeVolume(int coffeeVolume) {
        this.coffeeVolume = coffeeVolume;
        return this;
    }

    public int getCoffeeCost() {
        return coffeeCost;
    }

    public Coffee setCoffeeCost(int coffeeCost) {
        this.coffeeCost = coffeeCost;
        return this;
    }

    public int getVanId() {
        return vanId;
    }

    public Coffee setVanId(int vanId) {
        this.vanId = vanId;
        return this;
    }

    public double getSortValue() {
        return sortValue;
    }

    public Coffee setSortValue(double sortValue) {
        this.sortValue = sortValue;
        return this;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "coffeeId=" + coffeeId +
                ", physicCondition=" + physicCondition +
                ", coffeeVolume=" + coffeeVolume +
                ", coffeeCost=" + coffeeCost +
                ", vanId=" + vanId +
                '}';
    }
}
