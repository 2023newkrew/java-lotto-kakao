package lotto.views;

import java.util.List;
import lotto.models.Lotto;
import lotto.models.LottoResult;
import lotto.models.LottoStatistics;

public class OutputView {
    Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    public void askForMoneyToBuyLotto() {
        console.printOutput("구입금액을 입력해 주세요.");
    }

    public void printNumberOfLotto(Integer numberOfLotto) {
        console.printOutput(numberOfLotto.toString() + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> Lottos) {
        for (Lotto lotto:
             Lottos) {
            console.printOutput(lotto.getNumbers().toString());
        }
    }

    public void askForLastGoalNumbers() {
        console.printOutput("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void askForBonusBall() {
        console.printOutput("보너스 볼을 입력해 주세요.");
    }

    public void printStatistics(LottoStatistics statistics) {
        String rateString = String.format("%.2f", statistics.getEarningsRate());

        console.printOutput("당첨 통계\n"
                + "----------\n"
                + "3개 일치 (" + LottoResult.FIFTH.getPrize() + "원)- " + statistics.getCountOf(LottoResult.FIFTH) + "개\n"
                + "4개 일치 (" + LottoResult.FOURTH.getPrize() + "원)- " + statistics.getCountOf(LottoResult.FOURTH) + "개\n"
                + "5개 일치 (" + LottoResult.THIRD.getPrize() + "원)- " + statistics.getCountOf(LottoResult.THIRD) + "개\n"
                + "5개 일치, 보너스 볼 일치(" + LottoResult.SECOND.getPrize() + "원) - " + statistics.getCountOf(LottoResult.SECOND) + "개\n"
                + "6개 일치 (" + LottoResult.FIRST.getPrize() + "원)- " + statistics.getCountOf(LottoResult.FIRST) + "개\n"
                + "총 수익률은 " + rateString + "입니다.\n");
    }
}