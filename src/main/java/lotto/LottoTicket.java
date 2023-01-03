package lotto;

import java.util.List;

public class LottoTicket {
    LottoNumbers lottoNumbers;

    public LottoTicket(List<Integer> numbers) {
        lottoNumbers = new LottoNumbers(numbers);
    }

    public String getString() {
        return lottoNumbers.getString();
    }

    public LottoResult getResult(LottoWinningNumbers lottoWinningNumbers) {
        int equalNumber = lottoWinningNumbers.getAmountOfNumbersInWinningNumbers(lottoNumbers);
        boolean bonusNumber = lottoWinningNumbers.checkBonusNumberInNumbers(lottoNumbers);
        return LottoResult.getLottoResultOf(equalNumber, bonusNumber);
    }
}
