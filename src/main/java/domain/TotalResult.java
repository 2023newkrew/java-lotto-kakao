package domain;

import common.state.Result;
import view.OutputView;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<String> getTotalResultMessage() {
        return Arrays.stream(Result.values())
                .filter(result -> result != Result.NONE)
                .map(result ->
                        String.format(OutputView.TOTAL_RESULT_MESSAGE,
                                result.getDescription(),
                                result.getWinnings(),
                                this.getValueOfResult(result)))
                .collect(Collectors.toList());
    }

    public String getProfitMessage(double profit) {
        String message = String.format(OutputView.PROFIT_MESSAGE, profit);
        if (profit <= 1) {
            return message + OutputView.PROFIT_NEGATIVE_MESSAGE;
        }
        return message + OutputView.PROFIT_POSITIVE_MESSAGE;
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
