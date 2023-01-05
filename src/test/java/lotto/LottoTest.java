package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTest {
    @DisplayName("생성자 문자열 입력과 리스트 입력이 동일한 결과 반환")
    @Test
    void constructorInputTest() {
        //given
        Lotto lotto1 = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        //when
        //then
        Assertions.assertThat(lotto1).isEqualTo(lotto2);
    }

    @DisplayName("중복된 숫자로 리스트 받으면 예외 발생")
    @Test
    void duplicateTest() {
        //given
        //when
        //then
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(List.of(1, 1, 1, 1, 1, 1)));
    }

    @DisplayName("입력 숫자가 6개가 아닌 리스트 받으면 예외 발생")
    @Test
    void sizeTest() {
        //given
        //when
        //then
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(List.of(1)));
    }

    @DisplayName("다른 로또와 비교해 같은 번호의 수가 3이라 반환")
    @Test
    void compareTest() {
        //given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        //when
        int count = lotto1.compareWith(lotto2);
        //then
        Assertions.assertThat(count).isEqualTo(3);
    }

    @DisplayName("로또 번호 3이 포함됨을 반환")
    @Test
    void containsTest_true() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = LottoNumber.valueOf(3);
        //when
        //then
        Assertions.assertThat(lotto.containsLottoNumber(lottoNumber)).isTrue();
    }

    @DisplayName("로또 번호 10이 포함되지 않음을 반환")
    @Test
    void containsTest_false() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = LottoNumber.valueOf(10);
        //when
        //then
        Assertions.assertThat(lotto.containsLottoNumber(lottoNumber)).isFalse();
    }
}
