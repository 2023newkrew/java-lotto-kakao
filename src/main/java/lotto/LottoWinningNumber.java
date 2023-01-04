package lotto;

import java.util.List;
import java.util.Map;

public class LottoWinningNumber extends LottoTicket {
    private static final Map<Integer, Ranking> RANKING_TABLE = Map.ofEntries(
            Map.entry(60, Ranking.FIRST),
            Map.entry(55, Ranking.SECOND),
            Map.entry(50, Ranking.THIRD),
            Map.entry(45, Ranking.FOURTH),
            Map.entry(40, Ranking.FOURTH),
            Map.entry(35, Ranking.FIFTH),
            Map.entry(30, Ranking.FIFTH),
            Map.entry(25, Ranking.OTHER),
            Map.entry(20, Ranking.OTHER),
            Map.entry(15, Ranking.OTHER),
            Map.entry(10, Ranking.OTHER),
            Map.entry(5, Ranking.OTHER),
            Map.entry(0, Ranking.OTHER)
    );
    protected final LottoBall bonusBall;

    public LottoWinningNumber(List<LottoBall> lottoNumbers, LottoBall bonusBall) {
        super(lottoNumbers);
        if (this.lottoBalls.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 번호는 다른 번호와 중복될 수 없습니다.");
        }
        this.bonusBall = bonusBall;
    }

    public Ranking calculateRanking(LottoTicket lottoTicket) {
        int count = lottoTicket.lottoBalls
                .stream()
                .mapToInt((curr) -> (this.lottoBalls.contains(curr) ? 10 : 0))
                .sum();
        if (lottoTicket.lottoBalls.contains(bonusBall)) {
            count += 5;
        }
        return RANKING_TABLE.get(count);
    }
}
