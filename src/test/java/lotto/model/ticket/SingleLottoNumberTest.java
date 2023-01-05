package lotto.model.ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SingleLottoNumberTest {


    @Nested
    class valueOf {

        @DisplayName("로또 번호가 범위를 벗어날 경우 예외 발생")
        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 46, 47})
        void should_throwException_when_numberOutOfRange(int number) {
            Assertions.assertThatThrownBy(() -> SingleLottoNumber.valueOf(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1 ~ 45 사이의 숫자입니다.");
        }

        @DisplayName("범위에 맞는 로또 번호 생성")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 44, 45})
        void should_returnLottoNumber_when_validNumber(int number) {
            SingleLottoNumber singleLottoNumber = SingleLottoNumber.valueOf(number);

            Assertions.assertThat(singleLottoNumber.intValue()).isEqualTo(number);
        }
    }

    @Nested
    class getAllNumbersInRange {

        @DisplayName("1-45 사이의 숫자들이 생성된다.")
        @Test
        void should_return1To45Numbers_when_calling() {
            List<SingleLottoNumber> expected = IntStream.rangeClosed(1, 45)
                    .mapToObj(SingleLottoNumber::valueOf)
                    .collect(Collectors.toList());

            List<SingleLottoNumber> lottoNumbers = SingleLottoNumber.getAllNumbersInRange();

            Assertions.assertThatCollection(lottoNumbers).isEqualTo(expected);
        }
    }
}