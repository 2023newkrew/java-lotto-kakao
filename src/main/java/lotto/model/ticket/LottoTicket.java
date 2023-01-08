package lotto.model.ticket;

import lotto.model.ranking.WinningNumber;
import lotto.model.ranking.LottoRanking;
import lotto.model.ranking.LottoStats;

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

    public LottoTicket append(LottoTicket other) {
        List<LottoNumber> newLottos = Stream.concat(stream(), other.stream()).collect(Collectors.toList());

        return of(newLottos);
    }

    public LottoStats analyze(WinningNumber winningNumber) {
        List<LottoRanking> rankings = lottos.stream()
                .map(winningNumber::judge)
                .collect(Collectors.toList());

        return LottoStats.from(rankings);
    }

    public Stream<LottoNumber> stream() {
        return lottos.stream();
    }

    @Override
    public String toString() {
        return lottos.toString();
    }
}
