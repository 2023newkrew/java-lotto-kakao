package lotto.domain;

import lotto.domain.exception.DuplicatedBallNumber;
import lotto.domain.exception.InvalidLottoTrial;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoTrialTest {
    @Test
    void 중복된_경우_예외_던지자(){
        List<LottoBallNumber> oneTrial = new ArrayList<>(List.of(
                LottoBallNumber.get(1), LottoBallNumber.get(2), LottoBallNumber.get(3),
                LottoBallNumber.get(4), LottoBallNumber.get(5), LottoBallNumber.get(5)));
        assertThatThrownBy(()->{
            LottoTrial lottoTrial = new LottoTrial.Builder()
                    .addBalls(oneTrial)
                    .build();
        }).isInstanceOf(InvalidLottoTrial.class);
    }
    @Test
    void 여섯개_넣지_않으면_예외_던지자(){
        List<LottoBallNumber> oneTrial = new ArrayList<>(List.of(
                LottoBallNumber.get(1), LottoBallNumber.get(2), LottoBallNumber.get(3),
                LottoBallNumber.get(4), LottoBallNumber.get(5), LottoBallNumber.get(6), LottoBallNumber.get(7)));
        assertThatThrownBy(()->{
            LottoTrial lottoTrial = new LottoTrial.Builder()
                    .addBalls(oneTrial)
                    .build();
        }).isInstanceOf(InvalidLottoTrial.class);
    }
    @Test
    void 랜덤_잘_생성되는가(){
        assertThatCode(()->{
            for (int i=1;i<=100;i++){
                LottoTrial lottoTrial = new LottoTrial.Builder()
                        .addRandomBalls()
                        .build();
            }
        }).doesNotThrowAnyException();
    }
    @Test
    void toString_잘_되는가(){
        Assertions.assertThat(new LottoTrial.Builder().addBalls(new ArrayList<>(List.of(6,2,3,4,5,1))).build().toString())
                .isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

}