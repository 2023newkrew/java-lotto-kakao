package lottoTest.model;

import java.util.List;
import java.util.stream.Stream;
import lotto.model.LottoNumber;
import lotto.model.LottoRank;
import lotto.model.LottoResult;
import lotto.model.LottoTicket;
import lotto.model.LottoWinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @ParameterizedTest
    @EnumSource(LottoRank.class)
    @DisplayName("LottoResult에 등수 추가 테스트")
    void addLottoRankTest(LottoRank lottoRank) {
        //given
        LottoResult lottoResult = new LottoResult();

        //when
        lottoResult.addLottoRankCount(lottoRank);

        //then
        assertThat(lottoResult.getRankCountMap().get(lottoRank)).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("getTotalRevenueTestGenerator")
    @DisplayName("로또 결과에서 총 상금 계산 테스트")
    void getTotalRevenueTest(LottoResult lottoResult, Integer expected) {
        //given

        //when & then
        assertThat(lottoResult.getTotalRevenue()).isEqualTo(expected);
    }

    private static Stream<Arguments> getTotalRevenueTestGenerator() {
        return Stream.of(
                Arguments.of(new LottoResult(List.of(1, 1, 0, 1, 0, 0)), 2030050000),
                Arguments.of(new LottoResult(List.of(0, 1, 2, 1, 0, 5)), 33050000),
                Arguments.of(new LottoResult(List.of(0, 0, 4, 0, 1, 2)), 6005000)
        );
    }

}
