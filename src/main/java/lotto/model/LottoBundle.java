package lotto.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoBundle {

    private final List<Lotto> lottos;

    public static LottoBundle from(List<Lotto> lottos) {
        if (isNullOrEmpty(lottos)) {
            throw new IllegalArgumentException("로또가 없습니다.");
        }

        return new LottoBundle(lottos);
    }

    private static boolean isNullOrEmpty(List<Lotto> lottos) {
        return Objects.isNull(lottos) || lottos.isEmpty();
    }

    private LottoBundle(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    public Stream<Lotto> stream() {
        return lottos.stream();
    }
}
