package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.MatchResult;
import lotto.domain.RandomLottoTicketGenerator;
import lotto.domain.Ranking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {
    @Test
    @DisplayName("수동 & 자동 로또를 정해진 수량만큼 구매한다.")
    void purchaseLottoTickets() {
        RandomLottoTicketGenerator randomLottoGenerator = new RandomLottoTicketGenerator() {
            @Override
            public LottoTicket generate() {
                return new LottoTicket(1, 2, 3, 4, 5, 6);
            }
        };

        LottoMachine lottoMachine = new LottoMachine(randomLottoGenerator);
        List<LottoTicket> purchasedLottos = lottoMachine.purchaseLottoTickets(2000, List.of(List.of(2, 3, 4, 5, 6, 7)));

        assertThat(purchasedLottos).hasSize(2);
        assertThat(purchasedLottos.get(0)).isEqualTo(new LottoTicket(2, 3, 4, 5, 6, 7));
        assertThat(purchasedLottos.get(1)).isEqualTo(new LottoTicket(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또를 0장 구매하면 예외가 발생한다.")
    void zeroLotto() {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoTicketGenerator());
        assertThatThrownBy(() -> lottoMachine.purchaseLottoTickets(0, Collections.EMPTY_LIST))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또를 1장 이상 구매해야 합니다.");
    }

    @Test
    @DisplayName("로또 구입 금액에 거스름돈이 포함되면 예외가 발생한다.")
    void noChange() {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoTicketGenerator());
        assertThatThrownBy(() -> lottoMachine.purchaseLottoTickets(500, Collections.EMPTY_LIST))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 가격은 1000원입니다. 구입 금액으로 1000의 배수를 입력해야 합니다.");
    }

    @Test
    @DisplayName("수동 로또 수량이 구입 금액을 초과하면 예외가 발생한다.")
    void tooManyManualLotto() {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoTicketGenerator());
        assertThatThrownBy(() -> lottoMachine.purchaseLottoTickets(1000, List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6)
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액에 비해 많은 수량의 수동 로또를 구매할 수 없습니다.");
    }

    @Test
    @DisplayName("로또 당첨 결과를 카운트한다.")
    void match() {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoTicketGenerator());
        lottoMachine.setWinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        MatchResult matchResult = lottoMachine.match(List.of(
                new LottoTicket(1, 2, 3, 4, 5, 6),
                new LottoTicket(1, 2, 3, 4, 5, 7),
                new LottoTicket(8, 9, 10, 11, 12, 13)
        ));

        assertThat(matchResult.getCount(Ranking.FIRST)).isEqualTo(1);
        assertThat(matchResult.getCount(Ranking.SECOND)).isEqualTo(1);
        assertThat(matchResult.getCount(Ranking.THIRD)).isEqualTo(0);
        assertThat(matchResult.getCount(Ranking.FOURTH)).isEqualTo(0);
        assertThat(matchResult.getCount(Ranking.FIFTH)).isEqualTo(0);
        assertThat(matchResult.getCount(Ranking.OTHER)).isEqualTo(1);
    }
}