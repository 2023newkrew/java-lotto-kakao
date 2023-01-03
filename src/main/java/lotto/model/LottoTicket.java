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
            throw new IllegalArgumentException();
        }
    }

    private void validateDistinction(List<LottoValue> lottoValues) {
        if (lottoValues.stream().distinct().count() != VALUES_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    public boolean contains(LottoValue bonusNumber) {
        return lottoValues.contains(bonusNumber);
    }

    public List<LottoValue> getLottoValues() {
        return new ArrayList<>(lottoValues);
    }
}
