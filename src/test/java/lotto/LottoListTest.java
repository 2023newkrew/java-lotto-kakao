package lotto;

import lotto.model.Lotto;
import lotto.model.LottoList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottoListTest {

    private final LottoList lottoList = new LottoList(
            Lotto.of(1, 2, 3, 4, 5, 6),
            Lotto.of(7, 14, 21, 28, 35, 42)
    );

    @Test
    @DisplayName("로또 리스트 병합 테스트")
    void should_mergedLottoList_when_mergeSeveralLottoList() {
        LottoList otherLottoList = new LottoList(
                Lotto.of(2, 4, 6, 8, 10, 12)
        );

        assertEquals(lottoList.merge(otherLottoList), new LottoList(
                Lotto.of(2, 4, 6, 8, 10, 12),
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(7, 14, 21, 28, 35, 42)
        ));
    }
}
