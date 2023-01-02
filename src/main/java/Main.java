import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        LotteryController lotteryController = new LotteryController(new InputView(), new OutputView());

        lotteryController.run();
    }
}
