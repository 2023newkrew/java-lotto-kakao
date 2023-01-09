package lotto.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.stream.Collectors;

class LottoTest {
    @Test
    @DisplayName("로또 생성 확인")
    void checkLottoMaker() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        Assertions.assertEquals(Arrays.asList(1,2,3,4,5,6), lotto.getLotto());
    }

    @Test
    @DisplayName("로또 생성 순서 상관 확인")
    void checkLottoMakerSorted() {
        Lotto lotto = new Lotto(Arrays.asList(3,4,5,6,1,2));
        Assertions.assertEquals(Arrays.asList(1,2,3,4,5,6), lotto.getLotto());
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6;FIRST",
            "1,2,3,4,5,7;SECOND",
            "1,2,3,4,5,8;THIRD",
            "1,2,3,4,7,9;FOURTH",
            "1,2,3,8,9,10;FIFTH",
            "1,2,8,9,10,11;DEFAULT",
            "1,8,9,10,11,12,;DEFAULT",
            "8,9,10,11,12,13;DEFAULT"}, delimiter = ';')
    void checkLottoWin(String input, String expect){
        List<Integer> numbers = Arrays.stream(input.split(","))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());

        List<Integer> winNumbers = Arrays.asList(1,2,3,4,5,6);
        int bonus = 7;
        Lotto lotto = new Lotto(numbers);
        Assertions.assertEquals(lotto.checkWin(new WinningNumbers(winNumbers, bonus)).name(), expect);
    }

}