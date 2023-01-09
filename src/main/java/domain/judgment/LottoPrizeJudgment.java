package domain.judgment;

import domain.lotto.LottoNumbers;
import domain.lotto.LottoWinningNumbers;

public interface LottoPrizeJudgment {

    boolean judge(LottoNumbers lottoNumbers, LottoWinningNumbers lottoWinningNumbers);
}
