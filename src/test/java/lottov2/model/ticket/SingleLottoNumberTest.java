package lottov2.model.ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}