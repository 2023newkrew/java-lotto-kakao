package lotto.model.campany;

import lotto.model.prize.Prize;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoRankingTest {


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class from {

        @DisplayName("맞춘 갯수와 보너스 여부로 등수 반환")
        @ParameterizedTest
        @CsvSource(value = {
                "0, false, NOTHING", "0, true, NOTHING",
                "1, false, NOTHING", "1, true, NOTHING",
                "2, false, NOTHING", "2, true, NOTHING",
                "3, false, FIFTH", "3, true, FIFTH",
                "4, false, FOURTH", "4, true, FOURTH",
                "5, false, THIRD", "5, true, SECOND",
                "6, false, FIRST", "6, true, FIRST",
                "7, false, NOTHING", "7, true, NOTHING"
        })
        void should_returnRanking_when_givenNumbers(int commonCount, boolean hasBonus, LottoRanking lottoRanking) {
            LottoRanking actual = LottoRanking.from(commonCount, hasBonus);

            Assertions.assertThat(actual).isEqualTo(lottoRanking);
        }
    }
}