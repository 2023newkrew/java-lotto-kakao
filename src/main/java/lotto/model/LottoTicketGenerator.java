package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketGenerator {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private final List<Integer> lottoDomain;
    private static LottoTicketGenerator instance;

    private LottoTicketGenerator() {
        lottoDomain = new ArrayList<>();
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            lottoDomain.add(i);
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
