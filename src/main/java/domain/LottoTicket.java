package domain;

import java.util.List;

public class LottoTicket {

    private static final int LOTTO_TICKET_BALL_COUNT = 6;

    private final List<LottoBall> lottoBalls;

    public LottoTicket(List<LottoBall> lottoBalls) {
        validateLottoBalls(lottoBalls);
        this.lottoBalls = lottoBalls;
    }

    private void validateLottoBalls(List<LottoBall> lottoBalls) {
        if (lottoBalls.size() != LOTTO_TICKET_BALL_COUNT) {
            throw new IllegalArgumentException("로또 티켓 한 장은 6개의 로또볼이 필요합니다.");
        }
    }
}
