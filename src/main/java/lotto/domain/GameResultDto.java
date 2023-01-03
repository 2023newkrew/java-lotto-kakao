package lotto.domain;

import java.util.Arrays;
import java.util.List;

public class GameResultDto {

    private final String result;
    private final Double yield;

    public GameResultDto(List<Integer> rankCount, int lottoCount) {
        StringBuilder stringBuilder = new StringBuilder();

        for (LottoRank rank : LottoRank.values()) {
            stringBuilder.append(LottoConstants.getString(rank, rankCount.get(rank.index())));
        }
        result = stringBuilder.toString();
        yield = calculateYield(rankCount, lottoCount);
    }

    public double calculateYield(List<Integer> rankCount, int lottoCount) {
        return (double) (calculateTotalWinning(rankCount) / (lottoCount * LottoConstants.LOTTO_PRICE));

    }

    private long calculateTotalWinning(List<Integer> rankCount) {
        return Arrays.stream(LottoRank.values()).mapToLong(rank -> rank.winning() * rankCount.get(rank.index())).sum();
    }

    public String getResult() {
        return result;
    }

    public Double getYield() {
        return yield;
    }
}
