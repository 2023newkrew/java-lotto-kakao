package domain.lotto.prize.judgment.impl;

import domain.lotto.LottoNumber;
import domain.lotto.LottoWinningNumber;
import domain.lotto.prize.judgment.LottoPrizeJudgment;
import domain.lotto.ticket.LottoTicket;

import java.util.List;

public class FirstLottoPrizeJudgment implements LottoPrizeJudgment {

    @Override
    public boolean judge(LottoTicket lottoTicket, LottoWinningNumber lottoWinningNumber) {
        List<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();
        List<LottoNumber> winningNumbers = lottoWinningNumber.getLottoNumbers();

        return lottoNumbers.containsAll(winningNumbers);
    }

}
