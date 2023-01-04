package view;

import domain.LottoNumbers;

import java.util.List;

public class PurchasedLotto {

    private static final String NUMBER_OF_LOTTO_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.";

    private final int numberOfManualLotto;
    private final int numberOfAutomaticLotto;
    private final List<LottoNumbers> lottoNumbersList;

    public PurchasedLotto(int numberOfManualLotto, int numberOfAutomaticLotto, List<LottoNumbers> lottoNumbersList) {
        this.numberOfManualLotto = numberOfManualLotto;
        this.numberOfAutomaticLotto = numberOfAutomaticLotto;
        this.lottoNumbersList = lottoNumbersList;
    }

    public List<LottoNumbers> getLottoNumbersList() {
        return lottoNumbersList;
    }

    @Override
    public String toString() {
        return String.format(NUMBER_OF_LOTTO_MESSAGE, numberOfManualLotto, numberOfAutomaticLotto);
    }
}
