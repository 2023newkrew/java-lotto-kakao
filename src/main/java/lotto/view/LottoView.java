package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

import lotto.constant.LottoGrade;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.GameResultDto;

import static lotto.constant.ViewMessages.*;

public class LottoView {
    private static final Scanner scanner = new Scanner(System.in);

    private static List<Integer> inputLottoNumbers() {
        return Arrays.stream(scanner.nextLine().trim().replace(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputTotalMoney() {
        printTotalMoneyMessage();
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printTotalMoneyMessage() {
        System.out.println(TOTAL_PRICE_MESSAGE);
    }

    public static void printLottoList(List<Lotto> lottos) {
        System.out.println(lottos.size() + BOUGHT_AMOUNT_MESSAGE);
        lottos.forEach((lotto) -> System.out.println(lotto.toString()));
        System.out.println();
    }

    private static void printWinningLottoNumbersMessage() {
        System.out.println(WINNING_LOTTO_NUMBER_MESSAGE);
    }

    private static void printBonusNumberMessage() {
        System.out.println(BONUS_NUMBER_MESSAGE);
    }

    private static List<Integer> inputWinningLottoNumbers() {
        printWinningLottoNumbersMessage();
        return inputLottoNumbers();
    }

    private static int inputBonusNumber() {
        printBonusNumberMessage();
        return scanner.nextInt();
    }

    public static WinningLotto inputWinningLotto() {
        return new WinningLotto(inputWinningLottoNumbers(), inputBonusNumber());
    }

    public static void printResult(GameResultDto gameResultDto) {
        Map<LottoGrade, Integer> lottoResultCounter = gameResultDto.getLottoResultCounter();

        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVIDER_MESSAGE);
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
        System.out.printf(EARNING_RATE_MESSAGE + "\n", gameResultDto.getEarningRate());
    }

    public static int inputManualLottoAmount() {
        printManualLottoAmountMessage();
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printManualLottoAmountMessage() {
        System.out.println(MANUAL_LOTTO_AMOUNT_MESSAGE);
    }

    public static List<List<Integer>> inputManualLottoNumbers(int manualLottoAmount) {
        List<List<Integer>> lottoNumbersList = new ArrayList<>();

        printManualLottoNumbersMessage();
        for (int i = 0; i < manualLottoAmount; i++)
            lottoNumbersList.add(inputLottoNumbers());
        return lottoNumbersList;
    }

    private static void printManualLottoNumbersMessage() {
        System.out.println(MANUAL_LOTTO_NUMBER_MESSAGE);
    }
}
