package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {
    public static final int VALUES_COUNT = 6;
    private final List<LottoValue> lottoValues;

    public LottoTicket(List<LottoValue> lottoValues) {
        validateValuesCount(lottoValues);
        validateDistinction(lottoValues);
        this.lottoValues = new ArrayList<>(lottoValues);
    }

    private void validateValuesCount(List<LottoValue> lottoValues) {
        if (lottoValues.size() != VALUES_COUNT) {
            throw new IllegalArgumentException("입력된 값의 개수가 일치하지 않습니다.");
        }
    }

    private void validateDistinction(List<LottoValue> lottoValues) {
        if (lottoValues.stream().distinct().count() != VALUES_COUNT) {
            throw new IllegalArgumentException("값들이 중복되지 않아야 합니다.");
        }
    }

    private List<LottoValue> getSortedLottoValues() {
        LottoValue lv = new LottoValue();
        ArrayList<LottoValue> sortedLottoValues = new ArrayList<>(lottoValues);
        sortedLottoValues.sort(lv);
        return sortedLottoValues;
    }

    public boolean contains(LottoValue bonusNumber) {
        return lottoValues.contains(bonusNumber);
    }

    public List<LottoValue> getLottoValues() {
        return new ArrayList<>(lottoValues);
    }

    public String toString() {
        List<String> st = new ArrayList<>();
        for (LottoValue lv : getSortedLottoValues()) {
            st.add(Integer.toString(lv.getValue()));
        }
        return "[" + String.join(", ", st) + "]";
    }
}
