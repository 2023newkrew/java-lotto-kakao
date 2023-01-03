package lotto.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Result {
    private final Map<Grade, Integer> values;

    public Result() {
        values = new HashMap<>();
    }

    public Result(Map<Grade, Integer> values) {
        this.values = new HashMap<>(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result1 = (Result) o;
        return Objects.equals(values, result1.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    public int addUp(Grade grade) {
        values.put(grade, getValue(grade) + 1);
        return values.get(grade);
    }

    public int getValue(Grade grade) {
        return values.getOrDefault(grade, 0);
    }

    public double getProfitRate(int money) {
        return (double) (
                getValue(Grade.THREE) * Grade.THREE.getReward()
                        + getValue(Grade.FOUR) * Grade.FOUR.getReward()
                        + getValue(Grade.FIVE) * Grade.FIVE.getReward()
                        + getValue(Grade.FIVE_BONUS) * Grade.FIVE_BONUS.getReward()
                        + getValue(Grade.SIX) * Grade.SIX.getReward()
        ) / (double) money;
    }
}
