package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class User {
    private final Wallet wallet;
    private List<LottoTicket> manualTickets;
    private List<LottoTicket> autoTickets;

    public User(int initAmount) {
        this.wallet = new Wallet(initAmount);
        this.manualTickets = List.of();
        this.autoTickets = List.of();
    }

    public void buyAutoLottoTicket(LottoTicketStore lottoTicketStore, int autoLottoTicketCount){
        int autoLottoTicketCost = LottoTicketStore.AUTO_LOTTO_TICKET_COST;
        int totalCost = autoLottoTicketCost * autoLottoTicketCount;
        wallet.use(totalCost);

        autoTickets = lottoTicketStore.purchaseAutoLotto(autoLottoTicketCount, totalCost);
    }

    public void buyManualLottoTicket(LottoTicketStore lottoTicketStore, List<List<LottoNumber>> lottoNumbers, int manualLottoTicketCount){
        int autoLottoTicketCost = LottoTicketStore.MANUAL_LOTTO_TICKET_COST;
        int totalCost = autoLottoTicketCost * manualLottoTicketCount;
        wallet.use(totalCost);
        manualTickets = lottoTicketStore.purchaseManualLotto(lottoNumbers, totalCost);
    }

    public List<LottoTicket> getManualTickets() {
        return manualTickets;
    }

    public List<LottoTicket> getAutoTickets() {
        return autoTickets;
    }

    public List<LottoTicket> getLottoTickets(){
        return Stream.concat(autoTickets.stream(), manualTickets.stream())
                .collect(Collectors.toList());
    }

    public int getMoneyUsage(){
        return wallet.getUsage();
    }
    public int getRemainAmount(){
        return wallet.getRemainAmount();
    }
}
