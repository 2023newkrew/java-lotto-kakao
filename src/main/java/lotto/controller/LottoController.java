package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.config.AppConfig;
import lotto.domain.LottoConstants;
import lotto.domain.LottoHandler;
import lotto.domain.LottoNumbers;
import lotto.view.View;

public class LottoController {

    private final View view;
    private final LottoHandler lottoHandler;


    public static LottoController createLottoController(View view) {
        return new LottoController(view);
    }

    private LottoController(View view) {
        this.view = view;
        this.lottoHandler = AppConfig.getInstance().getLottoHandler();
    }

    public void play() {
        int price = readPrice();
        int passiveLottoCount = readPassiveLottoCount(price);
        makeLotto(price, passiveLottoCount);
        makeLottoAnswer();
        view.getOutputView().printResult(lottoHandler.getLottoResultString());
    }

    private void makeLottoAnswer() {
        List<Integer> lottoAnswer = readLottoAnswer();
        Integer bonusBallNumber = view.getInputView().readBonusBall();
        lottoHandler.setLottoAnswer(lottoAnswer, bonusBallNumber);
    }

    private void makeLotto(int price, int passiveLottoCount) {
        int autoCount = getAutoCount(price, passiveLottoCount);
        lottoHandler.createLottos(IntStream.range(0, passiveLottoCount).boxed()
                .map((i) -> LottoNumbers.createLottoNumbers(view.getInputView().readLottoNumbers())).collect(
                        Collectors.toList()), autoCount);

        view.getOutputView().printCount(passiveLottoCount, autoCount);
        view.getOutputView().printAllLotto(lottoHandler);

    }

    private List<Integer> readLottoAnswer() {
        view.getOutputView().printInputRequestOfWinningNumber();
        List<Integer> readLottoNumbers = view.getInputView().readLottoNumbers();
        view.getOutputView().printReadBonusBall();
        return readLottoNumbers;
    }

    private int readPassiveLottoCount(int price) {
        view.getOutputView().printReadPassiveLottoCount();
        int passiveLottoCount = view.getInputView().readPassiveLottoCount(price);
        if (passiveLottoCount != 0) {
            view.getOutputView().printReadPassiveLottoNumber();
        }
        return passiveLottoCount;
    }

    private int readPrice() {
        view.getOutputView().printReadPrice();
        return view.getInputView().readPrice();
    }

    private int getAutoCount(int price, int passiveLottoCount) {
        return (price / (int) LottoConstants.LOTTO_PRICE) - passiveLottoCount;
    }

}
