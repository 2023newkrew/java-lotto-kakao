package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLottos {
    private final List<UserLotto> userLottos;

    public UserLottos(List<UserLotto> userLottos) {
        this.userLottos = userLottos;
    }

    public List<UserLotto> getUserLottos() {
        return userLottos;
    }

    public LottoPrizeCountMap getLottoPrizeCountMap(WinningLotto winningLotto) {
        Map<LottoPrize, Integer> prizeCount = new HashMap<>();

        userLottos.forEach(userLotto -> {
            LottoPrize prize = userLotto.getLottoPrize(winningLotto);
            prizeCount.put(prize, prizeCount.getOrDefault(prize, 0) + 1);
        });

        return new LottoPrizeCountMap(prizeCount);
    }


}
