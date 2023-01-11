package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;


public class LottoTicketsTest {

    @Test
    void 복권_생성_확인() {
        ArrayList<LottoTicket> lt = new ArrayList<>(Arrays.asList(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6))));
        assertThatCode(() -> new LottoTickets(lt)).doesNotThrowAnyException();

    }
}
