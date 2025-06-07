package com.codingame.game;

import com.codingame.game.model.Inventory;
import com.codingame.game.model.Item;
import com.codingame.gameengine.core.AbstractMultiplayerPlayer;

// Uncomment the line below and comment the line under it to create a Solo Game
// public class Player extends AbstractSoloPlayer {
public class Player extends AbstractMultiplayerPlayer {
    private final Inventory inventory = new Inventory();

    @Override
    public int getExpectedOutputLines() {
        // Returns the number of expected lines of outputs for a player
        return 1;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean pickUp(Item item) {
        return inventory.pickUp(item);
    }

    public boolean drop(Item item) {
        return inventory.drop(item);
    }

    public void modifyItem(Item item, int weight, int value, int quality) {
        inventory.modify(item, weight, value, quality);
    }

    public int sell(Item item) {
        return inventory.sell(item);
    }

    public int getGold() {
        return inventory.getGold();
    }
}
