import controller.LottoSimulator;
import controller.QuickPickSimulator;
import domain.LottoTicketAutoGenerator;
import domain.LottoTicketStore;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(System.out, System.in);
        OutputView outputView = new OutputView(System.out);
        LottoTicketStore lottoTicketStore = new LottoTicketStore(new LottoTicketAutoGenerator());

        LottoSimulator lottoSimulator = new QuickPickSimulator(inputView, outputView, lottoTicketStore);
        lottoSimulator.simulate();
    }
}
