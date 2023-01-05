/**
 * 로또 번호를 감싸는 일급콜렉션
 * 로또 번호는 6개 이다
 * 로또 번호에는 중복이 없다
 * 불변 객체로 만들 것
 */
package model;

import exception.LottoNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new LottoNumberException("로또 번호의 개수는 6개 입니다.");
        }
        if (lottoNumbers.size() != lottoNumbers.stream().distinct().count()) {
            throw new LottoNumberException("로또 번호에는 중복이 없어야 합니다.");
        }
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(lottoNumbers.stream().map(LottoNumber::getNumber).map(String::valueOf).collect(Collectors.joining(",")));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
