package dto;

import domain.Lotto;
import domain.LottoBuyer;

import java.util.List;

public class PurchasedLotto {
    private final List<Lotto> manual;
    private final List<Lotto> auto;

    private PurchasedLotto(List<Lotto> manual, List<Lotto> auto) {
        this.manual = manual;
        this.auto = auto;
    }

    public static PurchasedLotto of(LottoBuyer lottoBuyer) {
        return new PurchasedLotto(lottoBuyer.getManualLottos(), lottoBuyer.getAutoLottos());
    }

    public List<Lotto> getManual() {
        return manual;
    }

    public List<Lotto> getAuto() {
        return auto;
    }
}
