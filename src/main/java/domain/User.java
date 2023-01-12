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

    public void purchaseAutoLottoTicket(LottoTicketStore lottoTicketStore, int autoLottoTicketCount){
        int autoLottoTicketCost = LottoTicketStore.AUTO_LOTTO_TICKET_COST;
        int totalPrice = autoLottoTicketCost * autoLottoTicketCount;
        wallet.use(totalPrice);

        autoTickets = lottoTicketStore.purchaseAutoLotto(autoLottoTicketCount, totalPrice);
    }

    public void purchaseManualLottoTicket(LottoTicketStore lottoTicketStore, List<List<LottoNumber>> lottoNumbers, int manualLottoTicketCount){
        int autoLottoTicketCost = LottoTicketStore.MANUAL_LOTTO_TICKET_COST;
        int totalPrice = autoLottoTicketCost * manualLottoTicketCount;
        wallet.use(totalPrice);
        manualTickets = lottoTicketStore.purchaseManualLotto(lottoNumbers, totalPrice);
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
