package lotto.domain;

import java.util.List;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_LENGTH = 6;
    private final List<SingleLottoNumber> singleLottoNumbers;

    public LottoNumbers(List<SingleLottoNumber> singleLottoNumbers) {
        validateLottoNumbers(singleLottoNumbers);

        this.singleLottoNumbers = singleLottoNumbers;
    }

    private void validateLottoNumbers(List<SingleLottoNumber> singleLottoNumbers) {
        if (singleLottoNumbers.size() != LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }

        long uniqueNumberSize = singleLottoNumbers.stream().distinct().count();
        if (singleLottoNumbers.size() != uniqueNumberSize) {
            throw new IllegalArgumentException("중복된 로또 번호가 있으면 안됩니다.");
        }
    }

    public boolean containsBonusNumber(SingleLottoNumber bonusNumber) {
        return this.singleLottoNumbers.stream().anyMatch(number -> number.equals(bonusNumber));
    }
}
