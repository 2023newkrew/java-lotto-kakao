package domain.judgment.impl;

import domain.judgment.LottoPrizeJudgment;
import domain.lotto.LottoNumber;
import domain.lotto.LottoNumbers;
import domain.lotto.LottoWinningNumbers;

public class SecondLottoPrizeJudgment implements LottoPrizeJudgment {

    @Override
    public boolean judge(LottoNumbers lottoNumbers, LottoWinningNumbers lottoWinningNumbers) {
        LottoNumbers winningNumbers = lottoWinningNumbers.getLottoNumbers();
        LottoNumber bonusNumber = lottoWinningNumbers.getBonusNumber();
        return lottoNumbers.countCommonLottoNumbers(winningNumbers) == 5 && lottoNumbers.contains(bonusNumber);
    }
}
