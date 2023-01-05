package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGroup {
    private final List<Lotto> lottoGroup;

    public LottoGroup(long repeat) {
        this.lottoGroup = new ArrayList<>();
        for (int i = 0; i < repeat; i++) {
            lottoGroup.add(new Lotto());
        }
    }

    public LottoGroup(List<Lotto> lottoGroup) {
        this.lottoGroup = lottoGroup;
    }

    public LottoGroup(long autoGenerateNumber, List<Lotto> userInputLottoGroup) {
        this.lottoGroup = userInputLottoGroup;
        for (int i = 0; i < autoGenerateNumber; i++) {
            lottoGroup.add(new Lotto());
        }
    }

    public List<Lotto> getLottoGroup() {
        return Collections.unmodifiableList(this.lottoGroup);
    }

    @Override
    public String toString() {
        return lottoGroup.stream().map(Lotto::toString).collect(Collectors.joining("\n"));
    }
}
