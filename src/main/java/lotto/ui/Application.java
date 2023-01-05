package lotto.ui;

import lotto.core.Statistics;
import lotto.ui.model.LottoShop;
import lotto.ui.model.Player;
import lotto.ui.view.TerminalInputUI;
import lotto.ui.view.TerminalOutputUI;

import java.io.InputStream;
import java.io.OutputStream;

public class Application {
    private final TerminalInputUI inputUI;
    private final TerminalOutputUI outputUI;
    private final Player player;
    private final LottoShop lottoShop;

    public Application(InputStream inputStream, OutputStream outputStream) {
        this.inputUI = new TerminalInputUI(inputStream, outputStream);
        this.outputUI = new TerminalOutputUI(outputStream);
        this.player = new Player();
        this.lottoShop = new LottoShop();
    }

    public void run() {
        // 지정된 금액으로 매매를 진행
        player.purchaseTicket(lottoShop, inputUI.scanPurchaseMoney());
        // 티켓을 모두 출력
        outputUI.printTickets(player.getLottoTickets());
        // 우승 티켓을 기반으로 통계를 집계
        Statistics result = Statistics.fromLotto(player.getLottoTickets(), inputUI.scanWinningNumber());
        // 결과 출력
        outputUI.printStatistics(result);
        outputUI.printTotalMargin(player.getTotalPurchase(), result.outcome());
    }
}
