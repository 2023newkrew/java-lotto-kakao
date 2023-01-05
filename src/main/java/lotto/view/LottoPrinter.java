package lotto.view;

import lotto.constant.LottoGrade;
import lotto.domain.Lotto;
import lotto.dto.GameResultDto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static lotto.constant.ViewMessages.*;

public class LottoPrinter {
    public static void printManualLottoNumbersMessage() {
        System.out.println(MANUAL_LOTTO_NUMBER_MESSAGE);
    }

    public static void printManualLottoAmountMessage() {
        System.out.println(MANUAL_LOTTO_AMOUNT_MESSAGE);
    }

    public static void printResult(GameResultDto gameResultDto) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVIDER_MESSAGE);
        printCounterResult(gameResultDto.getLottoResultCounter());
        printEarningRate(gameResultDto.getEarningRate());
    }

    private static void printCounterResult(Map<LottoGrade, Integer> lottoResultCounter) {
        lottoResultCounter.remove(LottoGrade.NONE);
        lottoResultCounter.keySet().stream()
                .sorted(Comparator.comparingInt(o -> o.price))
                .forEach((lottoGradeEnum) -> System.out.printf(
                        MATCH_COUNT_MESSAGE + "\n",
                        lottoGradeEnum.matchCount,
                        lottoGradeEnum.isBonusMatches ? BONUS_BALL_MATCHES_MESSAGE : "",
                        lottoGradeEnum.price,
                        lottoResultCounter.get(lottoGradeEnum)
                ));
    }

    private static void printEarningRate(float earningRate) {
        System.out.printf(EARNING_RATE_MESSAGE + "\n", earningRate);
    }

    public static void printTotalMoneyMessage() {
        System.out.println(TOTAL_PRICE_MESSAGE);
    }

    public static void printLottoList(List<Lotto> lottos) {
        System.out.println(lottos.size() + BOUGHT_AMOUNT_MESSAGE);
        lottos.forEach((lotto) -> System.out.println(lotto.toString()));
        System.out.println();
    }

    public static void printWinningLottoNumbersMessage() {
        System.out.println(WINNING_LOTTO_NUMBER_MESSAGE);
    }

    public static void printBonusNumberMessage() {
        System.out.println(BONUS_NUMBER_MESSAGE);
    }
}
