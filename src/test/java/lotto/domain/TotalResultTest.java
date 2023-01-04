package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalResultTest {
    TotalResult totalResult;
    @BeforeEach
    void initialization(){
        totalResult = new TotalResult();
    }

    @Test
    void 테스트_결과가_올바르게_나타나는가(){
        totalResult.add(new LottoResult(1,true));
        totalResult.add(new LottoResult(1,false));
        totalResult.add(new LottoResult(2,true));
        totalResult.add(new LottoResult(3,true)); // 5000원
        totalResult.add(new LottoResult(4,false)); // 50000원
        totalResult.add(new LottoResult(0,true));
        totalResult.add(new LottoResult(3,false)); // 5000원
        assertThat(Double.compare(totalResult.getSurplusRatio(), (60000.0/7000))).isEqualTo(0);
    }
}
