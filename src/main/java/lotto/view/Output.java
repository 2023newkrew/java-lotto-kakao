package lotto.view;

import lotto.model.LottoConstants;
import lotto.model.LottoCount;
import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.LottoTrials;
import lotto.model.TotalResult;
import lotto.model.WinningCondition;

public class Output {
    private Output() {}

    public static void printLottoCount(LottoCount lottoCount) {
        System.out.println(lottoCount.getCount() + "개를 구매했습니다.");
    }

    public static void printLottoTrials(LottoTrials lottoTrials) {
        for (int i = 0; i < lottoTrials.getSize(); i++) {
            printLotto(lottoTrials.getLottoTrial(i));
        }
    }

    public static void printLotto(Lotto lotto) {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < LottoConstants.BALLCOUNT_LIMIT - 1; i++){
            result.append(lotto.getBall(i)).append(", ");
        }

        result.append(lotto.getBall(LottoConstants.BALLCOUNT_LIMIT - 1)).append("]");

        System.out.println(result);
    }

    public static void printTotalResult(TotalResult totalResult) {
        StringBuilder result = new StringBuilder("당첨 통계\n---------\n");

        String[] strings = new String[WinningCondition.values().length];
        final double returnRatio = totalResult.getReturnRatio();

        for (WinningCondition con : WinningCondition.values()) {
            final LottoResult lottoResult = con.getLottoResult();

            strings[con.getOrder()] = lottoResult.getMatchCount() + "개 일치" +
                    (lottoResult.isMatchBonus() ? ", 보너스 볼 일치" : "") + " (" +
                    con.getWinningPrize().getCash() + LottoConstants.CASH_UNIT + ") - " +
                    totalResult.getLottoResultNum(con.getLottoResult()) + "개";
        }

        result.append(String.join("\n", strings) + "\n");
        result.append(String.format("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                returnRatio, (returnRatio > 1 ? "이득이" : "손해")));

        System.out.println(result);
    }

    public static void printEnterCash() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printEnterWinNum() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printEnterBonusNum() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }
}
