package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class LottoShopTest {

    @DisplayName("구매 금액에 맞는 로또 티켓 리스트를 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {3_000, 1_001, 15_999})
    void buyLottoTickets(int money) {
        Cost cost = new Cost(money);
        LottoShop lottoShop = new LottoShop(
                new LottoNumbersRandomGenerator(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER));

        LottoTickets lottoTickets = lottoShop.buyLottoTickets(cost);
        assertThat(lottoTickets.countAllTickets())
                .isEqualTo(money / LottoShop.LOTTO_PRICE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, LottoShop.LOTTO_PRICE - 1})
    @DisplayName("로또를 한 장도 못사는 경우에는 예외가 발생한다.")
    void buyLottoTickets_throw(int money) {
        Cost cost = new Cost(money);
        LottoShop lottoShop = new LottoShop(
                new LottoNumbersRandomGenerator(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> lottoShop.buyLottoTickets(cost));
    }

    @DisplayName("수동 로또를 구매하고, 남는 돈으로 자동 로또를 구매한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10})
    void buyLottoTickets_manual(int countOfManuals) {
        LottoNumbersGenerator lottoNumbersGenerator =
                new LottoNumbersRandomGenerator(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER);

        List<LottoNumbers> manualLottoNumbers = IntStream.range(0, countOfManuals)
                .mapToObj(idx -> lottoNumbersGenerator.generate())
                .collect(Collectors.toList());

        LottoShop lottoShop = new LottoShop(
                new LottoNumbersRandomGenerator(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER));

        int money = LottoShop.LOTTO_PRICE * 10;
        Cost cost = new Cost(money);

        LottoTickets lottoTickets = lottoShop.buyLottoTickets(manualLottoNumbers, cost);
        assertThat(lottoTickets.countAllTickets()).isEqualTo(cost.countAvailablePurchases(LottoShop.LOTTO_PRICE));
        assertThat(lottoTickets.countManualTickets()).isEqualTo(countOfManuals);
        assertThat(lottoTickets.countAutoTickets()).isEqualTo(cost.countAvailablePurchases(LottoShop.LOTTO_PRICE) - countOfManuals);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, LottoShop.LOTTO_PRICE, LottoShop.LOTTO_PRICE * 9})
    @DisplayName("수동 로또 구매 금액보다 cost가 모자라는 경우에는 예외가 발생한다.")
    void buyLottoTickets_manual_throw(int money) {
        Cost cost = new Cost(money);

        LottoNumbersGenerator lottoNumbersGenerator =
                new LottoNumbersRandomGenerator(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER);

        List<LottoNumbers> manualLottoNumbers = IntStream.range(0, 10)
                .mapToObj(idx -> lottoNumbersGenerator.generate())
                .collect(Collectors.toList());

        LottoShop lottoShop = new LottoShop(
                new LottoNumbersRandomGenerator(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> lottoShop.buyLottoTickets(manualLottoNumbers, cost));
    }

}
