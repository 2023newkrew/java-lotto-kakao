package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoDispenser {

    private final LottoSetting lottoSetting;

    private static final Map<LottoSetting, LottoDispenser> LOTTO_DISPENSER_MAP
            = new HashMap<>();

    private LottoDispenser(LottoSetting lottoSetting) {
        this.lottoSetting = lottoSetting;
    }

    public static LottoDispenser from(LottoSetting lottoSetting) {
        if (!LOTTO_DISPENSER_MAP.containsKey(lottoSetting)) {
            LOTTO_DISPENSER_MAP.put(lottoSetting, new LottoDispenser(lottoSetting));
        }
        return LOTTO_DISPENSER_MAP.get(lottoSetting);
    }

    public LottoTicketList getLottoTicket(int money) {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        int ticketQuantity = calculateTicketQuantity(money);
        for (int i = 0; i < ticketQuantity; i++) {
            LottoTicket lottoTicket = new LottoTicket(
                    lottoSetting.getNumberSelectStrategy().select());
            lottoTicketList.add(lottoTicket);
        }
        return new LottoTicketList(lottoTicketList);
    }

    private int calculateTicketQuantity(int money) {
        return money / lottoSetting.getLottoTicketPrice();
    }
}
