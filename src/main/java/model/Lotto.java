/**
 * 로또 번호를 감싸는 일급콜렉션
 * 로또 번호는 6개 이다
 * 로또 번호에는 중복이 없다
 * 불변 객체로 만들 것
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers); //방어적 복사.. 인자로 들어온 리스트랑 서로 다른 주소를 가지도록..
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
/*
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(lottoNumbers.stream().map(LottoNumber::getNumber).map(String::valueOf).collect(Collectors.joining(",")));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

 */
}
