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
    private static LottoNumber bonusNumber;

    @BeforeAll
    static void setUp() {
        winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6");
        bonusNumber = new LottoNumber(winningLotto, "7");

    }

    @DisplayName("로또 생성 시 예외 통과 테스트")
    @Test
    void createLottoTest() {
        String properInput = "8, 21, 23, 41, 42, 43";
        Assertions.assertDoesNotThrow(() -> new Lotto(properInput));
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
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.FIFTH_PLACE);
    }

    @DisplayName("로또 번호와 당첨 번호가 네 개 일치할 경우 테스트")
    @Test
    void getResultReturnFOURTest() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 8, 9");
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.FOURTH_PLACE);
    }

    @DisplayName("로또 번호와 당첨 번호가 다섯 개 일치할 경우 테스트")
    @Test
    void getResultReturnFIVETest() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 9");
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.THIRD_PLACE);
    }

    @DisplayName("로또 번호와 당첨 번호가 다섯 개 일치하고, 보너스 번호를 포함하고 있는 경우 테스트")
    @Test
    void getResultReturnFIVEBONUSTest() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 7");
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.SECOND_PLACE);
    }

    @DisplayName("로또 번호와 당첨 번호가 여섯 개 일치할 경우 테스트")
    @Test
    void getResultReturnSIXTest() {
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        assertThat(lotto.getResult(winningLotto, bonusNumber)).isEqualTo(Result.FIRST_PLACE);
    }

    @DisplayName("보너스 번호 일치하는 경우 참 반환 테스트")
    @Test
    void isBonusTest(){
        Lotto lottoWithBonusNumber = new Lotto("7, 8, 9, 10, 11, 12");
        assertThat(lottoWithBonusNumber.isBonus(bonusNumber)).isTrue();
    }

    @DisplayName("보너스 번호 일치하지 않는 경우 거짓 반환 테스트")
    @Test
    void isNotBonusTest(){
        Lotto lottoWithoutBonusNumber = new Lotto("8, 9, 10, 11, 12, 13");
        assertThat(lottoWithoutBonusNumber.isBonus(bonusNumber)).isFalse();
    }
}
