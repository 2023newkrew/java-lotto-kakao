package lotto;

import lotto.model.ranking.LottoRanking;
import lotto.model.ticket.SingleLottoNumber;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtil {

    public static Set<SingleLottoNumber> toSingleLottoNumbers(Set<Integer> numbers) {
        return numbers.stream()
                .map(SingleLottoNumber::valueOf)
                .collect(Collectors.toSet());
    }

    public static List<LottoRanking> repeatRankings(LottoRanking ranking, int count) {
        return IntStream.range(0, count)
                .mapToObj(ignore -> ranking)
                .collect(Collectors.toList());
    }
}
