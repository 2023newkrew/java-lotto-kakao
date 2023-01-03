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
        values.put(grade, get(grade) + 1);
        return values.get(grade);
    }

    public int get(Grade grade) {
        return values.getOrDefault(grade, 0);
    }

    public float getProfitRate(int money) {
        return (float) (
                values.get(Grade.THREE) * Grade.THREE.getReward()
                        + values.get(Grade.FOUR) * Grade.FOUR.getReward()
                        + values.get(Grade.FIVE) * Grade.FIVE.getReward()
                        + values.get(Grade.FIVE_BONUS) * Grade.FIVE_BONUS.getReward()
                        + values.get(Grade.SIX) * Grade.SIX.getReward()
        ) / (float) money;
    }
}
