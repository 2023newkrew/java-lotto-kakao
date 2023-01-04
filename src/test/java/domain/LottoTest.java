package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @ParameterizedTest
    @MethodSource("provideLotto")
    void 로또_당첨_등수를_계산한다(List<LottoNumbers> lottoNumbersList, LottoNumber bonusBall, Map<Rank, Integer> expectedRankMap) {
        // given
        Lotto lotto = new Lotto(lottoNumbersList);
        LottoNumbers winLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(41),
                new LottoNumber(9)
        ));

        // when
        Map<Rank, Integer> rankMap = lotto.rankEachLotto(winLottoNumbers, bonusBall);

        // then
        for (Rank rank : Rank.values()) {
            assertThat(rankMap.get(rank)).isEqualTo(expectedRankMap.get(rank));
        }
    }

    private static Stream<Arguments> provideLotto() {
        LottoNumbers firstPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(41),
                new LottoNumber(9)
        ));

        LottoNumbers secondorThirdPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(41),
                new LottoNumber(1)
        ));

        LottoNumbers fourthPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(2),
                new LottoNumber(1)
        ));

        LottoNumbers fifthPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(1)
        ));

        LottoNumbers noPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(4),
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(1)
        ));

        List<LottoNumbers> lottoNumbersList = List.of(firstPlaceLottoNumbers, secondorThirdPlaceLottoNumbers, fourthPlaceLottoNumbers, fifthPlaceLottoNumbers, noPlaceLottoNumbers);
        Map<Rank, Integer> expectedRankMap1 = Map.of(
                FIRST_PLACE, 1,
                SECOND_PLACE, 1,
                FOURTH_PLACE, 1,
                FIFTH_PLACE, 1
        );
        Map<Rank, Integer> expectedRankMap2 = Map.of(
                FIRST_PLACE, 1,
                THIRD_PLACE, 1,
                FOURTH_PLACE, 1,
                FIFTH_PLACE, 1
        );


        return Stream.of(
                Arguments.arguments(lottoNumbersList, new LottoNumber(1), expectedRankMap1),
                Arguments.arguments(lottoNumbersList, new LottoNumber(33), expectedRankMap2)
        );
    }


}
