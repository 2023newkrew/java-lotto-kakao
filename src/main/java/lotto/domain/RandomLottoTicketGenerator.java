package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoTicketGenerator {
    private final List<LottoBall> allLottoBalls;

    public RandomLottoTicketGenerator() {
        allLottoBalls = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            allLottoBalls.add(new LottoBall(i));
        }
    }

    public LottoTicket generate() {
        Collections.shuffle(allLottoBalls);
        List<LottoBall> lottoBalls = new ArrayList<>(allLottoBalls.subList(0, 6));
        return new LottoTicket(lottoBalls);
    }
}
