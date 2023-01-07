package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTrials {
    private final List<Lotto> lottoTrials = new ArrayList<>();

    public void createLottoTrials(LottoCount lottoCount) {
        final LottoPickerRandom lottoPickerRandom = new LottoPickerRandom();

        for (int i = 0; i < lottoCount.getCount(); i++){
            Lotto lotto = new LottoAuto(lottoPickerRandom);

            lottoTrials.add(lotto);
        }
    }

    public Lotto getLottoTrial(int index){
        return lottoTrials.get(index);
    }

    public int getSize() { return lottoTrials.size(); }
}
