package com.codingame.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic inventory storing items with weight management.
 */
public class Inventory {
    private final List<Item> items = new ArrayList<>();
    /** Maximum weight this inventory can hold. */
    private final int maxWeight;
    private int gold;

    /**
     * Create an inventory with the specified weight capacity.
     *
     * @param maxWeight maximum weight allowed for all stored items
     */
    public Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Create an inventory with a default weight capacity of 20.
     */
    public Inventory() {
        this(20);
    }

    /**
     * Add an item to the inventory if it doesn't exceed the weight capacity.
     *
     * @param item item to add
     * @return {@code true} if the item was added, {@code false} otherwise
     */
    public boolean pickUp(Item item) {
        if (getCurrentWeight() + item.getWeight() > maxWeight) {
            return false;
        }
        items.add(item);
        return true;
    }

    /**
     * Remove an item from the inventory.
     *
     * @param item item to remove
     * @return {@code true} if the item was present
     */
    public boolean drop(Item item) {
        return items.remove(item);
    }

    /**
     * Modify an existing item.
     */
    public void modify(Item item, int weight, int value, int quality) {
        item.setWeight(weight);
        item.setValue(value);
        item.setQuality(quality);
    }

    /**
     * Sell an item and remove it from the inventory.
     */
    public int sell(Item item) {
        if (items.remove(item)) {
            gold += item.getValue();
            return item.getValue();
        }
        return 0;
    }

    /**
     * @return the current total weight of all items.
     */
    public int getCurrentWeight() {
        int total = 0;
        for (Item i : items) {
            total += i.getWeight();
        }
        return total;
    }

    public int getGold() {
        return gold;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}
