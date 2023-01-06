package utils;

import domain.LottoNumber;
import domain.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

public class LottoConverter {

    public static LottoNumbers integerListToLottoNumbers(List<Integer> list) {
        return new LottoNumbers(list.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

}
