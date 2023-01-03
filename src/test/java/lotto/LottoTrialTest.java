package lotto;

import lotto.exception.DuplicatedBallNumber;
import lotto.exception.InvalidLottoNumberValue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTrialTest {
    @Test
    void 중복된_경우_예외_던지자(){
        List<Integer> oneTrial = new ArrayList<>(List.of(1,2,3,4,5,5));
        assertThatThrownBy(()->{
            LottoTrial lottoTrial = new LottoTrialManual(oneTrial);
        }).isInstanceOf(DuplicatedBallNumber.class);
    }
    @Test
    void 랜덤_잘_생성되는가(){
        assertThatCode(()->{
            for (int i=1;i<=100;i++){
                LottoTrial lottoTrial = new LottoTrialRandom(new LottoPickerRandom());
            }
        }).doesNotThrowAnyException();
    }

}