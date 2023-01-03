package javalotto.view;


import javalotto.domain.LottoCount;
import javalotto.domain.Lottos;
import javalotto.domain.WinningLotto;

public class OutputView {

    public void printLottoCount(LottoCount lottoCount) {
        System.out.println(lottoCount.getCount() + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos);
    }

    public void printResult(WinningLotto winningLotto, Lottos lottos) {
        printWinningStatistics(winningLotto, lottos);
    }

    public void printWinningStatistics(WinningLotto winningLotto, Lottos lottos) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        System.out.println(lottos.getLottoResult(winningLotto));
    }
}
