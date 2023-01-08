package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import lotto.LottoConfig;
import lotto.LottoGradeEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoGameTest {

    private Customer customer;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        customer = new Customer(10000);
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @AfterEach
    void tearDown() {
        customer = null;
        winningLotto = null;
    }


    @ParameterizedTest
    @MethodSource("decideLottoArgument")
    void 수익률을_계산할_수_있다(List<Lotto> lottos, float expected) {
        customer.registerAutoLottos(lottos);
        LottoGame lottoGame = new LottoGame(customer, winningLotto);
        assertThat(lottoGame.getGameResult().getRateOfReturn()).isEqualTo(expected);
    }

    static Stream<Arguments> decideLottoArgument() {
        Lotto FIRST_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto SECOND_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto THIRD_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto FOURTH_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 4, 9, 8));
        Lotto FIFTH_GRADE_LOTTO = new Lotto(List.of(1, 2, 3, 10, 9, 8));
        return Stream.of(
                Arguments.arguments(
                        List.of(FIRST_GRADE_LOTTO, FIRST_GRADE_LOTTO),
                        LottoGradeEnum.FIRST.getPrice() / LottoConfig.LOTTO_PRICE
                ),
                Arguments.arguments(
                        List.of(SECOND_GRADE_LOTTO, SECOND_GRADE_LOTTO),
                        LottoGradeEnum.SECOND.getPrice() / LottoConfig.LOTTO_PRICE
                ),
                Arguments.arguments(
                        List.of(THIRD_GRADE_LOTTO, THIRD_GRADE_LOTTO),
                        LottoGradeEnum.THIRD.getPrice() / LottoConfig.LOTTO_PRICE
                ),
                Arguments.arguments(
                        List.of(FOURTH_GRADE_LOTTO, FOURTH_GRADE_LOTTO),
                        LottoGradeEnum.FOURTH.getPrice() / LottoConfig.LOTTO_PRICE
                ),
                Arguments.arguments(
                        List.of(FIFTH_GRADE_LOTTO, FIFTH_GRADE_LOTTO),
                        LottoGradeEnum.FIFTH.getPrice() / LottoConfig.LOTTO_PRICE
                )
        );


    }

}