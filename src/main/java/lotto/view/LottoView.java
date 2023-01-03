package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

import lotto.constant.LottoGradeEnum;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.GameResultDto;

public class LottoView {
    static Scanner scanner = new Scanner(System.in);

    static public int inputInit() {
        printInit();
        return Integer.parseInt(scanner.nextLine());
    }

    static private void printInit() {
        System.out.println("구매금액을 입력해 주세요.");
    }

    static public void printLottoList(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach((lotto) -> System.out.println(lotto.toString()));
        System.out.println();
    }

    static private void printWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    static private void printBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    static private List<Integer> inputWinningLottoNumbers() {
        printWinningLottoNumbers();
        return Arrays.stream(scanner.nextLine().trim().replace(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static private int inputBonusNumber() {
        printBonusNumber();
        return scanner.nextInt();
    }

    static public WinningLotto inputWinningLotto() {
        return new WinningLotto(inputWinningLottoNumbers(), inputBonusNumber());
    }

    static public void printResult(GameResultDto gameResultDto) {
        final String bonusMatches = ", 보너스 볼 일치";
        Map<LottoGradeEnum, Integer> lottoResultCounter = gameResultDto.getLottoResultCounter();

        System.out.println("당첨 통계");
        System.out.println("-----------");
        lottoResultCounter.remove(LottoGradeEnum.NONE);
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
