package domain.lotto;

import domain.judgment.LottoPrizeJudgment;
import domain.judgment.impl.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum LottoPrize implements Comparable<LottoPrize> {

    FIRST_PRIZE(2_000_000_000, new FirstLottoPrizeJudgment()),
    SECOND_PRIZE(30_000_000, new SecondLottoPrizeJudgment()),
    THIRD_PRIZE(1_500_000, new ThirdLottoPrizeJudgment()),
    FOURTH_PRIZE(50_000, new FourthLottoPrizeJudgment()),
    FIFTH_PRIZE(5_000, new FifthLottoPrizeJudgment()),
    NONE_PRIZE(0, new NoneLottoPrizeJudgment());

    private final int prizeMoney;
    public final LottoPrizeJudgment prizeJudgment;

    LottoPrize(int prizeMoney, LottoPrizeJudgment prizeJudgment) {
        this.prizeMoney = prizeMoney;
        this.prizeJudgment = prizeJudgment;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static LottoPrize matchLottoNumbers(LottoNumbers lottoNumbers, LottoWinningNumbers lottoWinningNumbers) {
        List<LottoPrize> orderedLottoPrizes = getOrderedLottoPrizes();
        return orderedLottoPrizes.stream()
                .filter(lottoPrize -> lottoPrize.prizeJudgment.judge(lottoNumbers, lottoWinningNumbers))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static List<LottoPrize> getOrderedLottoPrizes() {
        return Arrays.stream(values())
                .sorted((prize1, prize2) -> -Integer.compare(prize1.prizeMoney, prize2.prizeMoney))
                .collect(Collectors.toList());
    }
}
