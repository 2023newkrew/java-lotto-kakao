/**
 * 로또 번호를 가지는 객체
 * 1이상 45이하의 정수를 가진다
 */
package model;

import model.constant.LottoInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    private final int number;

    static {    // lottoNumber 맵을 만든다.
        IntStream.range(LottoInfo.MIN_NUMBER.valueOf(), LottoInfo.MAX_NUMBER.valueOf())
                .forEach(number -> lottoNumbers.put(number, new LottoNumber(number)));
    }

    private LottoNumber(int number) { //로또 넘버 인스턴스 생성을 막는다.
        this.number = number;
    }

    public static LottoNumber getLottoNumber(int number) { //공을 꺼내는 일을함
        return lottoNumbers.get(number);
    }

    public static List<LottoNumber> getLottoNumbers() { // 로또 공 1~45개를 담는 리스트를 새로 만들어서 리턴한다.
        return new ArrayList<>(lottoNumbers.values()); // 해시맵에 담겨있는 로또 공 인스턴스들..
    }

    public int getNumber() { // 숫자 자체를 꺼내는 기능
        return number;
    }


}

