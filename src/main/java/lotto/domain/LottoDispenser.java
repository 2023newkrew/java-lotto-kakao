package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.strategy.NumberSelectStrategy;

public class LottoDispenser {

    private final LottoDispenserSetting lottoDispenserSettingSetting;

    private int leftoverMoney = 0;

    public LottoDispenser(LottoSetting lottoSetting, NumberSelectStrategy numberSelectStrategy) {
        this(new LottoDispenserSetting(lottoSetting, numberSelectStrategy));
    }

    public LottoDispenser(LottoDispenserSetting lottoDispenserSettingSetting) {
        this.lottoDispenserSettingSetting = lottoDispenserSettingSetting;
    }

    public LottoTicketList getLottoTicketList(int money) {
        int ticketQuantity = calculateTicketQuantity(money);
        List<LottoTicket> lottoTicketList = getLottoTicketListByQuantity(ticketQuantity);
        calculateLeftoverMoney(money, lottoTicketList.size());
        return new LottoTicketList(lottoTicketList);
    }

    private List<LottoTicket> getLottoTicketListByQuantity(int quantity) {
        List<LottoTicket> lottoTicketList = new ArrayList<>();

        for (int i = 0; i < quantity
                && !lottoDispenserSettingSetting.getNumberSelectStrategy().isEnd(); i++) {
            LottoTicket lottoTicket = new LottoTicket(
                    lottoDispenserSettingSetting.getNumberSelectStrategy().select());
            lottoTicketList.add(lottoTicket);
        }

        return lottoTicketList;
    }

    private int calculateTicketQuantity(int money) {
        return money / lottoDispenserSettingSetting.getLottoSetting().getLottoTicketPrice();
    }

    private void calculateLeftoverMoney(int money, int quantity) {
        leftoverMoney += money - quantity
                * lottoDispenserSettingSetting.getLottoSetting().getLottoTicketPrice();
    }

    public int receiveLeftoverMoney() {
        int leftoverMoney = this.leftoverMoney;
        this.leftoverMoney = 0;
        return leftoverMoney;
    }
}
