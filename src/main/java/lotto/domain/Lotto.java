package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    public static final int LOTTO_NUMBER_LENGTH = 6;
    private final List<SingleLottoNumber> singleLottoNumberList;

    public Lotto(List<SingleLottoNumber> singleLottoNumberList) {
        validateLottoNumbers(singleLottoNumberList);

        this.singleLottoNumberList = new ArrayList<>(singleLottoNumberList);
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

    public boolean containsLottoNumber(SingleLottoNumber singleLottoNumber) {
        return this.singleLottoNumberList.contains(singleLottoNumber);
    }

    public int countMatchNumber(Lotto other) {
        return (int) this.singleLottoNumberList.stream()
                .filter(other::containsLottoNumber)
                .count();
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        message.append("[");
        this.singleLottoNumberList.forEach(e -> message.append(e.toString()).append(","));
        message.append("]");

        return message.toString();
    }
}
