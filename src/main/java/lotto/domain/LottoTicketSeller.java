package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean isManualLottoPossible(int manualLottoCount, int payMoney) {
        return (payMoney < manualLottoCount * SINGLE_LOTTO_TICKET_PRICE);
    }

    public List<LottoTicket> sellManualLottoTickets(List<List<Integer>> manualLottoNumbers) {
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (List<Integer> manualLottoNumber : manualLottoNumbers) {
            List<LottoBall> lottoBalls = manualLottoNumber.stream()
                    .map(LottoBall::new)
                    .collect(Collectors.toList());
            manualLottoTickets.add(new LottoTicket(lottoBalls));
        }
        return manualLottoTickets;
    }

    public List<LottoTicket> sellAutoLottoTickets(int payMoney) {
        int lottoTicketCount = payMoney / SINGLE_LOTTO_TICKET_PRICE;
        List<LottoTicket> lottoTicketBought = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            lottoTicketBought.add(generateAutoLottoTicket());
        }
        return lottoTicketBought;
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
