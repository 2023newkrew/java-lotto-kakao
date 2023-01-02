package lotto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoCompany {

    private final WinningNumbers winningNumbers;

    public LottoCompany(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public Prizes judge(List<Lotto> lottos) {
        if (lottos == null || lottos.isEmpty()) {
            throw new IllegalArgumentException("구매한 로또가 없습니다.");
        }

        Map<Prize, Long> prizeMap = lottos.stream()
                .map(winningNumbers::judge)
                .collect(Collectors.toMap(p -> p, p -> 1L, Long::sum));
        return new Prizes(prizeMap);
    }
}
