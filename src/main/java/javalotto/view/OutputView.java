package javalotto.view;


import javalotto.domain.LottoCount;

public class OutputView {

    public void printLottoCount(LottoCount lottoCount) {
        System.out.println(lottoCount.getCount() + "개를 구매했습니다.");
    }
}
