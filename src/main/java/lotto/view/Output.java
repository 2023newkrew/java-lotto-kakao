package lotto.view;

import lotto.model.Cash;
import lotto.model.LottoConstants;
import lotto.model.LottoCount;
import lotto.model.Lotto;
import lotto.model.LottoTrials;
import lotto.model.TotalResult;

public class Output {
    public static void printCash(Cash cash) {
        System.out.println(cash.getCash() + LottoConstants.CASH_UNIT);
    }

    public static void printLottoCount(LottoCount lottoCount) {
        System.out.println(lottoCount.getCount() + "개");
    }

    public static void printLotto(Lotto lotto) {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < LottoConstants.BALLCOUNT_LIMIT - 1; i++){
            result.append(lotto.getBall(i)).append(", ");
        }

        result.append(lotto.getBall(LottoConstants.BALLCOUNT_LIMIT - 1)).append("]");

        System.out.println(result);
    }

    public static void startCashOutput() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void lottoCountOutput(LottoCount lottoCount) {
        System.out.println(lottoCount + "를 구매했습니다.");
    }

    public static void printLottoTrials(LottoTrials lottoTrials) {
        for (int i = 0; i < lottoTrials.getSize(); i++) {
            printLotto(lottoTrials.getLottoTrial(i));
        }
    }

    public static void winNumOutput() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void bonusNumOutput() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void totalResultOutput(TotalResult totalResult) {
        System.out.println(totalResult);
    }
}
