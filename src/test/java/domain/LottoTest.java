package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    @Test
    void 로또번호는_숫자_6개이어야_한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        // when, then
        assertThatThrownBy(()-> Lotto.ofManual(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호는_중복_될_수_없다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 6, 6);

        // when, then
        assertThatThrownBy(()-> Lotto.ofManual(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또는_가진_로또번호를_알_수_있다() {
        // given
        List<Integer> numbers = List.of(23, 25, 45, 34, 5, 7);
        Lotto lotto = Lotto.ofManual(numbers);

        // when, then
        numbers.forEach(number -> assertTrue(lotto.has(new LottoNumber(number))));
    }

    @Test
    void 로또는_가지지_않은_로또번호를_알_수_있다() {
        // given
        List<Integer> numbers = List.of(23, 25, 45, 34, 5, 7);
        List<Integer> notHasNumbers = List.of(1, 44, 2, 24, 4, 6);
        Lotto lotto = Lotto.ofManual(numbers);

        // when, then
        notHasNumbers.forEach(number -> assertFalse(lotto.has(new LottoNumber(number))));
    }

    @Test
    void 로또는_다른_로또와_동일_번호_개수를_알_수_있다() {
        // given
        Lotto lotto = Lotto.ofManual(List.of(23, 25, 45, 34, 5, 7));
        Lotto other = Lotto.ofManual(List.of(23, 25, 1, 2, 3, 4));

        // when
        Integer result = lotto.matches(other);

        // then
        assertThat(result).isEqualTo(2);
    }
}
