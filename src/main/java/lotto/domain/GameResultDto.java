package lotto.domain;

import java.util.List;

public class GameResultDto {
    private final List<Integer> rankCount;
    private final double yield;

    private GameResultDto(List<Integer> rankCount, double yield) {
        this.rankCount = rankCount;
        this.yield = yield;
    }

    public static GameResultDto of(GameResult gameResult) {
        return new GameResultDto(gameResult.getRankCount(), gameResult.getYield());
    }

    public List<Integer> getRankCount() {
        return rankCount;
    }

    public double getYield() {
        return yield;
    }
}
