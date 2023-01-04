package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

import lotto.constant.LottoGrade;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.GameResultDto;

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
        System.out.println("구매금액을 입력해 주세요.");
    }

    public static void printLottoList(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach((lotto) -> System.out.println(lotto.toString()));
        System.out.println();
    }

    private static void printWinningLottoNumbersMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    private static void printBonusNumberMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
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
        final String bonusMatches = ", 보너스 볼 일치";
        Map<LottoGrade, Integer> lottoResultCounter = gameResultDto.getLottoResultCounter();

        System.out.println("당첨 통계");
        System.out.println("-----------");
        lottoResultCounter.remove(LottoGrade.NONE);
        lottoResultCounter.keySet().stream()
                .sorted(Comparator.comparingInt(o -> o.price))
                .forEach((lottoGradeEnum) -> System.out.printf(
                        "%d개 일치%s (%d원)- %d개\n",
                        lottoGradeEnum.matchCount,
                        lottoGradeEnum.isBonusMatches ? bonusMatches : "",
                        lottoGradeEnum.price,
                        lottoResultCounter.get(lottoGradeEnum)
                ));
        System.out.printf("총 수익률은 %.2f 입니다.\n", gameResultDto.getRate());
    }

    public static int inputManualLottoAmount() {
        printManualLottoAmountMessage();
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printManualLottoAmountMessage() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static List<List<Integer>> inputManualLottoNumbers(int manualLottoAmount) {
        List<List<Integer>> lottoNumbersList = new ArrayList<>();

        printManualLottoNumbersMessage();
        for (int i = 0; i < manualLottoAmount; i++)
            lottoNumbersList.add(inputLottoNumbers());
        return lottoNumbersList;
    }

    private static void printManualLottoNumbersMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
