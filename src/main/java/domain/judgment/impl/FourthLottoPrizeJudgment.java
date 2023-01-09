package domain.judgment.impl;

import domain.judgment.LottoPrizeJudgment;
import domain.lotto.LottoNumbers;
import domain.lotto.LottoWinningNumbers;

public class FourthLottoPrizeJudgment implements LottoPrizeJudgment {

    @Override
    public boolean judge(LottoNumbers lottoNumbers, LottoWinningNumbers lottoWinningNumbers) {
        LottoNumbers winningNumbers = lottoWinningNumbers.getLottoNumbers();
        return lottoNumbers.countCommonLottoNumbers(winningNumbers) == 4;
    }
}
