package domain;

import common.state.Result;

import java.util.*;

public class TotalResult {
    private final Map<Result, Integer> totalResult;

    public TotalResult() {
        totalResult = new HashMap<>();
        Arrays.stream(Result.values()).forEach(result -> totalResult.put(result, 0));
    }

    public TotalResult(Map<Result, Integer> totalResult) {
        this();
        this.totalResult.putAll(totalResult);
    }

    public void increaseValueOfResult(Result result) {
        Integer previousValue = this.getValueOfResult(result);
        totalResult.put(result, previousValue + 1);
    }

    public int getValueOfResult(Result result) {
        return totalResult.get(result);
    }

    private int getWinningsOfResult(Result result) {
        return getValueOfResult(result) * result.getWinnings();
    }

    public double getProfit(int paidPrice) {
        Integer winnings = Arrays.stream(Result.values()).map(this::getWinningsOfResult).reduce(Integer::sum).orElse(0);
        return Math.floor((double) winnings * 100.0 / (double) paidPrice) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalResult that = (TotalResult) o;
        return Arrays.stream(Result.values())
                .allMatch(result -> this.totalResult.get(result) == that.totalResult.get(result));
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalResult);
    }

}
