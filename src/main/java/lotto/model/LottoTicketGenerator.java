package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketGenerator {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private static final List<Integer> lottoDomain = IntStream.range(MIN_VALUE, MAX_VALUE).boxed().collect(Collectors.toList());

    public static LottoTicket generate() {
        Collections.shuffle(lottoDomain);
        return new LottoTicket(lottoDomain.subList(0, LottoTicket.VALUES_COUNT));
    }
}
