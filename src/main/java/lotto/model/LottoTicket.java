package lotto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    public static final int VALUES_COUNT = 6;

    private final List<Integer> lottoValues;
    private final Integer bonusNumber;

    public LottoTicket(List<Integer> lottoValues) {
        this(lottoValues, null);
    }

    public LottoTicket(String lottoString, String bonusString) {
        this(parseNumbers(lottoString), parseBonusNum(bonusString));
    }

    public LottoTicket(List<Integer> lottoValues, Integer bonusNumber) {
        validateValuesRange(lottoValues);
        validateValuesCount(lottoValues);
        validateDistinction(lottoValues);
        this.lottoValues = new ArrayList<>(lottoValues);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }


    private void validateValuesRange(List<Integer> lottoValues) {
        for (int value : lottoValues) {
            validateValueRange(value);
        }
    }

    private void validateValueRange(Integer value) {
        if (value != null && (value < MIN_VALUE || value > MAX_VALUE)) {
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

    private void validateBonusNumber(Integer bonusNumber) {
        validateValueRange(bonusNumber);
        if (contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버는 여섯 개의 숫자와 중복되어서는 안됩니다.");
        }
    }

    private static ArrayList<Integer> parseNumbers(String numsString) {
        if (numsString == null || numsString.isEmpty() || numsString.isBlank()) {
            throw new IllegalArgumentException("빈 입력이 들어와서는 안됩니다.");
        }
        return (ArrayList<Integer>) Arrays.stream(numsString.split(", |,"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static Integer parseBonusNum(String string) {
        if (string.isEmpty() || string.isBlank()) {
            return null;
        }
        return Integer.parseInt(string);
    }

    public boolean contains(Integer bonusNumber) {
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

    public Grade matchValues(LottoTicket lottoTicket) {
        int sixCount = (int) lottoTicket.getLottoValues()
                .stream()
                .filter(this::contains)
                .count();
        int bonusCount = 0;
        if (sixCount == 5 && lottoTicket.contains(bonusNumber)) {
            bonusCount++;
        }
        return Grade.getGrade(sixCount + 10 * bonusCount);
    }
}
