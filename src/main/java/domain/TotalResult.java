package domain;

import common.state.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TotalResult {
    private final Map<Result, Integer> totalResult;

    public TotalResult() {
        totalResult = new HashMap<>();
    }

    public TotalResult(Map<Result, Integer> totalResult) {
        this.totalResult = totalResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalResult that = (TotalResult) o;
        return Arrays.stream(Result.values())
                .allMatch(result -> this.totalResult.getOrDefault(result, 0) == that.totalResult.getOrDefault(result, 0));
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalResult);
    }

    public void increaseValueOfResult(Result result) {
        Integer previousValue = this.getValueOfResult(result);
        totalResult.put(result, previousValue + 1);
    }

    public int getValueOfResult(Result result) {
        return totalResult.getOrDefault(result, 0);
    }
}
