package domain;

public class LottoFactory {

    public static Lotto getLotto(LottoGenerator lottoGenerator) {
        return lottoGenerator.generateLotto();
    }
}
