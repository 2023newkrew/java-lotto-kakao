package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {

    @DisplayName("당첨 번호에 포함된 숫자는 보너스 번호가 될 수 없다.")
    @Test
    void test(){
        StubLottoTicketGenerator lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = new LottoNumber(6);

        Assertions.assertThatThrownBy(() -> new WinningLotto(lottoTicketGenerator.generate(), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호와 로또 티켓의 번호가 순서와 상관없이 모두 같으면 일치하는 번호의 수는 6이다.")
    @Test
    void test1(){
        StubLottoTicketGenerator lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 6);

        LottoNumber bonusNumber = new LottoNumber(45);
        WinningLotto winningLotto = new WinningLotto(lottoTicketGenerator.generate(), bonusNumber);

        lottoTicketGenerator = new StubLottoTicketGenerator(6, 5, 4, 3, 2, 1);
        LottoTicket lottoTicket = lottoTicketGenerator.generate();

        assertThat(winningLotto.match(lottoTicket))
                .isEqualTo(new LottoMatchResult(6, false));
    }

    @DisplayName("5개의 로또 번호가 동일하고, 나머지 로또 번호가 보너스 번호와 같다면 일치하는 번호의 수는 6개이다.")
    @Test
    void test2(){
        StubLottoTicketGenerator lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 6);

        LottoNumber bonusNumber = new LottoNumber(44);
        WinningLotto winningLotto = new WinningLotto(lottoTicketGenerator.generate(), bonusNumber);

        lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 44);
        LottoTicket lottoTicket = lottoTicketGenerator.generate();

        assertThat(winningLotto.match(lottoTicket))
                .isEqualTo(new LottoMatchResult(6, true));
    }

    @DisplayName("5개의 로또 번호가 동일하고, 나머지 로또 번호가 보너스 번호와 다르다면, 일치하는 번호의 수는 5개이다.")
    @Test
    void test3(){
        StubLottoTicketGenerator lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 6);

        LottoNumber bonusNumber = new LottoNumber(44);
        WinningLotto winningLotto = new WinningLotto(lottoTicketGenerator.generate(), bonusNumber);

        lottoTicketGenerator = new StubLottoTicketGenerator(1, 2, 3, 4, 5, 42);
        LottoTicket lottoTicket = lottoTicketGenerator.generate();

        assertThat(winningLotto.match(lottoTicket))
                .isEqualTo(new LottoMatchResult(5, false));
    }
}
