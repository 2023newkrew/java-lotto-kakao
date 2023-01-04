import lotto.controllers.LotteryController;
import lotto.views.Console;
import lotto.views.StandardConsole;

public class Application {
    public static void main(String[] args) {
        Console console = new StandardConsole();
        LotteryController controller = new LotteryController(console);

        controller.run();
    }
}
