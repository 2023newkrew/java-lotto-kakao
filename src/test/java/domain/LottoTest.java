package domain;

import common.state.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    private static WinningLotto winningLotto;
    private static BonusNumber bonusNumber;

    @BeforeAll
    static void setUp() {
        winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6");
        bonusNumber = new BonusNumber(winningLotto, "7");

    }

    @Test
    void createLottoTest() {
        String input = "8, 21, 23, 41, 42, 43";
        Lotto lotto = new Lotto(input);
        Assertions.assertDoesNotThrow(() -> lotto);
    }

    @DisplayName("로또 번호와 당첨 번호가 세 개 이하 일치할 경우 테스트")
    @ParameterizedTest
    @ValueSource(strings = {
            "7, 8, 9, 10, 11, 12",
            "1, 7, 8, 9, 10, 11",
            "1, 2, 7, 8, 9, 10"
    })
    void getResultReturnNONETest(String input) {
        Lotto lotto = new Lotto(input);
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.NONE);
    }

    @DisplayName("로또 번호와 당첨 번호가 세 개 일치할 경우 테스트")
    @Test
    void getResultReturnTRHEETest() {
        Lotto lotto = new Lotto("1, 2, 3, 7, 8, 9");
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.THREE);
    }

    @DisplayName("로또 번호와 당첨 번호가 네 개 일치할 경우 테스트")
    @Test
    void getResultReturnFOURTest() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 8, 9");
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.FOUR);
    }

    @DisplayName("로또 번호와 당첨 번호가 네 개 일치할 경우 테스트")
    @Test
    void getResultReturnFIVETest() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 9");
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.FIVE);
    }

    @DisplayName("로또 번호와 당첨 번호가 네 개 일치할 경우 테스트")
    @Test
    void getResultReturnSIXTest() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.SIX);
    }

    @Test
    void isBonusTest(){
        Lotto lotto = new Lotto("7, 8, 9, 10, 11, 12");
        assertThat(lotto.isBonus( bonusNumber)).isTrue();
    }
    @Test
    void isNotBonusTest(){
        Lotto lotto = new Lotto("8, 9, 10, 11, 12, 13");
        assertThat(lotto.isBonus( bonusNumber)).isFalse();
    }
}
