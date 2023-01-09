package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTrials {
    private final List<Lotto> lottoTrials = new ArrayList<>();
    private static final LottoPicker lottoPickerRandom = new LottoPickerRandom();

    public void createLottoAutoTrials(LottoCount lottoCountAuto) {
        Stream.generate(() -> new Lotto(lottoPickerRandom.pick()))
                .limit(lottoCountAuto.getCount())
                .collect(Collectors.toCollection(() -> lottoTrials));
    }

    public void addLottoManualTrial(Lotto lotto) {
        lottoTrials.add(lotto);
    }

    public Lotto getLottoTrial(int index){
        return lottoTrials.get(index);
    }

    public int getSize() { return lottoTrials.size(); }
}
