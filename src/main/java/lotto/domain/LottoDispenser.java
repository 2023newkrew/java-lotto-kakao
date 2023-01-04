package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.strategy.NumberSelectStrategy;

public class LottoDispenser {

    private final LottoDispenserSetting lottoDispenserSettingSetting;

    public LottoDispenser(LottoSetting lottoSetting, NumberSelectStrategy numberSelectStrategy) {
        this(new LottoDispenserSetting(lottoSetting, numberSelectStrategy));
    }

    public LottoDispenser(LottoDispenserSetting lottoDispenserSettingSetting) {
        this.lottoDispenserSettingSetting = lottoDispenserSettingSetting;
    }

    public LottoTicketList getLottoTicketList(int money) {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        int ticketQuantity = calculateTicketQuantity(money);
        for (int i = 0; i < ticketQuantity; i++) {
            LottoTicket lottoTicket = new LottoTicket(
                    lottoDispenserSettingSetting.getNumberSelectStrategy().select());
            lottoTicketList.add(lottoTicket);
        }
        return new LottoTicketList(lottoTicketList);
    }

    private int calculateTicketQuantity(int money) {
        return money / lottoDispenserSettingSetting.getLottoSetting().getLottoTicketPrice();
    }
}
