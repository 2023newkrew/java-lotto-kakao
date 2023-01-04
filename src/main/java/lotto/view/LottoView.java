package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoView {
    private final Scanner scanner;

    public LottoView() {
        scanner = new Scanner(System.in);
    }

    public int inputPurchase() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printAmount(int amount) {
        System.out.println(amount + "개를 구매했습니다.");
    }

    public void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
        System.out.println();
    }

    public Lotto inputWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new Lotto(
                Arrays.stream(scanner.nextLine().replace(" ", "").split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
    }

    public LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return new LottoNumber(scanner.nextInt());
    }

    public void printStatisticsText() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printStatistics(Statistics stat) {
        List<LottoRank> rankList = List.of(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST);
        for (LottoRank rank : rankList) {
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.COUNT, rank.PRIZE, stat.getByRank(rank));
        }
    }

    public void printTotalProfitRate(Statistics stat) {
        System.out.printf("총 수익률은 %f입니다.\n", stat.getProfitRate());
    }
}
