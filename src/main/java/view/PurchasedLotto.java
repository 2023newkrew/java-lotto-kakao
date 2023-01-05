package view;

import domain.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

public class PurchasedLotto {

    private static final String LOTTO_COUNT_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";

    private final int manualLottoCount;
    private final int automaticLottoCount;
    private final List<LottoNumbers> lottoNumbersList;

    public PurchasedLotto(int manualLottoCount, int automaticLottoCount, List<LottoNumbers> lottoNumbersList) {
        this.manualLottoCount = manualLottoCount;
        this.automaticLottoCount = automaticLottoCount;
        this.lottoNumbersList = lottoNumbersList;
    }

    public List<LottoNumbers> getLottoNumbersList() {
        return lottoNumbersList;
    }

    @Override
    public String toString() {
        return String.format(LOTTO_COUNT_MESSAGE, manualLottoCount, automaticLottoCount) +lottoNumbersList.stream()
                .map(LottoNumbers::toString)
                .collect(Collectors.joining("\n"));
    }
}
