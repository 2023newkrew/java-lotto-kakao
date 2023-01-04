package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {
    @DisplayName("로또는 6개의 로또 번호를 가진다")
    @Test
    void test1(){
        StubLottoTicketGenerator lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5);
        Assertions.assertThatThrownBy(lottoTicketGenerator::generate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또는 중복된 로또 번호를 가질 수 없다")
    @Test
    void test2(){
        StubLottoTicketGenerator lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 5);
        Assertions.assertThatThrownBy(lottoTicketGenerator::generate)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
