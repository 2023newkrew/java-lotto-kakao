package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class AmountTest {
    private LottoTicket userTicket1;
    private LottoTicket userTicket2;
    
    @BeforeEach
    public void setUp() {
        List<Integer> userTicketList1 = List.of(1, 2, 3, 8, 9, 11);
        List<Integer> userTicketList2 = List.of(1, 2, 3, 4, 9, 12);
        this.userTicket1 = new LottoTicket(new ArrayList<>(userTicketList1.stream().map(LottoNumber::new)
                .collect(Collectors.toList())));
        this.userTicket2 = new LottoTicket(new ArrayList<>(userTicketList2.stream().map(LottoNumber::new)
                .collect(Collectors.toList())));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {14000, 15300, 17800})
    @DisplayName("입력된 금액에 맞게 로또 티켓이 생성되어야 한다.")
    void lottoBuyTest(int cost) {
        LottoController lottoController = new LottoController(cost);
        lottoController.registerRandomLotto(cost);
        assertThat(lottoController.getLottoTicketCount()).isEqualTo(cost / 1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0})
    @DisplayName("1000원 미만의 금액이 들어온다면 예외를 발생한다.")
    void lottoLowerThan1000Test(int amount) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoTickets(amount));
    }

    @Test
    @DisplayName("구매한 로또의 개수는 수동과 자동의 합과 같다")
    void lottoCountSummary(){
        LottoTickets lottoTickets = new LottoTickets(14000);
        lottoTickets.createRandomTickets(12);
        lottoTickets.createManualTicket(userTicket1);
        lottoTickets.createManualTicket(userTicket2);
        Assertions.assertThat(lottoTickets.getLottoTicketCount()).isEqualTo(14);
    }
}