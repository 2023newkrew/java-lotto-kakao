package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
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

    private Lotto inputLottoNumber() {
        return new Lotto(
                Arrays.stream(scanner.nextLine().replace(" ", "").split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
    }

    public int inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public Lottos inputManualLottoNumber(int count) {
        Lottos lottos = new Lottos();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < count; i++) {
            lottos.add(inputLottoNumber());
        }
        return lottos;
    }

    public void printAmount(int manualLottoAmount, int autoLottoAmount) {
        System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualLottoAmount, autoLottoAmount);
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

    public Lotto inputWinLottoNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return inputLottoNumber();
    }

    public LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return new LottoNumber(scanner.nextInt());
    }

    public void printStatistics(Statistics stat) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<LottoRank> rankList = new ArrayList<>(Arrays.asList(LottoRank.values()));
        rankList.remove(LottoRank.FAIL);
        for (LottoRank rank : rankList) {
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.COUNT, rank.PRIZE, stat.getByRank(rank));
        }
    }

    public void printTotalProfitRate(Statistics stat) {
        System.out.printf("총 수익률은 %f입니다.\n", stat.getProfitRate());
    }
}
