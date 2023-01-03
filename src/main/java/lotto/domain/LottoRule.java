package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoRule {
    private final static int MATCH_NUMBER_WEIGHT = 5;
    private final static int BONUS_NUMBER_WEIGHT = 1;

    private final static Map<Integer, LottoPrize> resultMap = new HashMap<>() {{
        put(MATCH_NUMBER_WEIGHT * LottoNumbers.LOTTO_NUMBER_LENGTH, LottoPrize.FIRST_PRIZE);
        put(MATCH_NUMBER_WEIGHT * (LottoNumbers.LOTTO_NUMBER_LENGTH - 1) + BONUS_NUMBER_WEIGHT, LottoPrize.SECOND_PRIZE);
        put(MATCH_NUMBER_WEIGHT * (LottoNumbers.LOTTO_NUMBER_LENGTH - 1), LottoPrize.THIRD_PRIZE);
        put(MATCH_NUMBER_WEIGHT * (LottoNumbers.LOTTO_NUMBER_LENGTH - 2) + BONUS_NUMBER_WEIGHT, LottoPrize.FOURTH_PRIZE);
        put(MATCH_NUMBER_WEIGHT * (LottoNumbers.LOTTO_NUMBER_LENGTH - 2), LottoPrize.FOURTH_PRIZE);
        put(MATCH_NUMBER_WEIGHT * (LottoNumbers.LOTTO_NUMBER_LENGTH - 3) + BONUS_NUMBER_WEIGHT, LottoPrize.FIFTH_PRIZE);
        put(MATCH_NUMBER_WEIGHT * (LottoNumbers.LOTTO_NUMBER_LENGTH - 3), LottoPrize.FIFTH_PRIZE);
    }};

    private LottoRule() {
    }

    public static LottoPrize getPrize(int matchNumberCount, boolean hasBonusNumber) {
        int bonusNumberCount = hasBonusNumber ? 1 : 0;
        return resultMap.getOrDefault(
                matchNumberCount * MATCH_NUMBER_WEIGHT + bonusNumberCount * BONUS_NUMBER_WEIGHT,
                LottoPrize.NONE
        );
    }
}
