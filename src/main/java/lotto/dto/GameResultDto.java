package lotto.dto;

import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoConstants;
import lotto.domain.LottoRank;
import lotto.domain.LottoResultString;

public class GameResultDto {

    private final String result;
    private final Double yield;

    public GameResultDto(List<Integer> rankCount, int lottoCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoRank rank : LottoRank.values()) {
            stringBuilder.insert(0, LottoResultString.getString(rank, rankCount.get(rank.getIndex())));
        }
        result = stringBuilder.toString();
        yield = calculateYield(rankCount, lottoCount);
    }

    public double calculateYield(List<Integer> rankCount, int lottoCount) {
        return (double) calculateTotalWinning(rankCount) / (lottoCount * LottoConstants.LOTTO_PRICE.getValue());
    }

    private long calculateTotalWinning(List<Integer> rankCount) {
        return Arrays.stream(LottoRank.values()).mapToLong(rank -> rank.getWinning() * rankCount.get(rank.getIndex())).sum();
    }

    public String getResult() {
        return result;
    }

    public Double getYield() {
        return yield;
    }
}
