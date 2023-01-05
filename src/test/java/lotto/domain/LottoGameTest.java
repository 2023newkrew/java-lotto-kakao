package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import lotto.LottoConfig;
import lotto.LottoGradeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoGameTest {

    @Test
    void 로또갯수만큼_로또가_생성된다() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoGame lottoGame = new LottoGame(
                lottoGenerator.generateLottos(30000),
                new WinningLotto(List.of(1,2,3,4,5,6), 7)
        );
        assertThat(lottoGame.getLottos().size()).isEqualTo(30);
    }

    @ParameterizedTest
    @MethodSource("decideLottoArgument")
    void 수익률을_계산할_수_있다(LottoGame lottoGame, float expected) {
        assertThat(lottoGame.getGameResult().getRateOfReturn()).isEqualTo(expected);
    }

    static Stream<Arguments> decideLottoArgument() {
        WinningLotto WINNING_LOTTO = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto FIRST_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto SECOND_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto THIRD_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto FOURTH_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 4, 9, 8));
        Lotto FIFTH_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 10, 9, 8));
        return Stream.of(
                Arguments.arguments(
                        new LottoGame(
                                List.of(FIRST_GRADE_LOTTO, FIRST_GRADE_LOTTO),
                                WINNING_LOTTO
                        ),
                        LottoGradeEnum.FIRST.getPrice() / LottoConfig.LOTTO_PRICE
                ),
                Arguments.arguments(
                        new LottoGame(
                                List.of(SECOND_GRADE_LOTTO, SECOND_GRADE_LOTTO),
                                WINNING_LOTTO
                        ),
                        LottoGradeEnum.SECOND.getPrice() / LottoConfig.LOTTO_PRICE
                ),
                Arguments.arguments(
                        new LottoGame(
                                List.of(THIRD_GRADE_LOTTO, THIRD_GRADE_LOTTO),
                                WINNING_LOTTO
                        ),
                        LottoGradeEnum.THIRD.getPrice() / LottoConfig.LOTTO_PRICE
                ),
                Arguments.arguments(
                        new LottoGame(
                                List.of(FOURTH_GRADE_LOTTO, FOURTH_GRADE_LOTTO),
                                WINNING_LOTTO
                        ),
                        LottoGradeEnum.FOURTH.getPrice() / LottoConfig.LOTTO_PRICE
                ),
                Arguments.arguments(
                        new LottoGame(
                                List.of(FIFTH_GRADE_LOTTO, FIFTH_GRADE_LOTTO),
                                WINNING_LOTTO
                        ),
                        LottoGradeEnum.FIFTH.getPrice() / LottoConfig.LOTTO_PRICE
                )
        );
    }

}