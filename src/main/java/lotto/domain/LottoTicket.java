package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    public static final int LOTTO_TICKET_PRICE = 1000;

    private final LottoNumberSet lottoNumberSet;

    public LottoTicket(List<Integer> numberList) {
        this(new HashSet<>(numberList));
    }

    public LottoTicket(Set<Integer> numberSet) {
        this(new LottoNumberSet(numberSet));
    }

    public LottoTicket(LottoNumberSet lottoNumberSet) {
        this.lottoNumberSet = lottoNumberSet;
    }

    public String getString() {
        return lottoNumberSet.getString();
    }

    public LottoResult getResult(LottoWinningNumberList lottoWinningNumbers) {
        int equalNumber = lottoWinningNumbers.getAmountOfNumbersInWinningNumbers(lottoNumberSet);
        boolean bonusNumber = lottoWinningNumbers.checkBonusNumberInNumbers(lottoNumberSet);
        return LottoResult.of(equalNumber, bonusNumber);
    }
}
