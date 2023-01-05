package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Statistics;
import lotto.util.LottoPayment;
import lotto.util.RandomLottoGenerator;
import lotto.view.LottoView;

public class App {
    public static void main(String[] args) {
        LottoView lottoView = new LottoView();
        RandomLottoGenerator generator = new RandomLottoGenerator();
        int totalLottoAmount = LottoPayment.getAmount(lottoView.inputPurchase());
        int manualLottoAmount = lottoView.inputManualLottoCount();
        int autoLottoAmount = totalLottoAmount - manualLottoAmount;

        Lottos manualLottos = lottoView.inputManualLottoNumber(manualLottoAmount);
        Lottos autoLottos = generator.generateLottos(autoLottoAmount);
        Lottos lottos = Lottos.union(manualLottos, autoLottos);

        lottoView.printAmount(manualLottoAmount, autoLottoAmount);
        lottoView.printLottos(lottos);

        Lotto winLotto = lottoView.inputWinLottoNumber();
        LottoNumber bonusNumber = lottoView.inputBonusNumber();

        Statistics stat = new Statistics(lottos, winLotto, bonusNumber);

        lottoView.printStatistics(stat);
        lottoView.printTotalProfitRate(stat);
    }
}
