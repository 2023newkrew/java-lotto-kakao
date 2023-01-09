package javalotto.domain;

import javalotto.exception.lotto.InvalidLottoCountException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoCountTest {

    @Test
    void should_throwException_whenNegativeCountGiven() {
        assertThatThrownBy(() -> LottoCount.from(-1))
                .isInstanceOf(InvalidLottoCountException.class);
    }

}