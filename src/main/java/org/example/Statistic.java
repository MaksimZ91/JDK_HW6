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
