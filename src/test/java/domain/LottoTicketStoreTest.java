package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketStoreTest {

    LottoTicketStore lottoTicketStore;
    List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUo(){
        lottoTicketStore = new LottoTicketStore(new LottoTicketAutoGenerator());
        lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @DisplayName("수동 로또 구매 시, 금액이 부족할 경우 로또를 구매할 수 없다.")
    @Test
    void test1(){
        List<List<LottoNumber>> lottos = List.of(lottoNumbers);
        Wallet wallet = new Wallet(100);
        Assertions.assertThatThrownBy(() -> lottoTicketStore.purchaseLotto(lottos, wallet))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동 로또 구매 시, 구매한 만큼의 티켓을 반환해야한다.")
    @Test
    void test2(){
        List<List<LottoNumber>> lottos = List.of(lottoNumbers, lottoNumbers);
        Wallet wallet = new Wallet(2000);
        ;

        assertThat(lottoTicketStore.purchaseLotto(lottos, wallet).size())
                .isEqualTo(2);
    }

    @DisplayName("수동 로또 구매 시, 금액을 지불해야 한다.")
    @Test
    void test3(){
        List<List<LottoNumber>> lottos = List.of(lottoNumbers);
        Wallet wallet = new Wallet(1000);
        lottoTicketStore.purchaseLotto(lottos, wallet);

        assertThat(wallet.getRemainAmount()).isZero();
        assertThat(wallet.getUsage()).isEqualTo(1000);
    }

    @DisplayName("자동 로또 구매 시, 금액이 부족할 경우 로또를 구매할 수 없다.")
    @Test
    void test4(){
        Wallet wallet = new Wallet(100);
        List<LottoTicket> lottoTickets = lottoTicketStore.purchaseLotto(wallet);

        assertThat(wallet.getRemainAmount()).isEqualTo(100);
        assertThat(lottoTickets.size()).isZero();
    }

    @DisplayName("자동 로또 구매 시, 구매한 만큼의 티켓을 반환해야한다.")
    @Test
    void test5(){
        Wallet wallet = new Wallet(2000);

        assertThat(lottoTicketStore.purchaseLotto(wallet).size())
                .isEqualTo(2);
    }


    @DisplayName("자동 로또 구매 시, 금액을 지불해야 한다.")
    @Test
    void test6(){
        Wallet wallet = new Wallet(2000);
        lottoTicketStore.purchaseLotto(wallet);

        assertThat(wallet.getRemainAmount()).isZero();
        assertThat(wallet.getUsage()).isEqualTo(2000);
    }
}
