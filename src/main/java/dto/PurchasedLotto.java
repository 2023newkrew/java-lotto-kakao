package dto;

import domain.Lotto;

import java.util.List;

public class PurchasedLotto {
    private final List<Lotto> manual;
    private final List<Lotto> auto;

    public PurchasedLotto(List<Lotto> manual, List<Lotto> auto) {
        this.manual = manual;
        this.auto = auto;
    }

    public List<Lotto> getManual() {
        return manual;
    }

    public List<Lotto> getAuto() {
        return auto;
    }
}
