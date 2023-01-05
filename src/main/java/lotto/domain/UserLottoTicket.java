package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLottoTicket {
    private final List<UserLotto> randomLottoTicket;
    private final List<UserLotto> manualLottoTicket;

    public UserLottoTicket(List<UserLotto> randomLottoTicket, List<UserLotto> manualLottoTicket) {
        this.randomLottoTicket = randomLottoTicket;
        this.manualLottoTicket = manualLottoTicket;
    }

    public List<UserLotto> randomLottoTicket() {
        return randomLottoTicket;
    }

    public List<UserLotto> manualLottoTicket() {
        return manualLottoTicket;
    }

    public LottoPrizeCountMap getLottoPrizeCountMap(WinningLotto winningLotto) {
        Map<LottoPrize, Integer> prizeCount = new HashMap<>();

        randomLottoTicket.forEach(userLotto -> {
            LottoPrize prize = userLotto.getLottoPrize(winningLotto);
            prizeCount.put(prize, prizeCount.getOrDefault(prize, 0) + 1);
        });

        manualLottoTicket.forEach(userLotto -> {
            LottoPrize prize = userLotto.getLottoPrize(winningLotto);
            prizeCount.put(prize, prizeCount.getOrDefault(prize, 0) + 1);
        });

        return new LottoPrizeCountMap(prizeCount);
    }


}
