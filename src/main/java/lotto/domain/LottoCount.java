package lotto.domain;

public class LottoCount {

    private int manualLottoCount;

    private int autoLottoCount;

    public LottoCount(int manualLottoCount, int lottoTicketCount) {
        if (manualLottoCount < 0) {
            throw new IllegalArgumentException("수동 로또 수는 음수가 될 수 없습니다");
        }
        if (manualLottoCount > lottoTicketCount) {
            throw new IllegalArgumentException("전체 로또 수 보다 많이 살 수 없습니다.");
        }
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = lottoTicketCount - manualLottoCount;
    }

    public boolean isManualLottoAvailable() {
        return this.manualLottoCount > 0;
    }

    public void decreaseManualCount() {
        this.manualLottoCount--;
    }

    public boolean isAutoLottoAvailable() {
        return this.autoLottoCount > 0;
    }

    public void decreaseAutoCount() {
        this.autoLottoCount--;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
