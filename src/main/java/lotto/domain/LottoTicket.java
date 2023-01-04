package lotto.domain;

import java.util.List;

public class LottoTicket {

    public static final int LOTTO_TICKET_PRICE = 1000;

    private final LottoNumberList lottoNumbers;

    public LottoTicket(List<Integer> numbers) {
        lottoNumbers = new LottoNumberList(numbers);
    }

    public String getString() {
        return lottoNumbers.getString();
    }

    public LottoResult getResult(LottoWinningNumberList lottoWinningNumbers) {
        int equalNumber = lottoWinningNumbers.getAmountOfNumbersInWinningNumbers(lottoNumbers);
        boolean bonusNumber = lottoWinningNumbers.checkBonusNumberInNumbers(lottoNumbers);
        return LottoResult.getLottoResultOf(equalNumber, bonusNumber);
    }
}
