package lotto.domain;

import java.util.List;

public class UserLottos {
    private final List<LottoNumbers> lottoNumbers;

    public UserLottos(List<LottoNumbers> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumbers> getLottoNumbers() {
        return lottoNumbers;
    }
}
