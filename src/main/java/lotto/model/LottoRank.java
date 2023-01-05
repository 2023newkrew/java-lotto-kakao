package lotto.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

import java.util.Arrays;

public enum LottoRank {
    RANK1(6, false, 2_000_000_000),
    RANK2(5, true, 30_000_000),
    RANK3(5, false, 1_500_000),
    RANK4(4, false, 50_000),
    RANK5(3, false, 5_000),
    RANK6_1(2, false, 0),
    RANK6_2(1, false, 0),
    RANK6_3(0, false, 0);

    private final Integer matchedCount;
    private final boolean bonusBall;
    private final Integer reward;

    LottoRank(Integer matchedCount, boolean bonusBall, Integer reward){
        this.matchedCount = matchedCount;
        this.bonusBall = bonusBall;
        this.reward = reward;
    }

    public static LottoRank fromCountAndBonus(Integer count, boolean bonus){
        return Arrays.stream(values())
                .filter(value -> {
                    if(value.matchedCount != 5){
                        return value.matchedCount.equals(count);
                    }
                    return value.bonusBall == bonus && value.matchedCount.equals(count);
                })
                .findAny()
                .orElseThrow(() -> new LottoException(ErrorCode.UNEXPECTED_COUNT_AND_BONUS_BALL));
    }

    public Integer getMatchedCount() {
        return matchedCount;
    }

    public Integer getReward(){
        return reward;
    }

}
