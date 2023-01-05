import model.*;
import model.constant.LottoPlace;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static model.constant.LottoPlace.FIRST_PLACE;
import static model.constant.LottoPlace.SECOND_PLACE;
import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    private static LottoTicket lottoTicketData;
    private static LottoWinner lottoWinnerData;

    @BeforeAll
    static void makeLottoTicket() {
        List<Lotto> lottos = new ArrayList<>();

        int[][] inputs= {
                {8, 21, 23, 41, 42, 43},
                {3, 5, 11, 16, 32, 38},
                {7, 11, 16, 35, 36, 44},
                {1, 8, 11, 31, 41, 42},
                {13, 14, 16, 38, 42, 45},
                {7, 11, 30, 40, 42, 43},
                {2, 13, 22, 32, 38, 45},
                {23, 25, 33, 36, 39, 41},
                {1, 3, 5, 14, 22, 45},
                {5, 9, 38, 41, 43, 44},
                {2, 8, 9, 18, 19, 21},
                {13, 14, 18, 21, 23, 35},
                {17, 21, 29, 37, 42, 45},
                {3, 8, 27, 30, 35, 44},
                {1, 2, 3, 4, 5, 7}
        };

        for(int[] input : inputs) {
            List<Integer> tmp = Arrays.stream(input)
                    .boxed()
                    .collect(Collectors.toList());
            lottos.add(new Lotto(asLottoNumbers(tmp)));
        }
        lottoTicketData = new LottoTicket(lottos);

        int[] winInput = {1,2,3,4,5,6};


        List<Integer> tmp = Arrays.stream(winInput)
                .boxed()
                .collect(Collectors.toList());

        lottoWinnerData = new LottoWinner(new Lotto(asLottoNumbers(tmp)),asLottoNumber(7));
    }

    @Test
    void 로또번호_6개를_발급한다() {
        LottoTicket lottoTicket = LottoFactory.createLottoTicket(1000);
        Lotto lotto = lottoTicket.getLottoList().get(0);
        assertThat(lotto.getLottoNumbers()).hasSize(6);
    }

    @Test
    void 로또번호는_1이상_45이하이다() {
        LottoTicket lottoTicket = LottoFactory.createLottoTicket(1000);
        Lotto lotto = lottoTicket.getLottoList().get(0);
        for (LottoNumber lottoNumber : lotto.getLottoNumbers()) {
            assertThat(lottoNumber.getNumber()).isBetween(1, 45);
        }
    }

    @Test
    void 로또번호는_중복되지_않는다() {
        LottoTicket lottoTicket = LottoFactory.createLottoTicket(1000);
        Lotto lotto = lottoTicket.getLottoList().get(0);
        assertThat(lotto.getLottoNumbers()).doesNotHaveDuplicates();
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 5000, 1000, 3000})
    void  로또_구입_금액에_해당하는_로또를_발급한다(final int purchaseMoney) {
        LottoTicket lottos = LottoFactory.createLottoTicket(purchaseMoney);
        assertThat(lottos.getLottoList()).hasSize(purchaseMoney / 1000);
    }

    static List<LottoNumber> asLottoNumbers (List<Integer> lottoNumbers) {
        List<LottoNumber> result = new ArrayList<>();
        for (int number : lottoNumbers)
            result.add(asLottoNumber(number));
        return result;
    }

    static LottoNumber asLottoNumber (Integer lottoNumber) {
        return LottoNumber.getLottoNumber(lottoNumber);
    }

    static Stream<Arguments> lottoData() {
        return Stream.of(
                Arguments.of(asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), 7, FIRST_PLACE),
                Arguments.of(asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), asLottoNumbers(Arrays.asList(1, 2, 4, 5, 6, 8)), 3, SECOND_PLACE)
        );
    }

    @ParameterizedTest
    @MethodSource("lottoData")
    void 로또_번호가_몇_개_일치하는지_계산한다(List<LottoNumber> lottoNumbers, List<LottoNumber> winNumbers, int bonusNumber, LottoPlace answer) {
        Lotto lotto = new Lotto(lottoNumbers);
        LottoWinner lottoWinner = new LottoWinner(new Lotto(winNumbers), LottoNumber.getLottoNumber(bonusNumber));
        assertThat(lottoWinner.getLottoPlace(lotto)).isEqualTo(answer);
    }

    @Test
    void 로또의_당첨금액을_계산한다() {
        LottoResult lottoResult = new LottoResult(lottoWinnerData, lottoTicketData);
        Banker banker = new Banker();
        assertThat(banker.getTotalPrizeMoney(lottoResult)).isEqualTo(30005000);
    }

    @Test
    void 총_수익률을_계산하여_출력한다() {
        LottoResult lottoResult = new LottoResult(lottoWinnerData, lottoTicketData);
        Banker banker = new Banker();
        //(double)banker.getTotalPrizeMoney(lottoResult) / 15000

        //winLottoNumbers.setWinNumbers(winNumbers);
        //winLottoNumbers.setBonusNumber(LottoNumber.getLottoNumber(7));
        assertThat(Math.floor(banker.getTotalPrizeMoney(lottoResult)*100 / 15000)).isEqualTo(200033);
    }

}