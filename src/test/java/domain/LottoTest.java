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
    private static Lotto lotto;
    private static LottoNumber bonusNumber;

    @BeforeAll
    static void setUp() {
        lotto = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 3, 4, 5, 6"));
        bonusNumber = new LottoNumber(lotto, "7");
        winningLotto = new WinningLotto(lotto, bonusNumber);
    }

    @DisplayName("수동 로또 생성 시 예외 통과 테스트")
    @Test
    void createManualLottoTest() {
        String properInput = "8, 21, 23, 41, 42, 43";
        Assertions.assertDoesNotThrow(() -> LottoFactory.getLotto(new ManualLottoGenerator(properInput)));
    }

    @DisplayName("자동 로또 생성 시 예외 통과 테스트")
    @Test
    void createAutoLottoTest() {
        Assertions.assertDoesNotThrow(() -> LottoFactory.getLotto(AutoLottoGenerator.get()));
    }

//    @DisplayName("자동 로또 생성기로 생성한 로또가 제대로 생성되었는지 확인 테스트")
//    @Test
//    void autoLottoGeneratorTest() {
//        Lotto lotto = Lotto.getLotto(new ManualLottoGenerator("8, 21, 23, 41, 42, 43"));
//        Lotto lottoByAutoLottoGenerator = Lotto.getLotto(new AutoLottoGenerator() {
//            @Override
//            public Lotto generateLotto() {
//                return lotto;
//            }
//        });
//        assertThat(lottoByAutoLottoGenerator).isEqualTo(lotto);
//    }

    @DisplayName("로또와 당첨 로또 간에 일치하는 숫자의 개수 반환 테스트")
    @Test
    void getMatchCountTest() {
        Lotto matchTHREE = LottoFactory.getLotto(new ManualLottoGenerator("1, 2, 3, 7, 8, 9"));
        assertThat(matchTHREE.getMatchCount(lotto)).isEqualTo(3);
    }

    @DisplayName("로또가 특정 숫자를 포함하고 있을 때 참 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void containsNumberTest(int containingNumber) {
        assertThat(lotto.containsNumber(new LottoNumber(containingNumber))).isTrue();
    }

    @DisplayName("로또가 특정 숫자를 포함하고 있지 않을 때 거짓 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 10, 11, 12})
    void doesNotContainNumberTest(int notContainingNumber) {
        assertThat(lotto.containsNumber(new LottoNumber(notContainingNumber))).isFalse();
    }

}
