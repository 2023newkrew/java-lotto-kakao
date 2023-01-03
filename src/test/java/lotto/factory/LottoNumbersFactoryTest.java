package lotto.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import lotto.domain.LottoNumbers;
import lotto.generatepolicy.GeneratePolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersFactoryTest {

    class IncreasePolicy implements GeneratePolicy {
        int num = 1;

        @Override
        public int generate() {
            return num++;
        }
    }

    @DisplayName("매개변수가_없으면_기본정책으로_생성한다")
    @Test
    void 매개변수가_없으면_기본정책으로_생성한다() {
        assertThatCode(() -> LottoNumbersFactory.create()).doesNotThrowAnyException();
    }

    @DisplayName("정책을 인자로 전달하면 해당 정책을 반영하여 생성한다")
    @Test
    void 정책을_인자로_전달하면_해당_정책을_반영하여_생성한다() {
        assertThatCode(() -> LottoNumbersFactory.create(new IncreasePolicy())).doesNotThrowAnyException();
    }

    @DisplayName("주어진_정책에_맞는_LottoNumbers를_생성한다")
    @Test
    void 주어진_정책에_맞는_LottoNumbers를_생성한다() {
        assertThat(LottoNumbersFactory.create(new IncreasePolicy())).isEqualTo(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
    }
}