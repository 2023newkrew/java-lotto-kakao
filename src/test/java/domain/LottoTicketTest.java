package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {
    @DisplayName("로또 티켓은 6개의 로또 번호를 가진다")
    @Test
    void test1(){
        StubLottoTicketGenerator lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5);
        Assertions.assertThatThrownBy(lottoTicketGenerator::generate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 티켓은 중복된 로또 번호를 가질 수 없다")
    @Test
    void test2(){
        StubLottoTicketGenerator lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 5);
        Assertions.assertThatThrownBy(lottoTicketGenerator::generate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 티켓은 서로 다른 로또 티켓과 일치하지 않는 로또 번호를 찾을 수 있어야 한다.")
    @Test
    void test3(){
        LottoTicket lottoTicket1 = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 6).generate();
        LottoTicket lottoTicket2 = new StubLottoTicketGenerator(4, 5, 1, 10, 11, 12).generate();

        List<LottoNumber> unMatchLottoNumbers = lottoTicket1.findUnMatchLottoNumbers(lottoTicket2);
        assertThat(unMatchLottoNumbers)
                .hasSize(3)
                .contains(new LottoNumber(2))
                .contains(new LottoNumber(3))
                .contains(new LottoNumber(6));
    }

    @DisplayName("로또 티켓은 어떤 로또 번호가 포함되었는지 알 수 있어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void test4(int lottoNumber){
        LottoTicket lottoTicket = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 6).generate();

        assertThat(lottoTicket.contains(new LottoNumber(lottoNumber))).isTrue();
    }

    @DisplayName("로또 티켓은 어떤 로또 번호가 포함되지 않았는지 알 수 있어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10, 45})
    void test5(int lottoNumber){
        LottoTicket lottoTicket = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 6).generate();

        assertThat(lottoTicket.contains(new LottoNumber(lottoNumber))).isFalse();
    }
}
