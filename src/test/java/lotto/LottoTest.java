package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.util.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @Test
    @DisplayName("로또 당첨번호가 몇개 인지 반환")
    void iAmRich() {
        Lotto myNumber = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toList()));
        Lotto answer = new Lotto(Stream.of(4,5,6,7,8,9).map(LottoNumber::new).collect(Collectors.toList()));
        assertThat(myNumber.compare(answer)).isEqualTo(3);
    }

    @Test
    @DisplayName("보너스가 포함된 로또 당첨번호 테스트")
    void bonusDogHoney() {
        Lotto myNumber = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toList()));
        LottoNumber bonus = new LottoNumber(5);
        assertThat(myNumber.hasBonus(bonus)).isTrue();
    }

    @Test
    @DisplayName("로또 열장을 발행한다.")
    void tooManyLottos() {
        assertThat(new RandomLottoGenerator().generateLottos(10).size()).isEqualTo(10);
    }
}
