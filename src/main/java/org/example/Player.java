package org.example;
import java.util.Random;

public class Player {
    private static final Random rand = new Random();
    private final boolean switchChoice;
    private int playerChoice;

    public Player(boolean switchChoice) {
        this.switchChoice = switchChoice;
    }

    public boolean isSwitchChoice() {
        return switchChoice;
    }

    public int userChoice() {
        int choice = rand.nextInt(3);
        this.playerChoice = choice;
        return choice;
    }

    public int switchUserChoice(int numberOpenDoor) {
        int newUserChoice = rand.nextInt(3);
        while (playerChoice == newUserChoice || newUserChoice == numberOpenDoor) {
            newUserChoice = rand.nextInt(3);
        }
        return newUserChoice;

    }


}
