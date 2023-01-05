package lotto.view.input;

import lotto.view.console.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Integer> getListOfInteger(String regex) {
        return Arrays.stream(getString().split(regex))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
