import domain.lotto.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class LottoTicketTest {


    @Test
    @DisplayName("로또 번호는 중복 없는 숫자 6자리")
    void checkDuplicateLottoTicketTest(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                    new LottoTicket(List.of(1,2,3,4,5,5))
                );
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(List.of(1,2,3,4,5))
                );
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(List.of(1,2,3,4,5,6,7))
                );
    }

    @Test
    @DisplayName("로또 번호는 1~45 만 가능")
    void checkLottoNumberDomainTest(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(List.of(0,1,2,3,4,5))
                );

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(List.of(1,2,3,4,5,46))
                );
    }


}
