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

    public List<LottoTicket> sellManualLottoTickets(List<List<Integer>> manualLottoNumbers, int payMoney) {
        validateManualLottoPayMoney(manualLottoNumbers.size(), payMoney);
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (List<Integer> manualLottoNumber : manualLottoNumbers) {
            manualLottoTickets.add(LottoTicket.fromNumbers(manualLottoNumber));
        }
        return manualLottoTickets;
    }

    private void validateManualLottoPayMoney(int manualLottoCount, int payMoney) {
        if (payMoney < manualLottoCount * SINGLE_LOTTO_TICKET_PRICE) {
            throw new IllegalArgumentException("가진 금액이 부족합니다.");
        }
    }

    public List<LottoTicket> sellAutoLottoTickets(int payMoney) {
        validatePayMoney(payMoney);
        int lottoTicketCount = payMoney / SINGLE_LOTTO_TICKET_PRICE;
        List<LottoTicket> lottoTicketBought = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            lottoTicketBought.add(generateAutoLottoTicket());
        }
        return lottoTicketBought;
    }

    private void validatePayMoney(int payMoney) {
        if (payMoney < 0) {
            throw new IllegalArgumentException("PayMoney는 음수가 될 수 없습니다.");
        }
    }

    private LottoTicket generateAutoLottoTicket() {
        Collections.shuffle(lottoBalls);
        List<LottoBall> selectedLottoBalls = new ArrayList<>(lottoBalls.subList(0, LOTTO_TICKET_BALL_COUNT));
        return new LottoTicket(selectedLottoBalls);
    }

    public int calculateTotalPrice(List<LottoTicket> lottoTickets) {
        return SINGLE_LOTTO_TICKET_PRICE * lottoTickets.size();
    }
}
