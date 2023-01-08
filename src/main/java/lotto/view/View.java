package lotto.view;

public class View {

    private final InputView inputView;

    private final OutputView outputView;

    private View(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static View createView(InputView inputView, OutputView outputView) {
        return new View(inputView, outputView);
    }

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }
}
