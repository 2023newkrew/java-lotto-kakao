package domain;

import common.state.Result;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    private static Lottos lottos;
    private static WinningLotto winningLotto;
    private static Lotto lotto;
    private static LottoNumber bonusNumber;

    @BeforeAll
    static void setUp() {
        lottos = new Lottos();
        lotto = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 3, 4, 5, 6"));
        bonusNumber = new LottoNumber(lotto, "7");
        winningLotto = new WinningLotto(lotto, bonusNumber);
    }

    @DisplayName("수동 로또 번호 추가 시 로또 목록에 포함되어 있는지 확인 테스트")
    @Test
    void addManualLottoTest() {
        String newLottoNumbers = "8, 21, 23, 41, 42, 43";
        Lotto newLotto = LottoFactory.getLotto(new ManualLottoGenerator(newLottoNumbers));
        lottos.addManualLotto(newLotto);
        assertThat(lottos.containsLotto(newLotto)).isTrue();
    }

    @DisplayName("자동 로또 번호 생성 시 사이즈 증가 확인 테스트")
    @Test
    void addAutoLottosTest() {
        int beforeSize = lottos.getSize();
        int autoLottoCount = 3;
        lottos.addAutoLottos(autoLottoCount);
        int afterSize = lottos.getSize();
        assertThat(afterSize).isEqualTo(beforeSize + autoLottoCount);
    }

    @DisplayName("당첨 통계 확인 테스트")
    @Test
    void getTotalResultTest() {
        Lotto matchZero = LottoFactory.getLotto(new ManualLottoGenerator("7, 8, 9, 10, 11, 12"));
        Lotto matchOne = LottoFactory.getLotto(new ManualLottoGenerator("1, 7, 8, 9, 10, 11"));
        Lotto matchTwo = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 7, 8, 9, 10"));
        Lotto matchThree = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 3, 7, 8, 9"));
        Lotto matchFiveBonus = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 3, 4, 5, 7"));
        Lotto matchSix = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 3, 4, 5, 6"));
        lottos.addManualLotto(matchZero);
        lottos.addManualLotto(matchOne);
        lottos.addManualLotto(matchTwo);
        lottos.addManualLotto(matchThree);
        lottos.addManualLotto(matchFiveBonus);
        lottos.addManualLotto(matchSix);
        Map<Result, Integer> expected = Map.of(Result.NONE, 3, Result.FIFTH_PLACE, 1, Result.SECOND_PLACE, 1, Result.FIRST_PLACE, 1);
        assertThat(lottos.getTotalResult(winningLotto)).isEqualTo(new TotalResult(expected));
    }

    @DisplayName("로또 목록이 특정 로또를 포함하고 있을 때 참 반환 테스트")
    @Test
    void containsLottoTest() {
        Lotto lotto = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 3, 4, 5, 6"));
        lottos.addManualLotto(lotto);
        assertThat(lottos.containsLotto(lotto)).isTrue();
    }

    @DisplayName("로또 목록이 특정 로또를 포함하고 있지 않을 때 거짓 반환 테스트")
    @Test
    void doesNotContainLottoTest() {
        Lotto lotto = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 3, 4, 5, 6"));
        assertThat(lottos.containsLotto(lotto)).isFalse();
    }

}
