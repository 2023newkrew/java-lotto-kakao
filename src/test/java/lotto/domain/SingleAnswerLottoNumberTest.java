package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SingleAnswerLottoNumberTest {

    @RepeatedTest(45)
    void 로또_번호는_1_에서_45_사이여야_한다(RepetitionInfo repetitionInfo) {
        int singleLottoNumber = repetitionInfo.getCurrentRepetition();

        assertThatCode(() -> new SingleLottoNumber(singleLottoNumber)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, 0, 46, 100})
    void 로또_번호는_1_에서_45_사이가_아니면_예외가_발생한다(int singleLottoNumber) {
        assertThatThrownBy(() -> new SingleLottoNumber(singleLottoNumber)).isInstanceOf(IllegalArgumentException.class);
    }
}
