package lotto.domain;

import lotto.domain.exception.DuplicatedBallNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinNumberTest {
    @Test
    void 보너스_숫자가_원래_숫자와_겹치면_예외(){
        assertThatThrownBy(()-> {
            WinNumber winNumber = new WinNumber(
                    new LottoTrial.Builder().addBalls(List.of(1,2,3,4,5,6)).build(), LottoBallNumber.get(6));
        }).isInstanceOf(DuplicatedBallNumber.class);
    }
    @Test
    void 보너스_숫자가_원래_숫자와_안겹치면_정상실행(){
        assertThatCode(()-> {
            WinNumber winNumber = new WinNumber(
                    new LottoTrial.Builder().addBalls(List.of(1,2,3,4,5,6)).build(), LottoBallNumber.get(7));
        }).doesNotThrowAnyException();
    }

    @Test
    void trial에_대한_결과를_잘_반환하는가(){
        WinNumber winNumber = new WinNumber(
                new LottoTrial.Builder().addBalls(List.of(1,2,3,4,5,6)).build(),
                LottoBallNumber.get(7));

        Assertions.assertThat(winNumber.compareLotto(
                new LottoTrial.Builder().addBalls(List.of(1,2,3,4,5,7)).build()))
                .isEqualTo(LottoResult.get(5,true));

        Assertions.assertThat(winNumber.compareLotto(
                new LottoTrial.Builder().addBalls(List.of(4,2,3,1,8,9)).build()))
                .isEqualTo(LottoResult.get(4,false));
    }
}