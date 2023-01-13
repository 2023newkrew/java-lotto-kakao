package domain;

import java.util.List;

public interface LottoNumbersGenerator {
    LottoNumbers generate();
    List<LottoNumbers> generate(int lottoCount);
}
