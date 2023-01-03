package lotto.model;

import lotto.model.generator.LottoGenerator;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoBundle;
import lotto.model.vo.Money;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LottoStore {

    private static final Money LOTTO_PRICE = Money.valueOf(1000L);

    private final LottoGenerator lottoGenerator;

    public static LottoStore create(LottoGenerator lottoGenerator) {
        if (Objects.isNull(lottoGenerator)) {
            throw new IllegalArgumentException("로또 생성기가 필요합니다.");
        }

        return new LottoStore(lottoGenerator);
    }

    private LottoStore(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public PurchaseResult buyLotto(Money money) {
        long lottoCount = money.getPurchasableCount(LOTTO_PRICE);

        return buyLotto(lottoCount);
    }

    private PurchaseResult buyLotto(long lottoCount) {
        LottoBundle lottoBundle = generateLottoBundle(lottoCount);
        Money totalPrice = Money.valueOf(LOTTO_PRICE.longValue() * lottoCount);

        return PurchaseResult.from(lottoBundle, totalPrice);
    }

    private LottoBundle generateLottoBundle(long lottoCount) {
        List<Lotto> lottos = LongStream.range(0, lottoCount)
                .mapToObj(ignore -> lottoGenerator.generate())
                .collect(Collectors.toList());

        return LottoBundle.from(lottos);
    }
}
