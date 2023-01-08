package lotto.model;

import lotto.exception.DuplicatedBallNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoTest {
    @Test
    void 중복된_경우_예외_던지자() {
        List<Integer> oneTrial = new ArrayList<>(List.of(1, 2, 3, 4, 5, 5));
        assertThatThrownBy(() -> {
            Lotto lotto = new LottoManual(oneTrial);
        }).isInstanceOf(DuplicatedBallNumber.class);
    }

    @Test
    void 랜덤_잘_생성되는가() {
        assertThatCode(() -> {
            LottoPickerRandom lottoPickerRandom = new LottoPickerRandom();

            for (int i = 0; i < 100; i++){
                Lotto lotto = new LottoAuto(lottoPickerRandom);
            }
        }).doesNotThrowAnyException();
    }
}