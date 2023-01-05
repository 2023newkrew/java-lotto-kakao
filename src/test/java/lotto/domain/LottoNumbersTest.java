package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_SIZE;
import static lotto.domain.LottoNumbers.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumbersTest {
    static List<Integer> oneToSixList;

    @BeforeAll
    static void init() {
        oneToSixList = IntStream.rangeClosed(1, LOTTO_SIZE).boxed().collect(Collectors.toList());
    }


    @Test
    @DisplayName("여섯개의_LottoNumber로_생성가능하다")
    void 여섯개의_IntegerList로_생성가능하다() {
        //expected
        assertThatCode(() -> createLottoNumbers(oneToSixList)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 7})
    @DisplayName("여섯개가_아닌_LottoNumber로_생성하면_에러가_발생한다")
    void 여섯개가_아닌_LottoNumber로_생성하면_에러가_발생한다(int input) {
        //given
        List<Integer> sizeIsNotSixlottoNumberList = IntStream.rangeClosed(1, input).boxed()
                .collect(Collectors.toList());
        //expected
        assertThatThrownBy(() -> createLottoNumbers(sizeIsNotSixlottoNumberList)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("중복된_수로_생성하면_에러가_발생한다")
    void 중복된_수로_생성하면_에러가_발생한다(int input) {
        //given
        List<Integer> duplicateLottoNumberList = IntStream.rangeClosed(1, 6).boxed().map(i -> input)
                .collect(Collectors.toList());

        //expected
        assertThatThrownBy(() -> createLottoNumbers(duplicateLottoNumberList)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @DisplayName("숫자가_존재하면_트루를_리턴한다")
    void 숫자가_존재하면_True를_리턴한다(int input) {
        //when
        LottoNumbers lottoNumbers = createLottoNumbers(oneToSixList);
        boolean result = lottoNumbers.contains(input);

        //then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9})
    @DisplayName("숫자가_존재하면_트루를_리턴한다")
    void 숫자가_존재하지않으면_False를_리턴한다(int input) {
        //when
        boolean result = createLottoNumbers(oneToSixList).contains(input);

        //then
        assertThat(result).isFalse();
    }
}