package lotto.domain;

import lotto.domain.enumeration.LottoResult;

import java.util.List;

public class LottoTicket {

    public static final int LOTTO_TICKET_PRICE = 1000;

    private final LottoNumberList lottoNumberList;

    public LottoTicket(List<Integer> numbers) {
        lottoNumberList = new LottoNumberList(numbers);
    }

    public String getString() {
        return lottoNumberList.getString();
    }

    public LottoResult getResult(Lotto lotto) {
        int equalNumber = lotto.getAmountOfNumbersInWinningNumbers(lottoNumberList);
        boolean bonusNumber = lotto.checkBonusNumberInNumbers(lottoNumberList);
        return LottoResult.getLottoResultOf(equalNumber, bonusNumber);
    }
}
