package lotto.view.output;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lotto.model.number.LottoNumber;
import lotto.model.enums.LottoResultType;
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

    public void printNumberOfLotto(Integer numberOfLotto, Integer numberOfLottoByManual) {
        Integer numberOfLottoByAuto = numberOfLotto - numberOfLottoByManual;
        console.printOutput(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", numberOfLottoByManual, numberOfLottoByAuto));
    }

    public void printLotto(List<LottoNumber> lottoNumbers) {
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
        List<LottoResultType> lottoResultTypes = Arrays.asList(LottoResultType.values());
        Collections.reverse(lottoResultTypes);

        console.printOutput("당첨 통계\n----------\n");
        lottoResultTypes.forEach((result) -> console.printOutput(getStatisticsFormat(statistics, result)));
        console.printOutput("총 수익률은 " + rateString + "입니다.\n");
    }

    private String getStatisticsFormat(LottoStatistic statistics, LottoResultType lottoResultType) {
        Integer numberOfMatchedLotto = statistics.getCount(lottoResultType);
        String bonusMatchString = getBonusMatchString(lottoResultType);

        if (lottoResultType == LottoResultType.NONE) {
            return "";
        }

        return String.format("%d개 일치%s (%d)원-%d개", lottoResultType.getMatchCount(), bonusMatchString, lottoResultType.getPrize(), numberOfMatchedLotto);
    }

    private String getBonusMatchString(LottoResultType lottoResultType) {
        if (lottoResultType == LottoResultType.SECOND) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    public void askForNumberOfLottoWithManual() {
        console.printOutput("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void askForLottoNumberWithManual() {
        console.printOutput("수동으로 구매할 번호를 입력해 주세요.");
    }
}