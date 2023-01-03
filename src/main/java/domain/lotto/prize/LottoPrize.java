package domain.lotto.prize;

import domain.lotto.LottoWinningNumber;
import domain.lotto.prize.judgment.LottoPrizeJudgment;
import domain.lotto.prize.judgment.impl.*;
import domain.lotto.ticket.LottoTicket;

import java.util.Arrays;

public enum LottoPrize {

    FIRST_PRIZE(2_000_000_000, new FirstLottoPrizeJudgment()),
    SECOND_PRIZE(30_000_000, new SecondLottoPrizeJudgment()),
    THIRD_PRIZE(1_500_000, new ThirdLottoPrizeJudgment()),
    FOURTH_PRIZE(50_000, new FourthLottoPrizeJudgment()),
    FIFTH_PRIZE(5_000, new FifthLottoPrizeJudgment()),
    NONE_PRIZE(0, new NoneLottoPrizeJudgment());

    LottoPrize(int prizeMoney, LottoPrizeJudgment prizeJudgment) {
        this.prizeMoney = prizeMoney;
        this.prizeJudgment = prizeJudgment;
    }

    private final int prizeMoney;

    private final LottoPrizeJudgment prizeJudgment;

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static LottoPrize findPrize(LottoTicket lottoTicket, LottoWinningNumber lottoWinningNumber) {
        return Arrays.stream(values())
                .filter(e -> e.prizeJudgment.judge(lottoTicket, lottoWinningNumber))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

}
