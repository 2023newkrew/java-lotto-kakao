package lotto;

import java.util.List;

public class LottoWinningNumber extends LottoTicket {
    private final LottoBall bonusBall;

    public LottoWinningNumber(List<LottoBall> lottoNumbers, LottoBall bonusBall) {
        super(lottoNumbers);
        this.bonusBall = bonusBall;

        if (this.lottoBalls.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 번호는 다른 번호와 중복될 수 없습니다.");
        }
    }

    public Ranking calculateRanking(LottoTicket lottoTicket) {
        int count = (int) lottoTicket.lottoBalls
                .stream()
                .filter(lottoBall -> this.lottoBalls.contains(lottoBall))
                .count();

        return Ranking.matchRanking(count, lottoTicket.lottoBalls.contains(bonusBall));
    }
}
