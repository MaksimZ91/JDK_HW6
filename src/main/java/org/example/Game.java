package org.example;
import java.util.Random;


public class Game {
    private static final String WIN_GIFT = "Car";
    private static final String LOOSE_GIFT = "Goat";
    private static final Random rand = new Random();
    private Door[] doors;
    private int numberWinDoor;
    private int numberOpenDoor;
    private Player player;

    public Game(boolean switchChoice) {
        this.doors = new Door[3];
        this.numberWinDoor = rand.nextInt(3);
        this.player = new Player(switchChoice);
    }

    private void initGame() {
        for (int i = 0; i < doors.length; i++) {
            if (i == numberWinDoor) {
                doors[i] = new Door(WIN_GIFT);
            } else {
                doors[i] = new Door(LOOSE_GIFT);
            }
        }
    }

    private void openDoor(int userChoice) {
        int door = rand.nextInt(3);
        while (numberWinDoor == door || door == userChoice) {
            door = rand.nextInt(3);
        }
        numberOpenDoor = door;
    }

    public String startGame() {
        initGame();
        int userChoice = player.userChoice();
        openDoor(userChoice);
        if (player.isSwitchChoice()) {
            userChoice = player.switchUserChoice(numberOpenDoor);
        }
        return doors[userChoice].getPrize();
    }

}


