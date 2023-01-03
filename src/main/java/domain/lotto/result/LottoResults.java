package domain.lotto.result;

import java.util.HashMap;
import java.util.Map;

public class LottoResults {

    private static Map<LottoResultType, Integer> lottoResultCount = new HashMap<>();

    public LottoResults() {
        for (LottoResultType lottoResultType : LottoResultType.values()) {
            lottoResultCount.put(lottoResultType, 0);
        }
    }

    //key 값이 부재
    // iter key : hm.keyset()
    // iter key : domain.lotto.result.LottoResultType.values()
    // fail 빼야함

    public Integer getResultCount(LottoResultType lottoResultType) {
        return lottoResultCount.getOrDefault(lottoResultType, 0);
    }

    public void countResult(LottoResultType resultType) {
        lottoResultCount.put(resultType, lottoResultCount.getOrDefault(resultType, 0) + 1);
    }
}
