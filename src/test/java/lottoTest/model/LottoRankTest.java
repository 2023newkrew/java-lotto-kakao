package lottoTest.model;

import lotto.model.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static lotto.model.LottoRank.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {
    @ParameterizedTest
    @MethodSource("fromCountAndBonusTestGenerator")
    @DisplayName("맞춘 로또 번호와 보너스 번호가 주어지면 등수 반환")
    public void fromCountAndBonusTest(Integer count, boolean bonusBall, LottoRank expected) {
        //given

        //when & then
        assertThat(LottoRank.fromCountAndBonus(count, bonusBall)).isEqualTo(expected);
    }

    private static Stream<Arguments> fromCountAndBonusTestGenerator() {
        return Stream.of(
                Arguments.of(6, false, RANK1),
                Arguments.of(5, true, RANK2),
                Arguments.of(5, false, RANK3),
                Arguments.of(4, false, RANK4),
                Arguments.of(4, true, RANK4),
                Arguments.of(3, true, RANK5),
                Arguments.of(3, false, RANK5),
                Arguments.of(2, true, RANK6),
                Arguments.of(1, true, RANK6),
                Arguments.of(0, true, RANK6),
                Arguments.of(2, false, RANK6),
                Arguments.of(1, false, RANK6),
                Arguments.of(0, false, RANK6)
        );
    }

}
