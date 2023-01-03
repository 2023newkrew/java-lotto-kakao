package lotto.domain;

import lotto.domain.enumeration.LottoResult;

import java.util.List;

public class LottoTicket {

    public static final int LOTTO_TICKET_PRICE = 1000;

    private final LottoNumberList lottoNumberList;

    public LottoTicket(List<Integer> numbers) {
        lottoNumberList = new LottoNumberList(numbers);
    }

    public String getDetail() {
        return lottoNumberList.getDetail();
    }

    public LottoResult getResult(Lotto lotto) {
        int equalNumber = lotto.countCorrectNumbers(lottoNumberList);
        boolean bonusNumber = lotto.checkBonusNumberInNumberList(lottoNumberList);
        return LottoResult.getLottoResultOf(equalNumber, bonusNumber);
    }
}
