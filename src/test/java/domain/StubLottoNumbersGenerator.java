package domain;

import java.util.ArrayList;
import java.util.List;

public class StubLottoNumbersGenerator{

    private final int[] values;
    public StubLottoNumbersGenerator(int ... values){
        this.values = values;

    }

    public LottoNumbers generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for(int value : values){
            lottoNumbers.add(new LottoNumber(value));
        }
        return new LottoNumbers(lottoNumbers);
    }
}
