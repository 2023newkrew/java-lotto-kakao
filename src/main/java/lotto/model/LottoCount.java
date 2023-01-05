package lotto.model;

public class LottoCount {

    private final Integer lottoCount;

    public LottoCount(Integer count) {
        this.lottoCount = count;
    }

    public void validateManualLottoCount(Integer manualLottoCount) {
        if (manualLottoCount > this.lottoCount) throw new IllegalArgumentException();
    }

    public Integer get() {
        return lottoCount;
    }
}
