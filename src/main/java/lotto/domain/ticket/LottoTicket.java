package lotto.domain.ticket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.number.LottoNumberSet;
import lotto.domain.result.LottoResult;

public class LottoTicket {

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
        int equalNumber = lottoWinningNumbers.matchBaseNumbers(lottoNumberSet);
        boolean bonusNumber = lottoWinningNumbers.matchBonusNumber(lottoNumberSet);
        return LottoResult.of(equalNumber, bonusNumber);
    }
}
