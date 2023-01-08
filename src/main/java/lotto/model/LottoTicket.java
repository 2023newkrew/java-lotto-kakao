package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    public static final int VALUES_COUNT = 6;

    private final List<Integer> lottoValues;

    public LottoTicket(List<Integer> lottoValues) {
        validateValuesRange(lottoValues);
        validateValuesCount(lottoValues);
        validateDistinction(lottoValues);
        this.lottoValues = new ArrayList<>(lottoValues);
    }

    private void validateValuesRange(List<Integer> lottoValues) {
        for (int value : lottoValues) {
            validateValueRange(value);
        }
    }

    private void validateValueRange(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException("LottoValue 는 1~45의 정수 값이어야 한다.");
        }
    }

    private void validateValuesCount(List<Integer> lottoValues) {
        if (lottoValues.size() != VALUES_COUNT) {
            throw new IllegalArgumentException("입력된 값의 개수가 일치하지 않습니다.");
        }
    }

    private void validateDistinction(List<Integer> lottoValues) {
        if (lottoValues.stream().distinct().count() != VALUES_COUNT) {
            throw new IllegalArgumentException("값들이 중복되지 않아야 합니다.");
        }
    }

    private List<Integer> getSortedLottoValues() {
        ArrayList<Integer> sortedLottoValues = new ArrayList<>(lottoValues);
        Collections.sort(sortedLottoValues);
        return sortedLottoValues;
    }

    public boolean contains(int bonusNumber) {
        return lottoValues.contains(bonusNumber);
    }

    public List<Integer> getLottoValues() {
        return new ArrayList<>(lottoValues);
    }

    public String toString() {
        List<String> st = new ArrayList<>();
        for (int value : getSortedLottoValues()) {
            st.add(Integer.toString(value));
        }
        return "[" + String.join(", ", st) + "]";
    }
}
