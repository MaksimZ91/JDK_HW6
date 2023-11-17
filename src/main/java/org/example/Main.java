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