package com.codingame.game.model;

/**
 * Represents an item that can be stored in a player's inventory.
 */
public class Item {
    private final String name;
    private int weight;
    private int value;
    private int quality;

    public Item(String name, int weight, int value, int quality) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public int getQuality() {
        return quality;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
