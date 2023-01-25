package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.constant.LottoRule;

public class Lotto {

    private final List<SingleLottoNumber> singleLottoNumberList;

    public Lotto(List<SingleLottoNumber> singleLottoNumberList) {
        validateLottoNumbers(singleLottoNumberList);

        this.singleLottoNumberList = new ArrayList<>(singleLottoNumberList);
    }

    private void validateLottoNumbers(List<SingleLottoNumber> singleLottoNumbers) {
        if (singleLottoNumbers.size() != LottoRule.LENGTH) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }

        long uniqueNumberSize = singleLottoNumbers.stream().distinct().count();
        if (singleLottoNumbers.size() != uniqueNumberSize) {
            throw new IllegalArgumentException("중복된 로또 번호가 있으면 안됩니다.");
        }
    }

    public boolean containsLottoNumber(SingleLottoNumber singleLottoNumber) {
        return this.singleLottoNumberList.contains(singleLottoNumber);
    }

    public int countMatchNumber(Lotto other) {
        return (int) this.singleLottoNumberList.stream()
                .filter(other::containsLottoNumber)
                .count();
    }

    public List<SingleLottoNumber> getSingleLottoNumberList() {
        return singleLottoNumberList;
    }
}
