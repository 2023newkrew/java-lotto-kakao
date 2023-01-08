package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketGenerator {
    private final List<LottoValue> lottoDomain;
    private static LottoTicketGenerator instance;

    private LottoTicketGenerator() {
        lottoDomain = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoDomain.add(new LottoValue(i));
        }
    }

    public static LottoTicketGenerator getInstance() {
        if (instance == null) {
            instance = new LottoTicketGenerator();
        }
        return instance;
    }

    public LottoTicket generate() {
        Collections.shuffle(lottoDomain);
        return new LottoTicket(lottoDomain.subList(0, LottoTicket.VALUES_COUNT));
    }
}
