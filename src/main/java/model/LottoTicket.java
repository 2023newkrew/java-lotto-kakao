/**
 * 로또의 리스트를 가지고 있다
 * 로또 리스트의 내부를 변경할 수 없어야 한다
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {
    private final List<Lotto> lottos;

    LottoTicket(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottoList() {
        return new ArrayList<>(lottos);
    }

}
