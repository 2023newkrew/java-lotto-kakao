package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountTest {

    private static int totalCount;

    @BeforeEach
    void setUp() {
        totalCount = 10;
    }

    @DisplayName("숫자가 아닌 문자열 입력 시 예외 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "14$"})
    void validateNumberFormatTest(String inputWithCharacter) {
        assertThrows(NumberFormatException.class, () -> new Count(inputWithCharacter, totalCount));
    }

    @DisplayName("입력한 값이 음수일 때 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-3"})
    void validateThrowNegativeArgumentExceptionTest(String negativeInput) {
        assertThrows(IllegalArgumentException.class, () -> new Count(negativeInput, totalCount));
    }

    @DisplayName("뽑고자 하는 수동 로또가 뽑을 수 있는 총 로또 개수보다 클 때 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"11", "12", "13"})
    public void validateThrowIllegalArgumentExceptionTest(String bigInput) {
        assertThrows(IllegalArgumentException.class, () -> new Count(bigInput, totalCount));
    }

    @DisplayName("입력한 값이 정상일 때 예외 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void validateDoesNotThrowAnyExceptionTest(String normalInput) {
        assertDoesNotThrow(() -> new Count(normalInput, totalCount));
    }

    @DisplayName("카운트 생성 예외 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "3", "5", "7", "9"})
    void createCount(String countUnderTotalCount) {
        Assertions.assertDoesNotThrow(() -> new Count(countUnderTotalCount, totalCount));
    }

    @DisplayName("0인지 여부 판별 참 반환 테스트")
    @Test
    void zeroTest() {
        String zero = "0";
        Count zeroCount = new Count(zero, totalCount);
        assertThat(zeroCount.zero()).isTrue();
    }

    @DisplayName("0인지 여부 판별 거짓 반환 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void nonZeroTest(String nonZero) {
        Count zeroCount = new Count(nonZero, totalCount);
        assertThat(zeroCount.zero()).isFalse();
    }

    @DisplayName("나머지 반환 테스트")
    @Test
    void getRemainsTest() {
        String three = "3";
        int remains = 7;
        Count count = new Count(three, totalCount);
        assertThat(count.getRemains()).isEqualTo(remains);
    }

}
