package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManualAndAdditionalLottosGenerator implements LottosGenerator {

    private final List<Lotto> manualLottos;
    private final LottosGenerator additionalLottosGenerator;

    public ManualAndAdditionalLottosGenerator(List<Lotto> manualLottos, LottosGenerator additionalLottosGenerator) {
        this.manualLottos = manualLottos;
        this.additionalLottosGenerator = additionalLottosGenerator;
    }

    @Override
    public List<Lotto> generate(Money money) {
        if (money.getLottoCount() < manualLottos.size()) {
            throw new IllegalArgumentException("금액이 부족하여 수동으로 로또를 생성할 수 없습니다.");
        }

        if (money.getLottoCount() > manualLottos.size()) {
            return generateWithAdditionalLottos(money);
        }

        return manualLottos;
    }

    private List<Lotto> generateWithAdditionalLottos(Money money) {
        Money remainMoney = money.getMoneyAfterBuyLottos(manualLottos.size());
        List<Lotto> additionalLottos = additionalLottosGenerator.generate(remainMoney);
        return Stream.concat(manualLottos.stream(), additionalLottos.stream())
                .collect(Collectors.toList());
    }
}
