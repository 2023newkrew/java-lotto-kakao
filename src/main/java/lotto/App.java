package lotto;

import lotto.domain.Lottos;
import lotto.domain.Statistics;
import lotto.domain.WinningLotto;
import lotto.util.LottoConverter;
import lotto.util.LottoPayment;
import lotto.util.RandomLottoGenerator;
import lotto.view.LottoView;

import java.util.List;

public class App {
    public static void main(String[] args) {
        LottoView lottoView = new LottoView();
        RandomLottoGenerator generator = new RandomLottoGenerator();
        LottoConverter converter = new LottoConverter();

        int totalLottoAmount = LottoPayment.getAmount(lottoView.inputPurchase());
        int manualLottoAmount = lottoView.inputManualLottoCount();
        int autoLottoAmount = totalLottoAmount - manualLottoAmount;

        Lottos manualLottos = converter.toLottos(lottoView.inputManualLottoNumbers(manualLottoAmount));
        Lottos autoLottos = generator.generateLottos(autoLottoAmount);
        Lottos lottos = Lottos.union(manualLottos, autoLottos);

        lottoView.printAmount(manualLottoAmount, autoLottoAmount);
        lottoView.printLottos(lottos);

        List<Integer> winningLottoNumbers = lottoView.inputWinningLottoNumber();
        Integer bonusNumber = lottoView.inputBonusNumber();
        WinningLotto winningLotto = converter.toWinningLotto(winningLottoNumbers, bonusNumber);

        Statistics stat = new Statistics(lottos, winningLotto);
        lottoView.printStatistics(stat);
        lottoView.printTotalProfitRate(stat);
    }
}
