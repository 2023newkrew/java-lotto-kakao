package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketAutoGenerator {
    private final List<LottoNumber> numberDomain;
    private static LottoTicketAutoGenerator instance;

    private LottoTicketAutoGenerator() {
        numberDomain = new ArrayList<>();
        for (int number = 1; number <= 45; number++) {
            numberDomain.add(LottoNumber.valueOf(number));
        }
    }

    public static LottoTicketAutoGenerator getInstance() {
        if (instance == null) {
            instance = new LottoTicketAutoGenerator();
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