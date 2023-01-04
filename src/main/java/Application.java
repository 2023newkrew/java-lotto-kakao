import lotto.controller.LottoController;
import lotto.view.console.Console;
import lotto.view.console.StandardConsole;

public class Application {
    public static void main(String[] args) {
        Console console = new StandardConsole();
        LottoController controller = new LottoController(console);

        controller.run();
    }
}
