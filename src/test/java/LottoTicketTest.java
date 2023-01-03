import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 용지를 생성하는 기능")
    void createLottoTicketTest(){
        LottoTicket lottoTicket = new LottoTicket((List.of(1,2,3,4,5,6)));

    }

    @Test
    @DisplayName("로또 번호는 숫자 중복 없음")
    void checkDuplicateLottoTicketTest(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                    new LottoTicket(List.of(1,2,3,4,5,5))
                );
    }

    @Test
    @DisplayName("로또 번호는 1~45 만 가능")
    void checkLottoNumberDomainTest(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(List.of(1,2,3,4,5,-1))
                );

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(List.of(1,2,3,4,5,46))
                );
    }


}
