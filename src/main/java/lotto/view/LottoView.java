package lotto.view;

import lotto.domain.*;
import lotto.util.LottoParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoView {
    private final Scanner scanner;

    public LottoView() {
        scanner = new Scanner(System.in);
    }

    public int inputPurchase() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printAutoAmount(int amount) {
        System.out.printf("자동으로 %d개를 구매했습니다.\n", amount);
    }

    public void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
        System.out.println();
    }

    public Lotto inputWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new Lotto(LottoParser.getNumbers(scanner.nextLine()));
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
        System.out.printf("%d개 일치 (%d원)- %d개\n", LottoRank.FIFTH.COUNT, LottoRank.FIFTH.PRIZE, stat.getByRank(LottoRank.FIFTH));
        System.out.printf("%d개 일치 (%d원)- %d개\n", LottoRank.FOURTH.COUNT, LottoRank.FOURTH.PRIZE, stat.getByRank(LottoRank.FOURTH));
        System.out.printf("%d개 일치 (%d원)- %d개\n", LottoRank.THIRD.COUNT, LottoRank.THIRD.PRIZE, stat.getByRank(LottoRank.THIRD));
        System.out.printf("%d개 일치 (%d원)- %d개\n", LottoRank.SECOND.COUNT, LottoRank.SECOND.PRIZE, stat.getByRank(LottoRank.SECOND));
        System.out.printf("%d개 일치 (%d원)- %d개\n", LottoRank.FIRST.COUNT, LottoRank.FIRST.PRIZE, stat.getByRank(LottoRank.FIRST));
    }

    public void printTotalProfitRate(Statistics stat) {
        System.out.printf("총 수익률은 %f입니다.\n", stat.getProfitRate());
    }

    public int inputManualAmount(){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<Lotto> inputManualLottos(int amount){
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> result = new ArrayList<>();
        while(amount-- > 0)
            result.add(new Lotto(LottoParser.getNumbers(scanner.nextLine())));
        return result;
    }

    public void printManualAmount(int amount) {
        System.out.printf("수동으로 %d장, ", amount);
    }
}
