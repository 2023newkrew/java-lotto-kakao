package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class WinningLottoWithBonusTest {

    private static WinningLottoWithBonus winningLottoWithBonus;
    private static Lotto winningLotto;
    private static LottoNumber bonusNumber;

    @BeforeAll
    static void setUp() {
        winningLotto = Lotto.getManualLotto("1, 2, 3, 4, 5, 6");
        bonusNumber = new LottoNumber(winningLotto, "7");
        winningLottoWithBonus = new WinningLottoWithBonus(winningLotto, bonusNumber);
    }

    @DisplayName("보너스 번호 일치하는 경우 참 반환 테스트")
    @Test
    void isBonusTest(){
        Lotto lottoWithBonusNumber = Lotto.getManualLotto("7, 8, 9, 10, 11, 12");
        assertThat(winningLottoWithBonus.isBonus(lottoWithBonusNumber)).isTrue();
    }

    @DisplayName("보너스 번호 일치하지 않는 경우 거짓 반환 테스트")
    @Test
    void isNotBonusTest(){
        Lotto lottoWithoutBonusNumber = Lotto.getManualLotto("8, 9, 10, 11, 12, 13");
        assertThat(winningLottoWithBonus.isBonus(lottoWithoutBonusNumber)).isFalse();
    }

}
