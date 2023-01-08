package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    private Customer customer;
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    @BeforeEach
    void setUp() {
        customer = new Customer(10000);
    }

    @AfterEach
    void tearDown() {
        customer = null;
    }

    @Test
    void 수동_로또은_비어있을때만_등록이_가능하다() {
        customer.registerManualLottos(lottoGenerator.generateLottos(1));
        assertThatThrownBy(
                () -> customer.registerManualLottos(lottoGenerator.generateLottos(1))
        );
    }

    @Test
    void 자동_로또은_비어있을때만_등록이_가능하다() {
        customer.registerAutoLottos(lottoGenerator.generateLottos(1));
        assertThatThrownBy(
                () -> customer.registerAutoLottos(lottoGenerator.generateLottos(1))
        );
    }

    @Test
    void 로또를_구매_가능한_갯수보다_더_많이_등록하려고_하면_예외가_발생한다() {
        assertThatThrownBy(
                () -> customer.registerManualLottos(lottoGenerator.generateLottos(11))
        );
    }

}


