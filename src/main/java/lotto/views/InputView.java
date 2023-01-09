package lotto.views;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    Console console;

    public InputView(Console console) {
        this.console = console;
    }

    public Integer getPositiveInteger() {
        Integer integer = getInteger();
        if (integer < 1) {
            throw new RuntimeException("양수를 입력하세요.");
        }
        return integer;
    }

    private Integer getInteger() {
        try {
            return Integer.parseInt(console.input());
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자를 입력하세요.");
        }
    }

    public List<Integer> getIntegerList() {
        try {
            return Arrays.stream(getString().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new RuntimeException("6개의 숫자를 쉼표로 구분지어 입력해주세요.");
        }
    }

    public String getString() {
        return console.input();
    }
}

