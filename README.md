# Урок 6. Управление проектом: сборщики проектов
В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).  
Необходимо:  
Создать свой Java Maven или Gradle проект;  
Самостоятельно реализовать прикладную задачу;  
Сохранить результат в HashMap<шаг теста, результат>  
Вывести на экран статистику по победам и поражениям  

![JDK_HW6_RESULT](https://github.com/MaksimZ91/JDK_HW6/assets/72209139/f9d25fe2-af47-4149-b31f-9e3deb90fd59)

## CLass Main
```java
package org.example;

public class Main {
    public static void main(String[] args) {
        Statistic statisticForNotSwitchGame = new Statistic();
        Statistic statisticForOnlySwitchGame = new Statistic();
        Game notSwitchGame = new Game(false);
        Game onlySwitchGame = new Game(true);

        for (int i = 0; i < 1000; i++) {
            statisticForNotSwitchGame.addResult(i + 1, notSwitchGame.startGame());
            statisticForOnlySwitchGame.addResult(i + 1, onlySwitchGame.startGame());
        }

        System.out.println("Результата парадокса когда выбор не меняется: ");
        //System.out.println(statisticForNotSwitchGame.getResult());
        System.out.println(statisticForNotSwitchGame.getPercentWin());

        System.out.println("Результата парадокса когда выбор меняется: ");
        //System.out.println(statisticForOnlySwitchGame.getResult());
        System.out.println(statisticForOnlySwitchGame.getPercentWin());

    }
}
```
## Class Game
```java
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
```
## Class Player
```java
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
```

## Class Door
```java
package org.example;

public class Door {
    private final String gift;

    public Door(String gift) {
        this.gift = gift;
    }

    public String getPrize() {
        return gift;
    }

    @Override
    public String toString() {
        return gift;
    }
}

```
## Class Statistic
```java
package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Statistic {
    private static final String WIN_GIFT = "Car";
    private static final int CAPACITY = 1_000;
    private Map<Integer, String> result = new HashMap<>(CAPACITY);


    public void addResult(int step, String data) {
        result.put(step, data);
    }
    public Map<Integer, String> getResult() {
        return result;
    }

    public String getPercentWin() {
        int countWin = 0;
        for (Map.Entry<Integer, String> entry : result.entrySet()) {
            if (Objects.equals(entry.getValue(), WIN_GIFT)) {
                countWin++;
            }
        }
        return "Процент побед составил: " + (double) countWin / CAPACITY * 100;
    }
}

```


