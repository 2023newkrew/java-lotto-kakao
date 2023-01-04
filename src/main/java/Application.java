import controller.LottoSimulator;
import controller.QuickPickSimulator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(System.out, System.in);
        OutputView outputView = new OutputView(System.out);
        LottoSimulator lottoSimulator = new QuickPickSimulator(inputView, outputView);
        lottoSimulator.play();
    }
}
