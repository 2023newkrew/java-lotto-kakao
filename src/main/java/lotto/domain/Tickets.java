package lotto.domain;

import static lotto.LottoConfig.LOTTO_PRICE;
import static lotto.constant.MessageConstant.INVALID_USING_TICKET;

public class Tickets {
    private int count;

    public Tickets(int purchasePrice) {
        this.count = purchasePrice / LOTTO_PRICE;
    }

    public int getCount() {
        return count;
    }

    public void use(int amount) {
        if (count < amount) {
            throw new IllegalArgumentException(INVALID_USING_TICKET);
        }
        count -= amount;
    }
}
