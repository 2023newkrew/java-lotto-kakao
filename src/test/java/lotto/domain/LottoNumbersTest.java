package lotto.domain;

import static lotto.domain.LottoConstants.SIZE;
import static lotto.domain.LottoNumber.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumbersTest {
    static List<LottoNumber> lottoNumberList;

    @BeforeAll
    static void init() {
        lottoNumberList = IntStream.rangeClosed(1, SIZE).boxed().map(LottoNumber::from)
                .collect(Collectors.toList());
    }


    @Test
    @DisplayName("여섯개의_LottoNumber로_생성가능하다")
    void 여섯개의_LottoNumberList로_생성가능하다() {
        //expected
        assertThatCode(() -> new LottoNumbers(lottoNumberList)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 7})
    @DisplayName("여섯개가_아닌_LottoNumber로_생성하면_에러가_발생한다")
    void 여섯개가_아닌_LottoNumber로_생성하면_에러가_발생한다(int input) {
        //given
        List<LottoNumber> sizeIsNotSixlottoNumberList = IntStream.rangeClosed(1, input).boxed().map(LottoNumber::from)
                .collect(Collectors.toList());
        //expected
        assertThatThrownBy(() -> new LottoNumbers(sizeIsNotSixlottoNumberList)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("중복된_수로_생성하면_에러가_발생한다")
    void 중복된_수로_생성하면_에러가_발생한다(int input) {
        //given
        List<LottoNumber> duplicateLottoNumberList = IntStream.rangeClosed(1, 6).boxed().map(i -> from(input))
                .collect(Collectors.toList());

        //expected
        assertThatThrownBy(() -> new LottoNumbers(duplicateLottoNumberList)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @DisplayName("숫자가_존재하면_트루를_리턴한다")
    void 숫자가_존재하면_True를_리턴한다(int input) {
        //when
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);
        boolean result = lottoNumbers.contains(input);

        //then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9})
    @DisplayName("숫자가_존재하면_트루를_리턴한다")
    void 숫자가_존재하지않으면_False를_리턴한다(int input) {
        //when
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);
        boolean result = lottoNumbers.contains(input);

        //then
        assertThat(result).isFalse();
    }
}