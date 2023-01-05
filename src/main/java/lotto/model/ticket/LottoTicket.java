package lotto.model.ticket;

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

    public Stream<LottoNumber> stream() {
        return lottos.stream();
    }

    @Override
    public String toString() {
        return lottos.toString();
    }
}
