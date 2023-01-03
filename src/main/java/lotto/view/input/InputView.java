package lotto.view.input;

import lotto.view.console.Console;

public class InputView {
    Console console;

    public InputView(Console console) {
        this.console = console;
    }

    public Integer getInteger() {
        return Integer.parseInt(console.input());
    }

    public String getString() {
        return console.input();
    }
}
