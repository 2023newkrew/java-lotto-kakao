import dto.LottoResultDto;
import dto.LottoWinnerDto;
import model.*;
import model.constant.LottoPlace;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LottoResultTest {

    LottoNumber toLottoNumber(int number) {
        return LottoNumber.getLottoNumber(number);
    }

    Lotto toLotto(List<Integer> numberList) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numberList) {
            lottoNumbers.add(toLottoNumber(number));
        }
        return new Lotto(lottoNumbers);
    }

    @Test
    void 로또들의_등수를_계산하여_출력한다() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(toLotto(Arrays.asList(1, 2, 3, 4, 5, 6))); //1등
        lottos.add(toLotto(Arrays.asList(1, 2, 3, 4, 5, 7))); //2등
        lottos.add(toLotto(Arrays.asList(1, 2, 3, 4, 10, 20)));  //4등
        lottos.add(toLotto(Arrays.asList(3, 4, 5, 6, 25, 35))); //4등
        LottoTicket lottoTicket = new LottoTicket(lottos);

        LottoWinner lottoWinner = new LottoWinner(toLotto(Arrays.asList(1, 2, 3, 4, 5, 6)), toLottoNumber(7));

        Map<LottoPlace, Integer> placeResult = new LottoResult(lottoWinner, lottoTicket).getLottoPlaces();

        Assertions.assertEquals(1, placeResult.getOrDefault(LottoPlace.FIRST_PLACE, 0));
        Assertions.assertEquals(1, placeResult.getOrDefault(LottoPlace.SECOND_PLACE, 0));
        Assertions.assertEquals(0, placeResult.getOrDefault(LottoPlace.THIRD_PLACE, 0));
        Assertions.assertEquals(2, placeResult.getOrDefault(LottoPlace.FOURTH_PLACE, 0));
        Assertions.assertEquals(0, placeResult.getOrDefault(LottoPlace.FIFTH_PLACE, 0));
        Assertions.assertEquals(0, placeResult.getOrDefault(LottoPlace.LOSE, 0));
    }

    @Test
    void 총_수익률을_계산하여_출력한다() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(toLotto(Arrays.asList(10, 12, 13, 14, 15, 17))); //x
        lottos.add(toLotto(Arrays.asList(1, 2, 3, 4, 10, 20)));  //4등
        lottos.add(toLotto(Arrays.asList(3, 4, 5, 6, 25, 35))); //4등
        lottos.add(toLotto(Arrays.asList(3, 4, 5, 7, 25, 35))); //5등
        LottoTicket lottoTicket = new LottoTicket(lottos);
        LottoWinner lottoWinner = new LottoWinner(toLotto(Arrays.asList(1, 2, 3, 4, 5, 6)), toLottoNumber(7));
        LottoResult lottoResult = new LottoResult(lottoWinner, lottoTicket);

        Banker banker = new Banker();
        long winningMoney = banker.getTotalPrizeMoney(lottoResult);

        Assertions.assertEquals(26.25, (double) winningMoney / 4000);
    }
}
