package javalotto.domain;

import javalotto.exception.winninglotto.BonusNumberDuplicateException;
import javalotto.exception.winninglotto.BonusNumberOutOfRangeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    void should_createSuccessfully_when_validLottoAndBonusNumber() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        WinningLotto result = WinningLotto.of(lotto, bonusNumber);

        assertThat(result).isNotNull();
    }

    @Test
    void should_throwException_when_duplicateBonusNumber() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 5;

        assertThatThrownBy(() -> WinningLotto.of(lotto, bonusNumber))
                .isInstanceOf(BonusNumberDuplicateException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void should_throwException_when_outOfRangeBonusNumber(int bonusNumber) {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> WinningLotto.of(lotto, bonusNumber))
                .isInstanceOf(BonusNumberOutOfRangeException.class);
    }

    @ParameterizedTest
    @MethodSource
    void should_returnValidRank(Lotto lotto, Optional<Rank> expectedRank) {
        WinningLotto winningLotto = WinningLotto.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), 7);

        assertThat(winningLotto.getRank(lotto)).isEqualTo(expectedRank);
    }

    static Stream<Arguments> should_returnValidRank() {
        return Stream.of(
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), Optional.of(Rank.FIRST)),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 7)), Optional.of(Rank.SECOND)),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 5, 45)), Optional.of(Rank.THIRD)),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 4, 44, 45)), Optional.of(Rank.FOURTH)),
                Arguments.of(Lotto.from(List.of(1, 2, 3, 43, 44, 45)), Optional.of(Rank.FIFTH)),
                Arguments.of(Lotto.from(List.of(40, 41, 42, 43, 44, 45)), Optional.empty())
        );
    }
}