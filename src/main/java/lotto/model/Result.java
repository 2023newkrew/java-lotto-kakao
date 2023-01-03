package lotto.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Result {
    private final Map<Grade, Integer> result;

    public Result(){
        result = new HashMap<>();
    }
    public Result(Map<Grade, Integer> result) {
        this.result = new HashMap<>(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result1 = (Result) o;
        return Objects.equals(result, result1.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    public int put(Grade grade, int value) {
        result.put(grade, value);
        return result.get(grade);
    }

    public int get(Grade grade) {
        return result.getOrDefault(grade, 0);
    }
}
