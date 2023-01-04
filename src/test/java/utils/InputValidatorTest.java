package utils;

import domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    void setUp() {
        inputValidator = new InputValidator();
    }

    @Test
    void 수동으로_구매할_로또_수가_구입금액을_넘으면_예외가_발생한다() {
        // given
        Payment payment = new Payment(14000);
        int numberOfManualLotto = LottoCalculator.calculateNumberOfLotto(payment) + 1;

        // when, then
        assertThatThrownBy(() -> inputValidator.validateNumberOfManualLotto(payment, numberOfManualLotto))
                .isInstanceOf(RuntimeException.class);
    }

}