package lotto.domain;

public class GenerateOption {

    private final int generateOption;
    private static final int INPUT_REQUIRED_OPTION = 2;

    public GenerateOption(int generateOption) {
        if (generateOption != 1 && generateOption != 2) {
            throw new IllegalArgumentException("1번 혹은 2번만 선택할 수 있습니다.");
        }
        this.generateOption = generateOption;
    }

    public boolean isInputRequired() {
        return this.generateOption == INPUT_REQUIRED_OPTION;
    }
}
