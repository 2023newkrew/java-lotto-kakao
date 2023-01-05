/**
 * 로또 번호를 감싸는 일급콜렉션
 * 로또 번호는 6개 이다
 * 로또 번호에는 중복이 없다
 * 불변 객체로 만들 것
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
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
