package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoTicketsTest {

    @Test
    void 수동_복권_생성_확인() {
        LottoTickets lt = LottoTickets.countOf(new ArrayList<>(Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7")), 2);
        System.out.println(lt.getTicket());
    }
}
