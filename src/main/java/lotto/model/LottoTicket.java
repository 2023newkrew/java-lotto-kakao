package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<LottoValue> lottoValues;

    public LottoTicket(List<LottoValue> lottoValues) {
        validateDistinction(lottoValues);
        this.lottoValues = new ArrayList<>(lottoValues);
    }

    private static void validateDistinction(List<LottoValue> lottoValues) {
        if (lottoValues.size() != 6) {
            throw new IllegalArgumentException();
        }
        if (lottoValues.stream().distinct().collect(Collectors.toList()).size() != 6) {
            throw new IllegalArgumentException();
        }
    }
}
