package lotto.domain.dispenser;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.game.LottoSetting;
import lotto.domain.strategy.NumberSelectStrategy;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketList;

public class LottoDispenser {

    private final LottoDispenserSetting lottoDispenserSetting;

    private int leftoverMoney = 0;
    private int ticketCount = 0;

    public LottoDispenser(LottoSetting lottoSetting, NumberSelectStrategy numberSelectStrategy) {
        this(new LottoDispenserSetting(lottoSetting, numberSelectStrategy));
    }

    public LottoDispenser(LottoSetting lottoSetting) {
        this(new LottoDispenserSetting(lottoSetting, new NumberSelectStrategy() {
            @Override
            public List<Integer> select() {
                return null;
            }

            @Override
            public boolean isEnd() {
                return true;
            }
        }));
    }

    public LottoDispenser(LottoDispenserSetting lottoDispenserSetting) {
        this.lottoDispenserSetting = lottoDispenserSetting;
    }

    public void setNumberSelectStrategy(NumberSelectStrategy numberSelectStrategy) {
        lottoDispenserSetting.setNumberSelectStrategy(numberSelectStrategy);
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
                && !lottoDispenserSetting.getNumberSelectStrategy().isEnd(); i++) {
            LottoTicket lottoTicket = new LottoTicket(
                    lottoDispenserSetting.getNumberSelectStrategy().select());
            lottoTicketList.add(lottoTicket);
        }

        addTicketCount(lottoTicketList.size());
        return lottoTicketList;
    }

    private int calculateTicketQuantity(int money) {
        return money / lottoDispenserSetting.getLottoSetting().getLottoTicketPrice();
    }

    private void calculateLeftoverMoney(int money, int quantity) {
        leftoverMoney += money - quantity
                * lottoDispenserSetting.getLottoSetting().getLottoTicketPrice();
    }

    private void addTicketCount(int count) {
        ticketCount += count;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public int receiveLeftoverMoney() {
        int leftoverMoney = this.leftoverMoney;
        this.leftoverMoney = 0;
        return leftoverMoney;
    }
}
