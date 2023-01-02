package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTest {
    @DisplayName("로또 숫자가 6개인지 확인한다")
    @Test
    void lottoSizeTest() {
        //given
        Lotto lotto = new Lotto();
        //when
        List<Integer> lottoNumbers = lotto.getNumbers();
        //then
        Assertions.assertThat(lottoNumbers).hasSize(6);
    }

    @DisplayName("모든 로또 숫자가 1부터 45 사이인지 확인")
    @Test
    void lottoNumberTest() {
        //given
        Lotto lotto = new Lotto();
        //when
        List<Integer> lottoNumbers = lotto.getNumbers();
        //then
        for (Integer lottoNumber : lottoNumbers) {
            Assertions.assertThat(lottoNumber).isBetween(1, 45);
        }
    }
}
