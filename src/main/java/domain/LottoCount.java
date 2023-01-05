package domain;

public class LottoCount {
    private final int manualLottoCount;
    private final int automaticLottoCount;

    public LottoCount(int totalLottoCount, int desiredLottoCount) {
        if (totalLottoCount < desiredLottoCount) {
            throw new IllegalArgumentException("수동 구매하려는 로또의 수가 총 구매 가능 로또 수를 초과합니다.");
        }
        this.manualLottoCount = desiredLottoCount;
        this.automaticLottoCount = totalLottoCount - desiredLottoCount;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutomaticLottoCount() {
        return automaticLottoCount;
    }
}
