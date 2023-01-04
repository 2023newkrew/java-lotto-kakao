package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class LottoNumberGenerateTest {
    @Test
    @DisplayName("로또 번호가 6개가 아니면 에러를 던진다.")
    void lottoInvalidNumbersTest1() {
        assertThatThrownBy(()->{
            new Lotto(Stream.of(1,2,3,4).map(LottoNumber::new).collect(Collectors.toList()));
        }).isInstanceOf(RuntimeException.class);

        assertThatThrownBy(()->{
            new Lotto(Stream.of(1,2,3,4,5,6,7).map(LottoNumber::new).collect(Collectors.toList()));
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 번호가 1 미만 45초과의 숫자를 가지면 에러를 던진다.")
    void lottoInvalidNumbersTest2() {
        assertThatThrownBy(()->{
            new Lotto(Stream.of(1,2,3,4,5,100).map(LottoNumber::new).collect(Collectors.toList()));
        }).isInstanceOf(RuntimeException.class);

        assertThatThrownBy(()->{
            new Lotto(Stream.of(0,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toList()));
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 에러를 던진다.")
    void lottoInvalidNumberTest3() {
        assertThatThrownBy(()->{
            new Lotto(Stream.of(1,1,3,4,5,6).map(LottoNumber::new).collect(Collectors.toList()));
        }).isInstanceOf(RuntimeException.class);
    }
}
