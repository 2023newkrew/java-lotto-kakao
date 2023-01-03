package lotto.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class LottoTicketAutoGeneratorTest {
    @Test
    void 난수_값_6개_생성시_예외없이_LottoTicket_이_생성되어야함() {
        assertThatCode(() -> LottoTicketAutoGenerator.getInstance().generate())
                .doesNotThrowAnyException();
    }
}