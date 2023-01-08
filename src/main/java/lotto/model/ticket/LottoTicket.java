package lotto.model.ticket;

import lotto.model.ranking.RankingCounts;
import lotto.model.ranking.WinningNumbers;
import lotto.model.ranking.LottoRanking;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

    public int size(){
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

    @Override
    public String toString() {
        return lottos.toString();
    }
}
