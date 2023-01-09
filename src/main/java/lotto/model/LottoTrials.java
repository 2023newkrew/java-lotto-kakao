package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTrials {
    private final List<Lotto> lottoTrials = new ArrayList<>();

    public void createLottoAutoTrials(LottoCount lottoCountAuto) {
        final LottoPickerRandom lottoPickerRandom = new LottoPickerRandom();

        for (int i = 0; i < lottoCountAuto.getCount(); i++){
            Lotto lotto = new Lotto(lottoPickerRandom.pick());

            lottoTrials.add(lotto);
        }
    }

    public void addLottoManualTrial(Lotto lotto) {
        lottoTrials.add(lotto);
    }

    public Lotto getLottoTrial(int index){
        return lottoTrials.get(index);
    }

    public int getSize() { return lottoTrials.size(); }
}
