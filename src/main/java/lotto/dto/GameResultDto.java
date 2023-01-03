package lotto.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoConstants;
import lotto.domain.LottoRank;
import lotto.domain.LottoResultMessage;

public class GameResultDto {

    private final String result;
    private final Double yield;

    public GameResultDto(List<Integer> rankCount, int lottoCount) {
        result = makeResult(rankCount);
        yield = calculateYield(rankCount, lottoCount);
    }

    private String makeResult(List<Integer> rankCount) {
        List<String> result = Arrays.stream(LottoRank.values())
                .map(rank -> LottoResultMessage.getString(rank, rankCount.get(rank.getIndex())))
                .collect(Collectors.toList());
        Collections.reverse(result);
        return String.join("", result);
    }

    public double calculateYield(List<Integer> rankCount, int lottoCount) {
        return (double) calculateTotalWinning(rankCount) / (lottoCount * LottoConstants.LOTTO_PRICE.getValue());
    }

    private long calculateTotalWinning(List<Integer> rankCount) {
        return Arrays.stream(LottoRank.values()).mapToLong(rank -> rank.getWinning() * rankCount.get(rank.getIndex()))
                .sum();
    }

    public String getResult() {
        return result;
    }

    public Double getYield() {
        return yield;
    }
}
