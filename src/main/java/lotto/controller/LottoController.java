package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoConstants;
import lotto.domain.LottoHandler;
import lotto.domain.LottoNumbers;
import lotto.domain.Lottos;
import lotto.view.View;

public class LottoController {

    private final View view;


    public static LottoController createLottoController(View view) {
        return new LottoController(view);
    }

    private LottoController(View view) {
        this.view = view;
    }

    public void play() {
        view.getOutputView().printReadPrice();
        int price = view.getInputView().readPrice();
        view.getOutputView().printReadPassiveLottoCount();
        int passiveLottoCount = view.getInputView().readPassiveLottoCount(price);
        view.getOutputView().printReadPassiveLottoNumber();
        LottoHandler lottoHandler = LottoHandler.createLottoHandler();
        int autoCount = getAutoCount(price, passiveLottoCount);
        lottoHandler.createLottos(IntStream.range(0, passiveLottoCount).boxed()
                .map((i) -> LottoNumbers.createLottoNumbers(view.getInputView().readLottoNumbers())).collect(
                        Collectors.toList()), autoCount);

        view.getOutputView().printCount(passiveLottoCount, autoCount);
        view.getOutputView().printAllLotto(lottoHandler);
        view.getOutputView().printInputRequestOfWinningNumber();
        List<Integer> readLottoNumbers = view.getInputView().readLottoNumbers();
        view.getOutputView().printReadBonusBall();
        Integer bonusBallNumber = view.getInputView().readBonusBall();
        lottoHandler.setLottoAnswer(readLottoNumbers, bonusBallNumber);
        view.getOutputView().printResult(lottoHandler.getLottoResultString());


    }

    private int getAutoCount(int price, int passiveLottoCount) {
        return (price / (int) LottoConstants.LOTTO_PRICE) - passiveLottoCount;
    }

}
