/**
 * 로또 번호를 감싸는 일급콜렉션
 * 로또 번호는 6개 이다
 * 로또 번호에는 중복이 없다
 * 불변 객체로 만들 것
 */
package model;

import exception.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private final Set<LottoNumber> lottoNumbers;

    public Lotto(final Set<LottoNumber> lottoNumbers) {
//        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
//            throw new LottoNumberCountException();
//        }
//        if (lottoNumbers.size() != lottoNumbers.stream().distinct().count()) {
//            throw new LottoNumberDuplicateException();
//        }

        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) { //여기 다시 손보자
            throw new LottoNumberCountException();
        }
        this.lottoNumbers = new HashSet<>(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lottoNumbers.stream().map(LottoNumber::getNumber).sorted().map(String::valueOf).collect(Collectors.toList()));
        return stringBuilder.toString();
    }
}
