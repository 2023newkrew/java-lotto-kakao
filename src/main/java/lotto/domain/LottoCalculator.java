package lotto.domain;

import java.util.Arrays;

public class LottoCalculator {

    public static LottoPrize calculatePrize(int matchNumberCount, boolean hasBonusNumber) {
        return Arrays.stream(LottoPrize.values())
                .filter(prize -> prize.isQualified(matchNumberCount, hasBonusNumber))
                .findFirst()
                .orElse(LottoPrize.NONE);
    }
}
