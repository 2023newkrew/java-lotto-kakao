package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.GameResultDto;

public class LottoView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String REQUEST_RECEIVE_PURCHASE_INPUT = "구매금액을 입력해 주세요.";
    private static final String REQUEST_RECEIVE_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_RECEIVE_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    static public int receivePurchasePrice() {
        System.out.println(REQUEST_RECEIVE_PURCHASE_INPUT);
        return Integer.parseInt(scanner.nextLine());
    }

    static public void printLottoList(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach((lotto) -> System.out.println(lotto.toString()));
        System.out.println();
    }

    static public WinningLotto inputWinningLotto() {
        return new WinningLotto(receiveWinningLottoNumbers(), receiveBonusNumber());
    }

    static private List<Integer> receiveWinningLottoNumbers() {
        System.out.println(REQUEST_RECEIVE_WINNING_LOTTO_NUMBERS);
        return Arrays.stream(scanner.nextLine()
                        .split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static private int receiveBonusNumber() {
        System.out.println(REQUEST_RECEIVE_BONUS_NUMBER);
        return scanner.nextInt();
    }

    static public void printResult(GameResultDto gameResultDto) {
        System.out.println("당첨 통계");
        System.out.println("-----------");
        gameResultDto.getLottoResults()
                .forEach((lottoResult) -> System.out.printf(
                        "%d개 일치 (%d원)- %d개\n",
                        lottoResult.getGrade().matchCount,
                        lottoResult.getGrade().price,
                        lottoResult.getGradeCount()
                ));
        System.out.printf("총 수익률은 %.2f\n", gameResultDto.getRate());
    }
}
