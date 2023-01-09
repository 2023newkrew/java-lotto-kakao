package lotto.domain;

import lotto.domain.enumeration.LottoResult;

import java.util.List;

public class LottoTicket {

    public static final int LOTTO_TICKET_PRICE = 1000;

    private final LottoNumberList lottoNumberList;

    public LottoTicket(List<Integer> numbers) {
        lottoNumberList = new LottoNumberList(numbers);
    }

    public LottoTicket(LottoNumberList lottoNumberList) {
        this.lottoNumberList = lottoNumberList;
    }

    @Override
    public String toString() {
        return lottoNumberList.toString();
    }

    public LottoResult getResult(WinningLotto winningLotto) {
        int equalNumber = winningLotto.countCorrectNumbers(lottoNumberList);
        boolean isBonusNumberIn = winningLotto.checkBonusNumberInNumberList(lottoNumberList);
        return LottoResult.getLottoResultOf(equalNumber, isBonusNumberIn);
    }
}
