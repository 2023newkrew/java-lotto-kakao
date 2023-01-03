package lotto.domain;

import java.util.List;

public class UserLottos {
    private final List<LottoNumbers> lottoNumbers;
    private final Money money;
    private PrizeCountMap prizeCountMap;

    public UserLottos(List<LottoNumbers> lottoNumbers, Money money) {
        this.lottoNumbers = lottoNumbers;
        this.money = money;
    }
}
