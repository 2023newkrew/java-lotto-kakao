package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketGenerator {
    private final List<LottoNumber> numberDomain;
    private static LottoTicketGenerator instance;

    private LottoTicketGenerator() {
        numberDomain = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numberDomain.add(new LottoNumber(i));
        }
    }

    public static LottoTicketGenerator getInstance() {
        if (instance == null) {
            instance = new LottoTicketGenerator();
        }
        return instance;
    }

    public LottoTicket generate() {
        Collections.shuffle(numberDomain);
        return new LottoTicket(
                numberDomain.subList(0, LottoTicket.NUMBERS_SIZE)
        );
    }
}