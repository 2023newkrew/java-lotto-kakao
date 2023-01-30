package lotto.domain;

import lotto.domain.constant.LottoRule;

public class Store {

    private final Money money;

    public Store(Money money) {
        if (money.getMoney() < LottoRule.PRICE) {
            throw new IllegalArgumentException("해당 돈으로 로또를 구매할 수 없습니다.");
        }
        this.money = money;
    }

    public int calculateTicketCount() {
        return money.getMoney() / LottoRule.PRICE;
    }
}
