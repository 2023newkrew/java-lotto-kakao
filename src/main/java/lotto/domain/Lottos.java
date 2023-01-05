package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public int size() {
        return lottos.size();
    }

    public static Lottos union(Lottos a, Lottos b) {
        Lottos lottos = new Lottos();
        a.forEach(lottos::add);
        b.forEach(lottos::add);
        return lottos;
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }
}
