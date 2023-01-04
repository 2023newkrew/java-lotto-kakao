package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.domain.LottoBall.MAXIMUM_BALL_NUMBER;
import static lotto.domain.LottoBall.MINIMUM_BALL_NUMBER;
import static lotto.domain.LottoTicket.LOTTO_TICKET_BALL_COUNT;

public class LottoTicketSeller {

    private static final int SINGLE_LOTTO_TICKET_PRICE = 1000;

    private final List<LottoBall> lottoBalls;

    public LottoTicketSeller() {
        this.lottoBalls = initializeLottoBalls();
    }

    private List<LottoBall> initializeLottoBalls() {
        List<LottoBall> lottoBalls = new ArrayList<>();
        for (int number = MINIMUM_BALL_NUMBER; number <= MAXIMUM_BALL_NUMBER; number++) {
            lottoBalls.add(new LottoBall(number));
        }
        return lottoBalls;
    }

    public List<LottoTicket> sellLottoTickets(int payMoney) {
        validatePayMoney(payMoney);
        int lottoTicketCount = payMoney / SINGLE_LOTTO_TICKET_PRICE;
        List<LottoTicket> lottoTicketBought = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            lottoTicketBought.add(generateLottoTicket());
        }
        return lottoTicketBought;
    }

    private void validatePayMoney(int payMoney) {
        if (payMoney < SINGLE_LOTTO_TICKET_PRICE) {
            throw new IllegalArgumentException("최소 1,000원 이상을 지불해야 합니다.");
        }
    }

    private LottoTicket generateLottoTicket() {
        Collections.shuffle(lottoBalls);
        return new LottoTicket(lottoBalls.subList(0, LOTTO_TICKET_BALL_COUNT));
    }

    public int calculateTotalPrice(List<LottoTicket> lottoTickets) {
        return SINGLE_LOTTO_TICKET_PRICE * lottoTickets.size();
    }
}
