package lotto.view.output;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lotto.model.number.LottoNumber;
import lotto.common.LottoResult;
import lotto.model.statistic.LottoStatistic;
import lotto.view.console.Console;

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

    public void printLottos(List<LottoNumber> lottoNumbers) {
        for (LottoNumber lottoNumber :
                lottoNumbers) {
            console.printOutput(lottoNumber.getNumbers().toString());
        }
    }

    public void askForLastGoalNumbers() {
        console.printOutput("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void askForBonusBall() {
        console.printOutput("보너스 볼을 입력해 주세요.");
    }

    public void printStatistics(LottoStatistic statistics, Double rate) {
        String rateString = String.format("%.2f", rate);
        List<LottoResult> lottoResults = Arrays.asList(LottoResult.values());
        Collections.reverse(lottoResults);

        console.printOutput("당첨 통계\n----------\n");
        lottoResults.forEach((result) -> console.printOutput(getStatisticsFormat(statistics, result)));
        console.printOutput("총 수익률은 " + rateString + "입니다.\n");
    }

    private String getStatisticsFormat(LottoStatistic statistics, LottoResult lottoResult) {
        Integer numberOfMatchedLotto = statistics.getCount(lottoResult);
        String bonusMatchString = getBonusMatchString(lottoResult);

        if (lottoResult == LottoResult.NONE) {
            return "";
        }

        return String.format("%d개 일치%s (%d)원-%d개", lottoResult.getMatchCount(), bonusMatchString, lottoResult.getPrize(), numberOfMatchedLotto);
    }

    private String getBonusMatchString(LottoResult lottoResult) {
        if (lottoResult == LottoResult.SECOND) {
            return ", 보너스 볼 일치";
        }
        return "";
    }
}