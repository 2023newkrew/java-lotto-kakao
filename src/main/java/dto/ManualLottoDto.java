package dto;

import model.Lotto;

import java.util.List;

public class ManualLottoDto {
    private final List<Lotto> lottos;

    public ManualLottoDto(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
