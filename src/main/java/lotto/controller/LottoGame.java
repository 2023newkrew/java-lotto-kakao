package lotto.controller;

import lotto.domain.Customer;
import lotto.domain.LottoRandom;
import lotto.domain.LottoTickets;
import lotto.service.LottoStore;

import java.util.List;

public class LottoGame {
    private final LottoStore lottoStore;
    private final Customer customer;

    public LottoGame(int amount, List<String> manual) {
        this.lottoStore = new LottoStore(new LottoRandom());
        this.customer = new Customer(new LottoTickets(
                lottoStore.storeAutoLotto(amount / 10),
                lottoStore.storeManualLotto(manual)),
                amount);
    }

    public Customer getCustomer(){
        return this.customer;
    }
}
