package domain.lotto.prize.judgment.impl;

import domain.lotto.LottoNumber;
import domain.lotto.LottoWinningNumber;
import domain.lotto.prize.judgment.LottoPrizeJudgment;
import domain.lotto.ticket.LottoTicket;
import util.ListUtils;

import java.util.List;

public class SecondLottoPrizeJudgment implements LottoPrizeJudgment {

    @Override
    public boolean judge(LottoTicket lottoTicket, LottoWinningNumber lottoWinningNumber) {
        List<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();
        List<LottoNumber> winningNumbers = lottoWinningNumber.getLottoNumbers();
        LottoNumber bonusNumber = lottoWinningNumber.getBonusNumber();

        return ListUtils.countCommonElements(lottoNumbers, winningNumbers) == 5
                && lottoNumbers.contains(bonusNumber);
    }
}
