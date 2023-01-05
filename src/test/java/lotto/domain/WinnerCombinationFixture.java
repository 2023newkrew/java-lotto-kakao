package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinnerCombinationFixture {
    private WinnerCombinationFixture() {
    }

    public static WinnerCombination createWinnerCombination(int lottoTicketStart, int lottoTicketEnd, int bonusBall) {
        List<LottoBall> lottoBalls = new ArrayList<>();
        for (int number = lottoTicketStart; number <= lottoTicketEnd; number++) {
            lottoBalls.add(new LottoBall(number));
        }
        return new WinnerCombination(new LottoTicket(lottoBalls), new LottoBall(bonusBall));
    }
}
