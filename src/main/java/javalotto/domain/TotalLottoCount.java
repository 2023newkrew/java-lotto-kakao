package javalotto.domain;

public class TotalLottoCount {

    private final LottoCount manuallyLottoCount;
    private final LottoCount automaticallyLottoCount;

    private TotalLottoCount(LottoCount manuallyLottoCount, LottoCount automaticallyLottoCount) {
        this.manuallyLottoCount = manuallyLottoCount;
        this.automaticallyLottoCount = automaticallyLottoCount;
    }

    public static TotalLottoCount of(LottoCount totalLottoCount, LottoCount manuallyLottoCount) {
        LottoCount automaticallyLottoCount = LottoCount.withCount(totalLottoCount.getCount() - manuallyLottoCount.getCount());
        return new TotalLottoCount(manuallyLottoCount, automaticallyLottoCount);
    }

    public LottoCount getManuallyLottoCount() {
        return manuallyLottoCount;
    }

    public LottoCount getAutomaticallyLottoCount() {
        return automaticallyLottoCount;
    }

    @Override
    public String toString() {
        return String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manuallyLottoCount.getCount(), automaticallyLottoCount.getCount());
    }
}
