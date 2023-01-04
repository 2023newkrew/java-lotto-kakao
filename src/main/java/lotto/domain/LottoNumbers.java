package lotto.domain;

import java.util.List;

public class LottoNumbers {
    public static final int LOTTO_NUMBER_LENGTH = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }

        long uniqueNumberSize = lottoNumbers.stream().distinct().count();
        if (lottoNumbers.size() != uniqueNumberSize) {
            throw new IllegalArgumentException("중복된 로또 번호가 있으면 안됩니다.");
        }
    }

    public boolean containsLottoNumber(LottoNumber lottoNumber) {
        return this.lottoNumbers.stream()
                .anyMatch(lottoNumber::equals);
    }

    public int countMatchNumber(LottoNumbers other) {
        return (int) this.lottoNumbers.stream()
                .filter(other::containsLottoNumber)
                .count();
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        message.append("[");
        this.lottoNumbers.forEach(e -> message.append(e.toString()).append(","));
        message.append("]");

        return message.toString();
    }

}
