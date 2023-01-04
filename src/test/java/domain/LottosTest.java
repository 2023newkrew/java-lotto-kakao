package domain;

import common.state.Result;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    private static Lottos lottos;
    private static WinningLotto winningLotto;
    private static LottoNumber bonusNumber;

    @BeforeAll
    static void setUp() {
        lottos = new Lottos();
        winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6");
        bonusNumber = new LottoNumber(winningLotto, "7");

    }

    @DisplayName("로또 번호 추가 시 사이즈 증가 확인 테스트")
    @Test
    void addTest() {
        int beforeSize = lottos.getSize();
        String newLottoNumbers = "8, 21, 23, 41, 42, 43";
        lottos.add(newLottoNumbers);
        int afterSize = lottos.getSize();
        assertThat(afterSize).isEqualTo(beforeSize + 1);
    }



    @DisplayName("당첨 통계 확인 테스트")
    @Test
    void getTotalResultTest() {
        String matchZero = "7, 8, 9, 10, 11, 12";
        String matchOne = "1, 7, 8, 9, 10, 11";
        String matchTwo = "1, 2, 7, 8, 9, 10";
        String matchThree = "1, 2, 3, 7, 8, 9";
        String matchFiveBonus = "1, 2, 3, 4, 5, 7";
        String matchSix = "1, 2, 3, 4, 5, 6";
        lottos.add(matchZero);
        lottos.add(matchOne);
        lottos.add(matchTwo);
        lottos.add(matchThree);
        lottos.add(matchFiveBonus);
        lottos.add(matchSix);
        Map<Result, Integer> expected = Map.of(Result.NONE, 3, Result.FIFTH_PLACE, 1, Result.SECOND_PLACE, 1, Result.FIRST_PLACE, 1);
        assertThat(lottos.getTotalResult(winningLotto, bonusNumber)).isEqualTo(new TotalResult(expected));
    }

}
