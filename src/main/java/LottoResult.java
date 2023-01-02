import java.util.Map;

public class LottoResult {
    private Map<LottoStatus, Integer> lottosStatus;

    LottoResult(Map<LottoStatus, Integer> lottosStatus) {
        this.lottosStatus = lottosStatus;
    }

    public Integer get(LottoStatus lottoStatus) {
        return lottosStatus.get(lottoStatus);
    }
}
