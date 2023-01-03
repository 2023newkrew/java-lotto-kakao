package domain;

import common.constant.Constants;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(int paidPrice) {
        int count = paidPrice / Constants.PRICE;
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
    }

}
