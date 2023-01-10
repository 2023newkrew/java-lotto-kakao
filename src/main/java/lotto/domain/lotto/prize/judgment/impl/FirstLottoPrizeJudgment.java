package lotto.domain.lotto.prize.judgment.impl;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoWinningNumber;
import lotto.domain.lotto.prize.judgment.LottoPrizeJudgment;
import lotto.domain.lotto.ticket.LottoTicket;

import java.util.List;

public class FirstLottoPrizeJudgment implements LottoPrizeJudgment {

    @Override
    public boolean judge(LottoTicket lottoTicket, LottoWinningNumber lottoWinningNumber) {
        List<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();
        List<LottoNumber> winningNumbers = lottoWinningNumber.getLottoNumbers();

        return lottoNumbers.containsAll(winningNumbers);
    }

}
