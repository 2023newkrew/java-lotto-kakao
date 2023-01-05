package lotto.domain;

import java.util.Arrays;

public class LottoCalculator {

    public static LottoPrize calculatePrize(int matchNumberCount, boolean hasBonusNumber) {
        return Arrays.stream(LottoPrize.values())
                .filter(prize -> prize.isWon(matchNumberCount, hasBonusNumber))
                .findFirst()
                .orElse(LottoPrize.NONE);
    }
}
