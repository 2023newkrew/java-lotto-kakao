package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    protected static final int LOTTO_TICKET_BALL_COUNT = 6;

    private final List<LottoBall> lottoBalls;

    public LottoTicket(List<LottoBall> lottoBalls) {
        validateSize(lottoBalls);
        validateDuplicate(lottoBalls);
        Collections.sort(lottoBalls);
        this.lottoBalls = lottoBalls;
    }

    private void validateSize(List<LottoBall> lottoBalls) {
        if (lottoBalls.size() != LOTTO_TICKET_BALL_COUNT) {
            throw new IllegalArgumentException("로또 티켓 한 장은 6개의 로또볼이 필요합니다.");
        }
    }

    private void validateDuplicate(List<LottoBall> lottoBalls) {
        Set<LottoBall> duplicateCheck = new HashSet<>(lottoBalls);
        if (duplicateCheck.size() != LOTTO_TICKET_BALL_COUNT) {
            throw new IllegalArgumentException("로또 티켓에 중복되는 로또볼이 있습니다.");
        }
    }

    public boolean contains(LottoBall lottoBall) {
        return lottoBalls.contains(lottoBall);
    }

    public int countMatch(LottoTicket lottoTicket) {
        return (int) lottoBalls.stream()
                .filter(lottoTicket::contains)
                .count();
    }

    public List<LottoBall> getLottoBalls() {
        return lottoBalls;
    }
}
