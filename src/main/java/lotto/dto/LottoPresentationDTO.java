package lotto.dto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.SingleLottoNumber;

public class LottoPresentationDTO {

    private final List<SingleLottoNumber> singleLottoNumberList;

    private LottoPresentationDTO(List<SingleLottoNumber> singleLottoNumberList) {
        this.singleLottoNumberList = new ArrayList<>(singleLottoNumberList);
    }

    public static LottoPresentationDTO from(Lotto lotto) {
        return new LottoPresentationDTO(lotto.getSingleLottoNumberList());
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        message.append("[");
        this.singleLottoNumberList.forEach(e -> message.append(e.getSingleLottoNumber()).append(","));
        message.append("]");

        return message.toString();
    }
}
