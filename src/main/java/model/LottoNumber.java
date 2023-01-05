/**
 * 로또 번호를 가지는 객체
 * 1이상 45이하의 정수를 가진다
 */
package model;

import exception.LottoNumberException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    private final int number;

    static {
        IntStream.range(MIN_NUMBER, MAX_NUMBER)
                .forEach(number -> lottoNumbers.put(number, new LottoNumber(number)));
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber getLottoNumber(int number) {
        if(number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new LottoNumberException("로또 번호는 1이상 45이하입니다.");
        }
        return lottoNumbers.get(number);
    }

    public static List<LottoNumber> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers.values());
    }

    public int getNumber() {
        return number;
    }
}

