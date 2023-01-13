package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersStoreTest {

    LottoTicketStore lottoTicketStore;
    LottoNumbers defaultLottoNumbers;

    @BeforeEach
    void setUo(){
        lottoTicketStore = new LottoTicketStore(new RandomLottoNumbersGenerator());
        defaultLottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new)
                .toList());
    }

    @DisplayName("수동 로또 구매 시, 금액이 부족할 경우 로또를 구매할 수 없다.")
    @Test
    void test1(){
        Assertions.assertThatThrownBy(() -> {
                    lottoTicketStore.purchaseManualLotto(List.of(defaultLottoNumbers), LottoTicketStore.MANUAL_LOTTO_TICKET_COST - 1);
                }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동 로또 구매 시, 구매한 만큼의 티켓을 반환해야한다.")
    @Test
    void test2(){
        assertThat(lottoTicketStore.purchaseManualLotto(List.of(defaultLottoNumbers, defaultLottoNumbers), LottoTicketStore.MANUAL_LOTTO_TICKET_COST * 2).size())
                .isEqualTo(2);
    }

    @DisplayName("자동 로또 구매 시, 금액이 부족할 경우 로또를 구매할 수 없다.")
    @Test
    void test4(){
        Assertions.assertThatThrownBy(() -> lottoTicketStore.purchaseAutoLotto(1, LottoTicketStore.MANUAL_LOTTO_TICKET_COST - 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동 로또 구매 시, 구매한 만큼의 티켓을 반환해야한다.")
    @Test
    void test5(){
        assertThat(lottoTicketStore.purchaseAutoLotto(2, LottoTicketStore.MANUAL_LOTTO_TICKET_COST * 2).size())
                .isEqualTo(2);
    }
}
