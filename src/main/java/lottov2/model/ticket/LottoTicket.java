package lottov2.model.ticket;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class LottoTicket {

    private final List<LottoNumber> lottos;

    public static LottoTicket of(List<LottoNumber> lottos) {
        if (Objects.isNull(lottos)) {
            lottos = List.of();
        }

        return new LottoTicket(lottos);
    }

    private LottoTicket(List<LottoNumber> lottos) {
        this.lottos = lottos;
    }

    public Stream<LottoNumber> stream() {
        return lottos.stream();
    }

    @Override
    public String toString() {
        return lottos.toString();
    }
}
