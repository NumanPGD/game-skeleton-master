package com.codingame.game;
import java.util.List;
import java.util.Random;

import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.google.inject.Inject;
import com.codingame.game.model.Item;

public class Referee extends AbstractReferee {
    // Uncomment the line below and comment the line under it to create a Solo Game
    // @Inject private SoloGameManager<Player> gameManager;
    @Inject private MultiplayerGameManager<Player> gameManager;
    @Inject private GraphicEntityModule graphicEntityModule;

    private final Random random = new Random();

    @Override
    public void init() {
        gameManager.setMaxTurns(5);
    }

    @Override
    public void gameTurn(int turn) {
        for (Player player : gameManager.getActivePlayers()) {
            Item item = new Item("Item" + turn,
                random.nextInt(3) + 1,
                random.nextInt(5) + 1,
                random.nextInt(5) + 1);
            player.setOfferedItem(item);
            player.sendInputLine(String.format("ITEM %s %d %d %d",
                item.getName(), item.getWeight(), item.getValue(), item.getQuality()));
            player.execute();
        }

        for (Player player : gameManager.getActivePlayers()) {
            try {
                List<String> outputs = player.getOutputs();
                if (!outputs.isEmpty()) {
                    String out = outputs.get(0).trim();
                    if ("PICK".equalsIgnoreCase(out)) {
                        Item it = player.getOfferedItem();
                        if (player.pickUp(it)) {
                            player.setScore(player.getScore() + it.getValue());
                        }
                    } else if ("PASS".equalsIgnoreCase(out)) {
                        // player chooses not to pick the offered item
                    }
                }
                player.setOfferedItem(null);
            } catch (TimeoutException e) {
                player.deactivate(String.format("$%d timeout!", player.getIndex()));
            }
        }

        if (turn == gameManager.getMaxTurns() - 1) {
            gameManager.endGame();
        }
    }
}
