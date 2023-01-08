package lotto.domain;

import static lotto.domain.BonusBall.createBonusBall;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusBallTest {

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,5,6})
    @DisplayName("lottoNumbers에_보너스볼이_있으면_True를_리턴한다")
    void lottoNumbers에_보너스볼이_있으면_True를_리턴한다(int input) {

        //given
        LottoNumbers lottoNumbers = LottoNumbers.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusBall bonusBall = createBonusBall(input);

        //when
        boolean result = bonusBall.hasBonusBall(lottoNumbers);

        //then
        assertThat(result).isTrue();

    }


    @ParameterizedTest
    @ValueSource(ints={7,8,9,10,11,12})
    @DisplayName("lottoNumbers에_보너스볼이_없으면_False를_리턴한다")
    void lottoNumbers에_보너스볼이_없으면_False를_리턴한다(int input) {

        //given
        LottoNumbers lottoNumbers = LottoNumbers.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusBall bonusBall = createBonusBall(input);

        //when
        boolean result = bonusBall.hasBonusBall(lottoNumbers);

        //then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints={0,100,46})
    @DisplayName("보너스볼은_1에서_45가_아닌_수가주어지면_에러가_발생한다")
    void 보너스볼은_1에서_45가_아닌_수가주어지면_에러가_발생한다(int input) {
        assertThatThrownBy(() -> createBonusBall(input)).isInstanceOf(IllegalArgumentException.class);

    }
}