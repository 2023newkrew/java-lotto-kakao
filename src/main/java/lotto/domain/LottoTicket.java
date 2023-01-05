package lotto.domain;

import java.util.HashSet;
import java.util.Set;

public class LottoTicket {

    private static final int LOTTO_TICKET_BALL_COUNT = 6;
    private final Set<LottoBall> lottoBalls;

    public LottoTicket(Set<LottoBall> lottoBalls) {
        validateLottoBalls(lottoBalls);
        this.lottoBalls = new HashSet<>(lottoBalls);
    }

    private void validateLottoBalls(Set<LottoBall> lottoBalls) {
        if (lottoBalls.size() != LOTTO_TICKET_BALL_COUNT) {
            throw new IllegalArgumentException("로또 티켓 한 장은 6개의 중복 없는 로또볼이 필요합니다.");
        }
    }

    public boolean contains(LottoBall lottoBall) {
        return lottoBalls.contains(lottoBall);
    }

    public int countMatch(LottoTicket lottoTicket) {
        return (int) lottoBalls.stream()
                .filter(lottoBall -> lottoTicket.contains(lottoBall))
                .count();
    }

    public Set<LottoBall> getLottoBalls() {
        return lottoBalls;
    }
}
