package lotto.domain;

import java.util.List;

public class LottoWinningNumber {
    private final LottoTicket winningLotto;
    private final LottoBall bonusBall;

    public LottoWinningNumber(List<LottoBall> lottoNumbers, LottoBall bonusBall) {
        this.winningLotto = new LottoTicket(lottoNumbers);
        this.bonusBall = bonusBall;

        if (this.winningLotto.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 번호는 다른 번호와 중복될 수 없습니다.");
        }
    }

    public Ranking calculateRanking(LottoTicket lottoTicket) {
        int count = lottoTicket.countMatchingNumber(winningLotto);
        return Ranking.matchRanking(count, lottoTicket.contains(bonusBall));
    }
}
