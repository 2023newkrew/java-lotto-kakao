package utils;

import domain.LottoNumbers;
import domain.Payment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    void 금액에_맞게_로또가_생성된다() {
        // given
        Payment payment = new Payment(14000);

        // when
        List<LottoNumbers> lottoNumbers = LottoGenerator.generateLotto(payment);

        // then
        assertThat(lottoNumbers.size()).isEqualTo(LottoCalculator.calculateNumberOfLotto(payment));
    }
}
