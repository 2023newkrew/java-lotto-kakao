package lotto.view;

import lotto.model.LottoCount;
import lotto.model.LottoTrial;
import lotto.model.TotalResult;

public class Output {
    public static void startCashOutput() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void lottoCountOutput(LottoCount lottoCount) {
        System.out.println(lottoCount + "를 구매했습니다.");
    }

    public static void lottoTrialOutput(LottoTrial lottoTrial) {
        System.out.println(lottoTrial);
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
