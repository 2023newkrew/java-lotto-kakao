package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

import lotto.constant.LottoGrade;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.GameResultDto;

public class LottoView {
    static Scanner scanner = new Scanner(System.in);

    public static int inputInit() {
        printInit();
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printInit() {
        System.out.println("구매금액을 입력해 주세요.");
    }

    public static void printLottoList(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach((lotto) -> System.out.println(lotto.toString()));
        System.out.println();
    }

    private static void printWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    private static void printBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    private static List<Integer> inputWinningLottoNumbers() {
        printWinningLottoNumbers();
        return Arrays.stream(scanner.nextLine().trim().replace(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static int inputBonusNumber() {
        printBonusNumber();
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
}
