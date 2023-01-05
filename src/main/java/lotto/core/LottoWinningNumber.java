package lotto.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LottoWinningNumber {
    /**
     * 등수에 대한 검색 방법에 대해 사전 정의한 테이블
     * 각 키의 의미는 다음과 같다.
     * 첫번째 키 {@link Integer} : 몇개의 공을 맞췄는지에 대한 기록
     * 두번째 키 {@link Boolean} : 보너스 공을 맞췄는지에 대한 기록
     */
    private static final Map<Integer, Map<Boolean, Ranking>> RANKING_TABLE = Map.ofEntries(
            Map.entry(6, Map.of(
                    false, Ranking.FIRST
            )),
            Map.entry(5, Map.of(
                    true, Ranking.SECOND,
                    false, Ranking.THIRD
            )),
            Map.entry(4, Map.of(
                    true, Ranking.FOURTH,
                    false, Ranking.FOURTH
            )),
            Map.entry(3, Map.of(
                    true, Ranking.FIFTH,
                    false, Ranking.FIFTH
            )),
            Map.entry(2, Map.of(
                    true, Ranking.OTHER,
                    false, Ranking.OTHER
            )),
            Map.entry(1, Map.of(
                    true, Ranking.OTHER,
                    false, Ranking.OTHER
            )),
            Map.entry(0, Map.of(
                    true, Ranking.OTHER,
                    false, Ranking.OTHER
            ))

    );
    protected final Set<LottoBall> lottoBalls;
    protected final LottoBall bonusBall;

    public LottoWinningNumber(List<LottoBall> lottoNumbers, LottoBall bonusBall) {
        Set<LottoBall> balls = Set.copyOf(lottoNumbers);
        if (balls.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("각 숫자는 중복을 허용하지 않습니다.");
        }
        if (balls.size() != 6) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다.");
        }
        if (balls.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 번호는 다른 번호와 중복될 수 없습니다.");
        }
        this.lottoBalls = balls;
        this.bonusBall = bonusBall;
    }

    public Ranking calculateRanking(LottoTicket lottoTicket) {
        // 몇개의 로또 공을 맞췄는지 집계
        int count = (int) lottoTicket.lottoBalls
                .stream()
                .filter(lottoBalls::contains)
                .count();
        boolean isCorrectBonusBall = lottoTicket.lottoBalls
                .stream()
                .anyMatch(v -> v.equals(bonusBall));
        return RANKING_TABLE
                .get(count)
                .get(isCorrectBonusBall);
    }
}
