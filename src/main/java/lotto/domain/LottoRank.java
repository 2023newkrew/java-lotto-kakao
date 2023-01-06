package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false,2_000_000_000),
    SECOND(5, true,30_000_000),
    THIRD(5, false,1_500_000),
    FOURTH(4, false,50_000),
    FIFTH(3, false,5_000),
    FAIL(0, false,0);

    public final int COUNT;
    public final boolean BONUS;
    public final int PRIZE;

    LottoRank(int count, boolean bonus, int prize) {
        this.COUNT = count;
        this.BONUS = bonus;
        this.PRIZE = prize;
    }

    public static LottoRank getRank(int count, boolean bonus) {
        if(count == LottoRank.SECOND.COUNT && bonus)
            return LottoRank.SECOND;
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.COUNT==count && lottoRank.BONUS == false)
                .findAny()
                .orElse(FAIL);
    }

    public static LottoRank getRank(Lotto myLotto, Lotto winningLotto, LottoNumber bonusNumber) {
        return getRank(myLotto.compare(winningLotto), myLotto.hasBonus(bonusNumber));
    }
}
