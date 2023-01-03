import lotto.controllers.LottoController;
import lotto.views.Console;
import lotto.views.StandardConsole;

public class Application {
    public static void main(String[] args) {
        Console console = new StandardConsole();
        LottoController controller = new LottoController(console);

        controller.run();
    }
}
