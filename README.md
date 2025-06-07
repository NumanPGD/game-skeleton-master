This project is the skeleton for the creation of a game using the Game Engine Toolkit of [CodinGame](https://codingame.com).

Check the documentation on the [github repository](https://github.com/CodinGame/codingame-sdk-doc).

## Note about the game turn implementation
There are 2 ways to implement your game turn according to the game you want to create. **The simultaneous mode** or the **Turn by Turn mode**.

### The simultaneous mode
It's a game mode where all players receive the game data and execute their actions in the same turn. (eg: Race, Pong, ...)

```java
for (Player player : gameManager.getActivePlayers()) {
    player.sendInputLine(input);
    player.execute();
}

for (Player player : gameManager.getActivePlayers()) {
    try {
        List<String> outputs = player.getOutputs();
        // Check validity of the player output and compute the new game state
    } catch (TimeoutException e) {
        player.deactivate(String.format("$%d timeout!", player.getIndex()));
    }
}

// Check if there is a win / lose situation and call gameManager.endGame(); when game is finished
```

### The Turn by Turn mode:
It's a game mode where only one player execute an action during a turn. (eg: TicTacToe, Chess)

```java
SkeletonPlayer player = gameManager.getPlayer(turn % playerCount);
player.sendInputLine(input);
player.execute();
try {
    List<String> outputs = player.getOutputs();
    // Check validity of the player output and compute the new game state
} catch (TimeoutException e) {
    player.deactivate(String.format("$%d timeout!", player.getIndex()));
    player.setScore(-1);
    gameManager.endGame();
}

// Check if there is a win / lose situation and call gameManager.endGame(); when game is finished
```

## Loading assets
Assets are expected to be placed in the `src/main/resources/view/assets` folder of your game's project.

You can then use the images in the texture cache with the Entity Module:
```java
entityManager.createSprite.setImage("background.jpg");
```

## Inventory system

The skeleton now provides a simple inventory that can store items. An `Item`
has a weight, value and quality. A `Player` owns an `Inventory` and exposes
convenience methods to pick up, drop, modify and sell items. Each inventory has
a limited weight capacity; attempting to pick up an item that would exceed this
limit will fail.

```java
Player player = gameManager.getPlayer(0);
Item sword = new Item("Sword", 3, 10, 100);
if (player.pickUp(sword)) {
    System.out.println("Picked up sword!");
}
player.sell(sword); // adds the item value to the player's gold
int gold = player.getGold();
```
