package lotto.model.ticket;

import lotto.model.ranking.LottoRanking;
import lotto.model.ranking.RankingCounts;
import lotto.model.ranking.WinningNumbers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class LottoTicket {

    private final List<LottoNumber> lottos;


    public static LottoTicket of() {
        return of(null);
    }

    public static LottoTicket of(List<LottoNumber> lottos) {
        if (Objects.isNull(lottos)) {
            lottos = List.of();
        }

        return new LottoTicket(lottos);
    }

    private LottoTicket(List<LottoNumber> lottos) {
        this.lottos = lottos;
    }

    public static LottoTicket createByRandom(long count) {
        validateCount(count);
        List<LottoNumber> lottos = LongStream.range(0, count)
                .mapToObj(ignore -> LottoNumber.createByRandom())
                .collect(Collectors.toList());

        return LottoTicket.of(lottos);
    }

    private static void validateCount(long count) {
        if (count < 0) {
            throw new IllegalArgumentException("생성할 로또의 수량이 0보다 작습니다.");
        }
    }

    public int count() {
        return lottos.size();
    }

    public RankingCounts judge(WinningNumbers winningNumbers) {
        List<LottoRanking> rankings = lottos.stream()
                .map(winningNumbers::rank)
                .collect(Collectors.toList());

        return RankingCounts.from(rankings);
    }

    public Stream<LottoNumber> stream() {
        return lottos.stream();
    }

    public LottoTicket append(LottoTicket other) {
        return LottoTicket.of(concat(other));
    }

    private List<LottoNumber> concat(LottoTicket other) {
        return Stream.concat(
                        stream(),
                        other.stream())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return lottos.toString();
    }
}
