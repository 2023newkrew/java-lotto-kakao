package lotto;

import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoNumberGenerateTest {
    @Test
    @DisplayName("로또는 6개의 숫자로 이루어져 있다.")
    void lottoHas6Numbers() {
        assertThat(List.of(1,2,3,4,5,6)).hasSize(6);
    }

    @Test
    @DisplayName("로또 번호는 서로 다른 숫자다.")
    void lottoNumberNotDuplicate() {
        assertThat(
                List.of(1,2,3,4,5,6).stream().distinct().count()
        ).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 번호는 1과 45 사이의 숫자로만 이루어져 있다.")
    void lottoNumberin1To45() {
        assertThat(
                List.of(1,2,3,4,5,6).stream().filter(number -> 1 <= number && number <= 45).count()
        ).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 에러를 던진다.")
    void lottoInvalidNumbersTest1() {
        assertThatThrownBy(()->{
            new LottoNumber(List.of(1,2,3,4));
        }).isInstanceOf(RuntimeException.class);

        assertThatThrownBy(()->{
            new LottoNumber(List.of(1,2,3,4,5,6,7));
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 번호가 1 미만 45초과의 숫자를 가지면 에러를 던진다.")
    void lottoInvalidNumbersTest2() {
        assertThatThrownBy(()->{
            new LottoNumber(Arrays.asList(1, 2, 3, 4, 5, 100));
        }).isInstanceOf(RuntimeException.class);

        assertThatThrownBy(()->{
            new LottoNumber(Arrays.asList(0, 2, 3, 4, 5, 6));
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 에러를 던진다.")
    void lottoInvalidNumberTest3() {
        assertThatThrownBy(()->{
            new LottoNumber(Arrays.asList(1, 1, 3, 4, 5, 6));
        }).isInstanceOf(RuntimeException.class);
    }
}
