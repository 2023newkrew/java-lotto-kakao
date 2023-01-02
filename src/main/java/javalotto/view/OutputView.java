package javalotto.view;


import javalotto.domain.LottoCount;
import javalotto.domain.Lottos;

public class OutputView {

    public void printLottoCount(LottoCount lottoCount) {
        System.out.println(lottoCount.getCount() + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos);
    }
}
