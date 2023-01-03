package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public LottoTickets(List<List<Integer>> numbersList) {
        for (List<Integer> numbers : numbersList) {
            lottoTickets.add(new LottoTicket(numbers));
        }
    }

    public String getLottoNumbersString() {
        return lottoTickets.stream()
                .map(LottoTicket::getString)
                .collect(Collectors.joining("\n"));
    }
}
