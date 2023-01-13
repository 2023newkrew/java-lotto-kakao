package domain;

import java.util.Arrays;

public class StubLottoNumbersGenerator{

    private final int[] values;
    public StubLottoNumbersGenerator(int ... values){
        this.values = values;

    }

    public LottoNumbers generate() {
        return new LottoNumbers(Arrays.stream(values)
                .mapToObj(LottoNumber::new)
                .toList());
    }
}
