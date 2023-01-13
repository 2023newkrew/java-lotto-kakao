import controller.LottoSimulator;
import domain.RandomLottoNumbersGenerator;
import domain.LottoTicketStore;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(System.out, System.in);
        OutputView outputView = new OutputView(System.out);
        LottoTicketStore lottoTicketStore = new LottoTicketStore(new RandomLottoNumbersGenerator());

        LottoSimulator lottoSimulator = new LottoSimulator(inputView, outputView, lottoTicketStore);
        lottoSimulator.simulate();
    }
}
