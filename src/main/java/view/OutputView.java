package view;

import domain.lotto.ticket.LottoTicket;

import java.util.Arrays;
import java.util.List;

public class OutputView {

    public static void printLottoTickets(LottoTickets lottoTickets) {
        System.out.println(lottoTickets.getLottoTickets().size() + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            System.out.println(lottoTicket.toString());
        }
    }
}
