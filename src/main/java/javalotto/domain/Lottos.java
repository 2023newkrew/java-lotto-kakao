package javalotto.domain;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos fromNumbers(List<List<Integer>> lottoNumbers) {
        List<Lotto> lottos = lottoNumbers.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public static Lottos of(Lottos...manyLottos) {
        List<Lotto> allLottos = Arrays.stream(manyLottos)
                .map(lottos -> lottos.lottos)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return new Lottos(allLottos);
    }

    public static Lottos none() {
        return new Lottos(new ArrayList<>());
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public String toString() {
        return lottos.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining("\n"));
    }


}
