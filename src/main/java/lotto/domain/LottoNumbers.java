package lotto.domain;

import java.util.List;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_LENGTH = 6;
    private final List<SingleLottoNumber> singleLottoNumbers;

    public LottoNumbers(List<SingleLottoNumber> singleLottoNumbers) {
        if (singleLottoNumbers.size() != LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }

        this.singleLottoNumbers = singleLottoNumbers;
    }
}
